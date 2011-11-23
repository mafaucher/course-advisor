package main;

import java.util.ArrayList;
import java.util.List;

public class StudentRecord 
{
    public enum Option
    {
        GAME, WEB, CSYS, SSYS, ISYS, ART, STAT, MINOR, NONE
    }

	private String name;
	private long id;
    private Option option;
	private float gpa, lastAnnualGpa;
	private List<Course> courses, coursesTaken;
	//dynamic data pertinent to the program's functionality
	private float suggestedCredits;
	public StudentRecord(String name, long id, float gpa, float lastAnnualGpa)
	{
		this.name=new String(name);
		this.id=id;
		this.gpa=gpa;
		this.lastAnnualGpa=lastAnnualGpa;
		courses=new ArrayList<Course>();
		coursesTaken=new ArrayList<Course>();
	}
	public void setSuggestedCredits(float credits)
	{
		suggestedCredits=credits;
	}
	public float getSuggestedCredits()
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
	public float getGpa()
	{
		return gpa;
	}
	public float lastAnnualGpa()
	{
		return lastAnnualGpa;
	}
	public Option getOption()
	{
		return option;
	}
	public void addCourse(Course course)
	{
		courses.add(course);
	}
	public void addCourseTaken(Course course, float grade)
	{
		coursesTaken.add(course);
		course.setGrade(grade);
	}
	public List<Course> getCourses()
	{
		return new ArrayList<Course>(courses);
	}
	public List<Course> getCoursesTaken()
	{
		return new ArrayList<Course>(coursesTaken);
	}
}
