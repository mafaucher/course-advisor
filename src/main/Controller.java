package main;

import java.util.ArrayList;
import filters.IFilter;

public class Controller 
{
	public static StudentRecord getRecord()
	{
		return MainFrame.getModel().getRecord();
	}
	public static double suggestNumberOfCredits()
	{
		return Math.max(getRecord().computeSuggestedCredits()-getRecord().getCurrentCredits(), 0);
	}
	public static void reportError(String err)
	{
		MainFrame.getModel().addError(err);
	}
	public static ArrayList<String> getErrors()
	{
		return MainFrame.getModel().getErrors();
	}
	public static ArrayList<String> getKeywords()
	{
		return MainFrame.getModel().getKeywords();
	}
	public static void setSelectedKeywords(ArrayList<String> keywords)
	{
	    MainFrame.getModel().setSelectedKeywords(keywords);
	}
	public static String embedHtml(String str)
	{
		return "<html><body>"+str+"</body></html>";
	}
	public static String getCourseDetails(Course course)
	{
		//TODO: enter details about the filter processing of this course
		String result = course.toString()+"<br>";
		result += Course.groupToString(MainFrame.getModel().dominantGroup(course.getGroups()))+"<br>";
		result += "<br>";
		for ( IFilter filter : MainFrame.getModel().getFilters() )
		{
			if (filter.getDetails(course) != null)
			{
				result += filter.getName()+":<br>";
				result += "Result: "+filter.processScore(course, 1.0f)+"<br>";
				result += "Reason: "+filter.getDetails(course)+"<br>";
				result += "<br>";
			}
		}
		return result;
	}
}
