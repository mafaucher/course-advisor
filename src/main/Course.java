package main;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.io.*;

public class Course
{
    public enum Group
    {
        COMP_CORE, ENCS_CORE, COMP_ELEC, MATH_ELEC, GEN_ELEC,
        GAME_ELEC, WEB_ELEC, CSYS_ELEC, SSYS_ELEC, ISYS_ELEC, ART_ELEC, STAT_ELEC
    }

    private String number;
	private String name;
	private float numCredits;
    private Group group;
	private List<Course> prereqs;
    private boolean[] semesters; // 0=Summer; 1=Fall; 2=Fall/Winter; 3=Winter
	private List<String> keywords;
	//this is info pertaining to the student's relationship to the course
	//this should prolly be later consolidated in another class
	private float grade;

    // Constructors

	public Course(String name, String number, float numCredits)
	{
		grade=-1.0f;
		this.name=new String(name);
		this.number=new String(number);
		this.numCredits=numCredits;
		prereqs=new ArrayList<Course>();
        semesters = new boolean[4];
	}
    
    public Course(String name, String number, float numCredits, Group group,
                  boolean[] semesters, List<String> keywords)
    {
        this.name = new String(name);
        this.number = new String(number);
        this.numCredits = numCredits;
        this.group = group;
        this.setSemesters(semesters);
        this.keywords = new ArrayList<String>(keywords);
        this.prereqs = new ArrayList<Course>();
        this.grade = -1.0f;
    }

    public Course(Course course)
    {
        this.name = course.getName();
        this.number = course.getNumber();
        this.numCredits = course.getCredits();
        this.group = course.getGroup();
        this.setSemesters(course.getSemesters());
        this.keywords = course.getKeywords();
        this.prereqs = course.getPrereqs();
        this.grade = course.getGrade();
    }
    
    // Setters

	public void setGrade(float grade)
	{
		this.grade=grade;
	}
    public void setSemesters(boolean[] semesters)
    {
        this.semesters = new boolean[4];
        for (int i = 0; i < semesters.length && i < 4; i++)
        {
            this.semesters[i] = semesters[i];
        }
    }

    // Getters
    
	public String getName()
	{
		return new String(name);
	}
    public String getNumber()
    {
        return new String(number);
    }
	public float getCredits()
	{
		return numCredits;
	}
    public Group getGroup()
    {
        return group;
    }
    public boolean[] getSemesters()
    {
        semesters = new boolean[4];
        for (int i = 0; i < semesters.length; i++)
        {
            semesters[i] = this.semesters[i];
        }
        return semesters;
    }
    public List<String> getKeywords()
    {
        return new ArrayList<String>(keywords);
    }
    public List<Course> getPrereqs()
    {
        return new ArrayList<Course>(prereqs);
    }
	public float getGrade()
	{
		return grade;
	}

	public boolean wasTaken()
	{
		return grade>=0.0;
	}
	public void addPrereq(Course course)
	{
		prereqs.add(course);
	}
    
    /**
     * TODO: Missing group, semesters, keywords, and prereqs.
     * Source: http://stackoverflow.com/questions/3666007/how-to-serialize-object-to-csv-file
     */
    private static final String CSV_SEPARATOR = ",";
    private static void writeToCSV(List<Course> courseList)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("courselist.csv"), "UTF-8"));
            for (Course course : courseList)
            {
                StringBuffer sb = new StringBuffer();
                /* Examples:
                sb.append(course.getId() <=0 ? "" : course.getId());
                sb.append(CSV_SEPARATOR);
                sb.append(course.getName().trim().length() == 0? "" : course.getName());
                sb.append(CSV_SEPARATOR);
                sb.append(course.getCostPrice() < 0 ? "" : course.getCostPrice());
                sb.append(CSV_SEPARATOR);
                sb.append(course.isVatApplicable() ? "Yes" : "No");
                */

                sb.append(course.getName().trim().length() == 0? "" : course.getName());
                sb.append(CSV_SEPARATOR);
                sb.append(course.getNumber().trim().length() == 0? "" : course.getNumber());
                sb.append(CSV_SEPARATOR);
                sb.append(course.getCredits());
                
                bw.write(sb.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e) {}
        catch (IOException e){}
    }

    /**
     * TODO: ArrayList is filled with null pointers, did I mess up something with the scope?
     */
    private static List<Course> readFromCSV()
    {
        try
        {
            String line = null;
            ArrayList<Course> courses = new ArrayList<Course>();
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("courselist.csv"), "UTF-8"));
            while( (line = br.readLine()) != null )
            {
                System.out.println(2);
                String[] result = line.split(CSV_SEPARATOR);
                System.out.println(result[0]);
                System.out.println(result[1]);
                System.out.println(result[2]);

                Course course = new Course(result[0], result[1], new Float(result[2]).floatValue());
                courses.add(course);
            }
            br.close();
            return courses;
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e) {}
        catch (IOException e) {}
        finally
        {
            return null;
        }
    }

    // Just a quick driver to test io
    public static void main(String[] args) {
        Course c1 = new Course("Intelligent Systems", "COMP 474", 3);
        Course c2 = new Course("Pattern Recognition", "COMP 473", 3);
        List<Course> courseList = new ArrayList<Course>();
        courseList.add(c1);
        courseList.add(c2);
        Course.writeToCSV(courseList);
        System.out.println("Done");
        List<Course> newList = Course.readFromCSV();
        System.out.println(newList.get(0).getNumber());
    }
}
