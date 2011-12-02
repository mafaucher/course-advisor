package main;

import java.util.ArrayList;

public class Controller 
{
	public static StudentRecord getRecord()
	{
		return MainFrame.getModel().getRecord();
	}
	public static double suggestNumberOfCredits()
	{
		return getRecord().computeSuggestedCredits();
	}
	public static void reportError(String err)
	{
		MainFrame.getModel().addError(err);
	}
	public static ArrayList<String> getErrors()
	{
		return MainFrame.getModel().getErrors();
	}
	public static String embedHtml(String str)
	{
		return "<html><body>"+str+"</body></html>";
	}
	public static String getCourseDetails(Course course)
	{
		//TODO: enter details about the filter processing of this course
		return course.getNumber();
	}
}
