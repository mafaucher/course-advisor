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
		loadTestData();
	}
	private void loadTestData()
	{
		record=new StudentRecord("Ratslayer", 9591222, 8.9f, 9.001f);
		addCourse(new Course("COMP", "474", 4.0f));
		addCourse(new Course("COMP", "373", 3.0f));
		addCourse(new Course("COMP", "576", 5.0f));
		addCourse(new Course("COMP", "273", 1.0f));
		record.addCourseTaken(getCourse("COMP 474"), 4.3f);
		record.addCourseTaken(getCourse("COMP 373"), 3.0f);
		record.addCourseTaken(getCourse("COMP 576"), 3.7f);
		record.addCourse(getCourse("COMP 273"));
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
