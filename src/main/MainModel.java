package main;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.io.*;

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
	private ArrayList<String> errorMsgs;
	public MainModel()
	{
		errorMsgs = new ArrayList<String>();
		courses = new Hashtable<String, Course>();
		loadTestData();
	}
	private void loadTestData()
	{
		record=new StudentRecord("Ratslayer", 9591222, 8.9f, 9.001f);
		/*addCourse(new Course("COMP", "474", 4.0f));
		addCourse(new Course("COMP", "373", 3.0f));
		addCourse(new Course("COMP", "576", 5.0f));
		addCourse(new Course("COMP", "273", 1.0f));
		record.addCourseTaken(getCourse("COMP 474"), 4.3f);
		record.addCourseTaken(getCourse("COMP 373"), 3.0f);
		record.addCourseTaken(getCourse("COMP 576"), 3.7f);
		record.addCourse(getCourse("COMP 273"));*/
	}
	public void addError(String err)
	{
		errorMsgs.add(new String(err));
	}
	public ArrayList<String> getErrors()
	{
		return errorMsgs;
	}
	public void clearErrors()
	{
		errorMsgs.clear();
	}
	public void addCourse(Course course)
	{
		courses.put(course.getNumber(), course);
	}
	public Course getCourse(String courseNumber)
	{
		return courses.get(courseNumber);
	}
	public StudentRecord getRecord()
	{
		return record;
	}
	
    public void linkPrereqs(Course course, List<String> prereqNumbers)
	{
		for(String number : prereqNumbers)
		{
			Course prereq = getCourse(number);
			course.addPrereq(prereq);	
		}
	}
    
    public List<Course> loadCoursesFromCSV()
    {
        try
        {
            String line = null;
            ArrayList<Course> courses = new ArrayList<Course>();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(COURSE_FILE), "UTF-8"));
            while( (line = br.readLine()) != null )
            {
                String[] result = line.split(CSV_ATTR_SEPARATOR);
                Course course = new Course( result[0],
                                            result[1],
                                            new Float(result[2]).floatValue(),
                                            Course.asGroup(result[3]),
                                            Arrays.asList(result[5].split(CSV_LIST_SEPARATOR)) );
                // Convert semesters to int array
                String[] strSemesters = result[4].split(CSV_LIST_SEPARATOR);
                int[] intSemesters = new int[4];
                for (int i = 0; i < strSemesters.length && i < intSemesters.length; i++)
                {
                    intSemesters[i] = Integer.parseInt(strSemesters[i]);
                }
                course.setSemesters(intSemesters);
                courses.add(course);
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
    
    public List<CourseEntry> loadPrereqsFromCSV()
    {
        try
        {
            String line = null;
            ArrayList<CourseEntry> entries = new ArrayList<CourseEntry>();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(COURSE_FILE), "UTF-8"));
            while( (line = br.readLine()) != null )
            {
                String[] result = line.split(CSV_ATTR_SEPARATOR);
                Course course = getCourse(result[0]);
                List<String> prereqs = new ArrayList<String>( Arrays.asList( result[6].split(CSV_LIST_SEPARATOR) ) );
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
}
