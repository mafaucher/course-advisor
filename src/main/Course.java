package main;

import java.util.List;
import java.util.ArrayList;

public class Course
{
    public enum Group
    {
        COMP_CORE, ENCS_CORE, COMP_ELEC, MATH_ELEC, GEN_ELEC, NONE
        GAME_ELEC, WEB_ELEC, CSYS_ELEC, SSYS_ELEC, ISYS_ELEC, ART_ELEC, STAT_ELEC
    }
    
    public static Group asGroup(String group)
    {
        Group result = Group.NONE;
        if (group.equals("COMP_CORE")) result = Group.COMP_CORE;
        if (group.equals("ENCS_CORE")) result = Group.ENCS_CORE;
        if (group.equals("COMP_ELEC")) result = Group.COMP_ELEC;
        if (group.equals("MATH_ELEC")) result = Group.MATH_ELEC;
        if (group.equals("GEN_ELEC"))  result = Group.GEN_ELEC;
        if (group.equals("GAME_ELEC")) result = Group.GAME_ELEC;
        if (group.equals("WEB_ELEC"))  result = Group.WEB_ELEC;
        if (group.equals("CSYS_ELEC")) result = Group.CSYS_ELEC;
        if (group.equals("SSYS_ELEC")) result = Group.SSYS_ELEC;
        if (group.equals("ISYS_ELEC")) result = Group.ISYS_ELEC;
        if (group.equals("ART_ELEC"))  result = Group.ART_ELEC;
        if (group.equals("STAT_ELEC")) result = Group.STAT_ELEC;
        return result;
    }

    public static Group asGroup(StudentRecord.Option option)
    {
        switch (option)
        {
            case StudentRecord.Option.GAME:
                return Group.GAME_ELEC;
            case StudentRecord.Option.WEB:
                return Group.WEB_ELEC;
            case StudentRecord.Option.CSYS:
                return Group.CSYS_ELEC;
            case StudentRecord.Option.SSYS:
                return Group.SSYS_ELEC;
            case StudentRecord.Option.ISYS:
                return Group.ISYS_ELEC;
            case StudentRecord.Option.ART:
                return Group.ART_ELEC;
            case StudentRecord.Option.STAT:
                return Group.STAT_ELEC;
            case default:
                return Group.NONE;
        }
    }

    private String number;
	private String name;
	private double credits;
    private List<Group> groups;
	private List<Course> prereqs;
    private boolean[] semesters; // [0]=Summer; [1]=Fall; [2]=Fall/Winter; [3]=Winter
	private List<String> keywords;
	private double grade;
    private double score;

    // Constructors

	public Course(String number, String name, double credits)
	{
		this.number=new String(number);
		this.name=new String(name);
		this.credits=credits;
		prereqs=new ArrayList<Course>();
        semesters = new boolean[4];
		grade=-1.0f;
        score = 0.0;
	}
    
    public Course(String number, String name, double credits, List<String> keywords)
    {
        this.number = new String(number);
        this.name = new String(name);
        this.credits = credits;
        this.groups = new ArrayList<Group>();
        this.semesters = new boolean[4];
        this.keywords = new ArrayList<String>(keywords);
        this.prereqs = new ArrayList<Course>();
        this.grade = -1.0f;
        score = 0.0;
    }
    
    public Course(String number, String name, double credits,
            boolean[] semesters, List<String> keywords)
    {
        this.number = new String(number);
        this.name = new String(name);
        this.credits = credits;
        this.groups = new ArrayList<Group>();
        this.setSemesters(semesters);
        this.keywords = new ArrayList<String>(keywords);
        this.prereqs = new ArrayList<Course>();
        this.grade = -1.0f;
        score = 0.0;
    }

    public Course(Course course)
    {
        this.name = course.getName();
        this.number = course.getNumber();
        this.credits = course.getCredits();
        this.groups = course.getGroups();
        this.setSemesters(course.getSemesters());
        this.keywords = course.getKeywords();
        this.prereqs = course.getPrereqs();
        this.grade = course.getGrade();
        this.score = course.getScore();
    }
    
    // Setters

    public void setNumber(String number)
    {
        this.number = new String(number);
    }

    public void setName(String name)
	{
		this.name = new String(name);
	}

	public void setCredits(double credits)
	{
		this.credits = credits;
	}

    public void setSemesters(boolean[] semesters)
    {
        this.semesters = new boolean[4];
        for (int i = 0; i < semesters.length && i < this.semesters.length; i++)
        {
            this.semesters[i] = semesters[i];
        }
    }

    public void setSemesters(int[] semesters)
    {
        this.semesters = new boolean[4];
        for (int i = 0; i < semesters.length; i++)
        {
            switch (semesters[i])
            {
                case 1:
                    this.semesters[0] = true;
                    break;
                case 2:
                    this.semesters[1] = true;
                    break;
                case 3:
                    this.semesters[2] = true;
                    break;
                case 4:
                    this.semesters[3] = true;
                    break;
            }
        }
    }

    public void setKeywords(List<String> keywords)
    {
        this.keywords = new ArrayList<String>();
        for (String keyword : keywords)
        {
            this.keywords.add(keyword);
        }
    }

	public void setGrade(double grade)
	{
		this.grade = grade;
	}

    public void setScore(double score)
    {
        this.score = score;
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
	
    public double getCredits()
	{
		return credits;
	}
    
    public List<Group> getGroups()
    {
        return groups;
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
	
    public double getGrade()
	{
		return grade;
	}

    public double getScore()
    {
        return score;
    }

    // Methods

	public boolean wasTaken()
	{
		return grade >= 0.0;
	}

	public void addPrereq(Course course)
	{
		prereqs.add(course);
	}

	public void addGroup(Group group)
	{
	    groups.add(group);
	}
}
