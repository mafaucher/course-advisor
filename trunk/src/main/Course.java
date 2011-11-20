package main;

import java.util.List;

public class Course
{
	private String name;
	private float numCredits;
	private List<Course> prereqs;
	//this is info pertaining to the student's relationaship to the course
	//this should prolly be later consolidated in another class
	private boolean wasTaken;
	private float grade;
}