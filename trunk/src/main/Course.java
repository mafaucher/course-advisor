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
	//this is info pertaining to the student's relationship to the course
	//this should prolly be later consolidated in another class
	private List<String> keywords;
	private boolean bTaken;
	private float grade;
	
	public String getName()
	{
		return new String(name);
	}
	public float getCredits()
	{
		return numCredits;
	}
	public boolean wasTaken()
	{
		return bTaken;
	}
	public float getGrade()
	{
		return grade;
	}
	public void addPrereq(Course course)
	{
		prereqs.add(course);
	}
	//this should prolly be later consolidated in another class -> In student record
	//private boolean wasTaken;
	//private float grade;
    
    public Course(String nb, String nm, float cr, Group gr, List<Course> pr, List<String> kw)
    {
        number = nb;
        name = nm;
        numCredits = cr;
        group = gr;
        prereqs = pr;
        keywords = kw;
    }
    
    /**
     * TODO: (Marc) Finish deciding Class attributes before implementing this method.
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
                sb.append(course.number.trim().length() == 0? "" : course.number);
                sb.append(CSV_SEPARATOR);
                sb.append(course.name.trim().length() == 0? "" : course.name);
                sb.append(CSV_SEPARATOR);
                bw.write(sb.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }

    // Just a quick driver to test io
    public static void main(String[] args) {
        List<Course> pr = new ArrayList<Course>();
        List<String> kw = Arrays.asList("This", "is", "a", "test");
        Course c1 = new Course("COMP 474", "Intelligent Systems", 3, Group.COMP_ELEC, pr, kw);
        List<Course> courseList = new ArrayList<Course>();
        courseList.add(c1);
        Course.writeToCSV(courseList);
        System.out.println("Done");
    }
}
