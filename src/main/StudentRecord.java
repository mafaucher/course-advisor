package main;

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
	private Course[] courses, coursesTaken;
}
