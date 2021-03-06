package main;

import java.util.List;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Arrays;
import java.util.Hashtable;
import java.io.*;

import filters.IFilter;

public class MainModel 
{
    static final String COURSE_FILE = "courselist.csv";
    static final String CSV_ATTR_SEPARATOR = ",";
    static final String CSV_LIST_SEPARATOR = ":";

    private class CourseEntry
    {
        private Course course;
        private List<String> prereqs;
    }

	private StudentRecord record;
	private Hashtable<String, Course> courses;
	private ArrayList<String> errors;
	private ArrayList<IFilter> filters;
	private ArrayList<String> keywords;
	private ArrayList<String> selectedKeywords;

	public MainModel()
	{
		errors = new ArrayList<String>();
		courses = new Hashtable<String, Course>();
		filters = new ArrayList<IFilter>();
		keywords = new ArrayList<String>();
		selectedKeywords = new ArrayList<String>();
		loadCourses();
	}
	
    // Getters
	public StudentRecord getRecord()
	{
		return record;
	}
    
    public ArrayList<Course> getAllCourses()
    {
        Enumeration<Course> allCourses=courses.elements();
        ArrayList<Course> courses=new ArrayList<Course>();
        while(allCourses.hasMoreElements())
            courses.add(allCourses.nextElement());
        return courses;
    }
 
    public Course getCourse(String courseNumber)
	{
		return courses.get(courseNumber);
	}
	
    public ArrayList<String> getErrors()
	{
		return errors;
	}
	
    public ArrayList<IFilter> getFilters()
    {
    	return filters;
    }
    
    public ArrayList<String> getKeywords()
    {
        return keywords;
    }
    public ArrayList<String> getSelectedKeywords()
    {
        return selectedKeywords;
    }
    
    // Methods
    public void addFilter(IFilter filter)
    {
        filters.add(filter);
    }

	public void addError(String err)
	{
		errors.add(new String(err));
	}

	public void clearErrors()
	{
		errors.clear();
	}

    // Return the Group which corresponds to the studentRecord option
    public Course.Group dominantGroup(List<Course.Group> groups)
    {
        if (groups.size() == 1)
        {
            return groups.get(0);
        }
        else if (groups.size() > 1)
        {
            Course.Group studentGroup = Course.asGroup( MainFrame.getModel().getRecord().getOption() );
            for (Course.Group group : groups)
            {
                if (studentGroup == group)
                    return group;
            }
        }
        return Course.Group.NONE;
    }
	
	public void addCourse(Course course)
	{
		courses.put(course.getNumber(), course);
	}

    public void linkPrereqs(Course course, List<String> prereqNumbers)
	{
		for(String number : prereqNumbers)
		{
			Course prereq = getCourse(number);
            if (prereq != null)
            {
    			course.addPrereq(prereq);
            }
		}
	}

    public void computeScores()
    {
        ArrayList<Course> courses = getAllCourses();
        for(Course course : courses)
        {
            double score = 1.0f;
            for(IFilter filter : filters)
            {
                score = filter.processScore(course, score);
            }
            course.setScore(score);
        }
    }
    
    public void setSelectedKeywords(ArrayList<String> kw)
    {
        selectedKeywords = new ArrayList<String>(kw);
    }
    
    private void updateKeywords(Course course)
    {
        for (String courseKeyword : course.getKeywords())
        {
            boolean newKeyword = true;
            for (String keyword : keywords)
            {
                if (keyword.equals(courseKeyword))
                {
                    newKeyword = false;
                    break;
                }
            }
            if (newKeyword)
            {
                keywords.add(courseKeyword);
            }
        }
    }

    // Serialisation
    private List<Course> loadCoursesFromCSV()
    {
        try
        {
            String line = null;
            ArrayList<Course> courses = new ArrayList<Course>();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(COURSE_FILE), "UTF-8"));
            while( (line = br.readLine()) != null )
            {
                // Skip lines with a leading '#' or only whitespace
                if ( line.trim().equals("") || line.trim().charAt(0) == '#')
                {
                    continue;
                }
                // new Course from comma separated values
                String[] result = line.split(CSV_ATTR_SEPARATOR);
                Course course = new Course( result[0],
                                            result[1],
                                            new Double(result[2]).doubleValue(),
                                            Arrays.asList(result[5].split(CSV_LIST_SEPARATOR)) );
                // Assign groups
                String[] groups = result[3].split(CSV_LIST_SEPARATOR);
                for (int i = 0; i < groups.length; i++)
                {
                    course.addGroup(Course.asGroup(groups[i]));
                }
                // Assign available semesters
                String[] strSemesters = result[4].split(CSV_LIST_SEPARATOR);
                int[] intSemesters = new int[strSemesters.length];
                for (int i = 0; i < strSemesters.length && i < intSemesters.length; i++)
                {
                    intSemesters[i] = Integer.parseInt(strSemesters[i]);
                }
                course.setSemesters(intSemesters);
                courses.add(course);
                updateKeywords(course);
            }
            br.close();
            return courses;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
    
    private List<CourseEntry> loadPrereqsFromCSV()
    {
        try
        {
            String line = null;
            ArrayList<CourseEntry> entries = new ArrayList<CourseEntry>();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(COURSE_FILE), "UTF-8"));
            while( (line = br.readLine()) != null )
            {
                // Skip lines with a leading '#' or only whitespace
                if ( line.trim().equals("") || line.trim().charAt(0) == '#' )
                {
                    continue;
                }

                // new CourseEntry from comma separated values
                String[] result = line.split(CSV_ATTR_SEPARATOR);
                Course course = getCourse(result[0]);
                List<String> prereqs = new ArrayList<String>();
                if (result.length == 7)
                {
                    prereqs = new ArrayList<String>( Arrays.asList( result[6].split(CSV_LIST_SEPARATOR) ) );
                }

                CourseEntry entry = new CourseEntry();
                entry.course = course;
                entry.prereqs = prereqs;
                entries.add(entry);
            }
            br.close();
            return entries;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
    
    private StudentRecord loadStudentRecordFromCSV(String filename)
    {
        try
        {
            StudentRecord record = null;
            String line = null;
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF-8"));
            while( (line = br.readLine()) != null )
            {
                // Skip lines with a leading '#' or only whitespace
                if ( line.trim().equals("") || line.trim().charAt(0) == '#')
                {
                    continue;
                }
                // new StudentRecord from first line
                else if (record == null)
                {
                    String[] result = line.split(CSV_ATTR_SEPARATOR);
                    record = new StudentRecord( result[0],
                                                new Long(result[1]).longValue(),
                                                StudentRecord.asOption(result[2]),
                                                new Double(result[3]).doubleValue(),
                                                new Double(result[4]).doubleValue() );
                }
                // Assign Course
                else
                {
                    String[] result = line.split(CSV_ATTR_SEPARATOR);
                    if (result.length > 1)
                        record.addCourseTaken( getCourse(result[0]), (result[1]) );
                    else
                        record.addCourse( getCourse(result[0]) );
                }
            }
            br.close();
            double gpa=0;
            double nCredits=0;
            List<Course> courses=record.getCoursesTaken();
            for(Course course : courses)
            {
            	gpa+=course.getGrade()*course.getCredits();
            	nCredits+=course.getCredits();
            }
            gpa/=nCredits;
            record.setGpa(gpa);
            return record;
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return null;
    }
    
    public void loadCourses()
    {
        List<Course> courses = loadCoursesFromCSV();
        for (Course course : courses)
        {
            addCourse(course);
        }
        List<CourseEntry> entries = loadPrereqsFromCSV();
        for (CourseEntry entry : entries)
        {
	        linkPrereqs(entry.course, entry.prereqs);
        }
    }

    public void loadStudentRecord(long id)
    {
        record = loadStudentRecordFromCSV(id + ".csv");
    }
    
    // Driver
    /*public static void main(String[] args)
    {
        MainModel mm = new MainModel();
        mm.loadStudentRecord(9614729L);
        System.out.println(mm.getCourse("COMP 228").getPrereqs().get(0).getNumber());
        System.out.println(mm.getRecord().getName());
    }*/
}
