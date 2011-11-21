package main;

import java.util.ArrayList;
import java.util.Hashtable;

public class MainModel 
{
	private StudentRecord record;
	private Hashtable<String, Course> courses;
	
	public MainModel()
	{
		courses=new Hashtable<String, Course>();
	}
	public void addCourse(Course course)
	{
		courses.put(course.getName(), course);
	}
	public Course getCourse(String courseName)
	{
		return courses.get(courseName);
	}
	public StudentRecord getRecord()
	{
		return record;
	}
	public void linkPrereqs(Course course, ArrayList<String> prereqNames)
	{
		for(String name : prereqNames)
		{
			Course prereq=getCourse(name);
			course.addPrereq(prereq);	
		}
	}
}
