package main;

import java.util.ArrayList;

public class Controller 
{
	public static StudentRecord getRecord()
	{
		return MainFrame.getFrame().getModel().getRecord();
	}
	public static float suggestNumberOfCredits()
	{
		//StudentRecord record=getRecord();
		return 15;
	}
	public static void reportError(String err)
	{
		MainFrame.getFrame().getModel().addError(err);
	}
	public static ArrayList<String> getErrors()
	{
		return MainFrame.getFrame().getModel().getErrors();
	}
	public static String embedHtml(String str)
	{
		return "<html><body>"+str+"</body></html>";
	}
}
