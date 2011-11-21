package main;

import java.util.ArrayList;

public class StudentRecord 
{
    public enum Option
    {
        GAME, WEB, CSYS, SSYS, ISYS, MINOR
    }

	private String name;
	private long id;
    private Option option;
	private float gpa, lastAnnualGpa;
	private ArrayList<Course> courses, coursesTaken;
	public StudentRecord(String name, long id, float gpa, float lastAnnualGpa)
	{
		this.name=new String(name);
		this.id=id;
		this.gpa=gpa;
		this.lastAnnualGpa=lastAnnualGpa;
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
	public void addCourse(Course course)
	{
		courses.add(course);
	}
	public void addCourseTaken(Course course)
	{
		coursesTaken.add(course);
	}
	public Course[] getCourses()
	{
		return (Course[])courses.toArray();
	}
	public Course[] getCoursesTaken()
	{
		return (Course[])coursesTaken.toArray();
	}
}
