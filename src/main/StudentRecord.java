package main;

import java.util.ArrayList;
import java.util.List;

public class StudentRecord 
{
    public enum Option
    {
        GAME, WEB, CSYS, SSYS, ISYS, CAPP, ART, STAT, NONE
    }

    public class CourseRequirements
    {
        public double compCore = 32.00;
        public double encsCore =  6.00;
        public double mathElec =  6.00;
        public double genElec  = 15.00;
        public double specialElec;
        public double compElec;
    }

    public static Option asOption(String option)
    {
        Option result = Option.NONE;
        if (option.equals("GAME")) result = Option.GAME;
        if (option.equals("WEB"))  result = Option.WEB;
        if (option.equals("CSYS")) result = Option.CSYS;
        if (option.equals("SSYS")) result = Option.SSYS;
        if (option.equals("ISYS")) result = Option.ISYS;
        if (option.equals("CAPP")) result = Option.CAPP;
        if (option.equals("ART"))  result = Option.ART;
        if (option.equals("STAT")) result = Option.STAT;
        return result;
    }

	private String name;
	private long id;
    private Option option;
    public CourseRequirements requirements;
	private double gpa, lastAnnualGpa;
	private List<Course> courses, coursesTaken;
	private double suggestedCredits;
	public StudentRecord(String name, long id, Option option, double gpa, double lastAnnualGpa)
	{
		this.name = new String(name);
		this.id = id;
		this.gpa = gpa;
        this.option = option;
		this.lastAnnualGpa = lastAnnualGpa;
		courses = new ArrayList<Course>();
		coursesTaken = new ArrayList<Course>();
        setRequirements();
	}
	public StudentRecord(String name, long id, double gpa, double lastAnnualGpa)
    {
        this(name, id, Option.NONE, gpa, lastAnnualGpa);
    }

    // Setters
    public void setSuggestedCredits(double cr)
    {
    	suggestedCredits=cr;
    }
    // Getters
    public double getSuggestedCredits()
    {
    	return suggestedCredits;
    }
	public String getName()
	{
		return new String(name);
	}

	public long getId()
	{
		return id;
	}
	
    public Option getOption()
	{
		return option;
	}

	public double getGpa()
	{
		return gpa;
	}
	public void setGpa(double gpa)
	{
		this.gpa=gpa;
	}
	public double getLastAnnualGpa()
	{
		return lastAnnualGpa;
	}

	public List<Course> getCourses()
	{
		return new ArrayList<Course>(courses);
	}

	public List<Course> getCoursesTaken()
	{
		return new ArrayList<Course>(coursesTaken);
	}
	
    public double computeSuggestedCredits()
	{
    	if (gpa > 3.0)
    		return 15.0;
    	else if (gpa > 2.0)
    		return 12.0;
    	return 9.0;
	}

    // Methods
	
    public void addCourse(Course course)
	{
		courses.add(course);
	}
    public double getCurrentCredits()
    {
    	double credits=0;
    	for(Course course : courses)
    		credits+=course.getCredits();
    	return credits;
    }
	public void addCourseTaken(Course course, double grade)
	{
		coursesTaken.add(course);
		course.setGrade(grade);
	}

    public void addCourseTaken(Course course, String grade)
    {
        addCourseTaken(course, letterGradeToDouble(grade));
    }
    
    public boolean isRegistered(Course course)
    {
        for (Course registered : courses)
        {
            if (course == registered)
                return true;
        }
        return false;
    }

    private static double letterGradeToDouble(String grade)
    {
        if (grade.equals("A+")) return 4.3;
        if (grade.equals("A"))  return 4.0;
        if (grade.equals("A-")) return 3.7;
        if (grade.equals("B+")) return 3.3;
        if (grade.equals("B"))  return 3.0;
        if (grade.equals("B-")) return 2.7;
        if (grade.equals("C+")) return 2.3;
        if (grade.equals("C"))  return 2.0;
        if (grade.equals("C-")) return 1.7;
        if (grade.equals("D+")) return 1.3;
        if (grade.equals("D"))  return 1.0;
        if (grade.equals("D-")) return 0.7;
        return 0;
    }
    
    private void setRequirements()
    {
        requirements = new CourseRequirements();
        switch (this.option)
        {
            case GAME:
                requirements.specialElec = 23.00;
                requirements.compElec    =  8.00;
                break;
            case WEB:
                requirements.specialElec = 22.00;
                requirements.compElec    =  9.00;
                break;
            case CSYS:
                requirements.specialElec = 21.00;
                requirements.compElec    = 10.00;
                break;
            case SSYS:
                requirements.specialElec = 17.00;
                requirements.compElec    = 14.00;
                break;
            case ISYS:
                requirements.specialElec = 31.00;
                requirements.compElec    = 15.00;
                requirements.genElec     =  0.00;
                break;
            case CAPP:
                requirements.specialElec =  0.00;
                requirements.compElec    = 19.00;
                requirements.genElec     = 27.00;
                break;
            case ART:
                requirements.mathElec    =  0.00;
                requirements.specialElec = 52.00;
                requirements.compElec    =  0.00;
                requirements.genElec     =  0.00;
                break;
            case STAT:
                requirements.specialElec = 36.00;
                requirements.compElec    =  4.00;
                requirements.genElec     = 12.00;
                break;
            default:
                requirements.specialElec =  0.00;
                requirements.compElec    = 31.00;
        }
    }
}
