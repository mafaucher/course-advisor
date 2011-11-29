package main;

import java.awt.*;

import javax.swing.*;

import panels.*;
import filters.*;

public class MainFrame extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static MainFrame frame;
	private static MainModel model;

	private MainFrame(String name)
	{
		super(name);
		frame=this;
		model=new MainModel();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,700);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setPanel(new LoginPanel());
		setVisible(true);
	}
	/*// Temp method to test filters
	private MainFrame(String id)
	{
	    model = new MainModel();
	}
	*/
	// Getters
	
	public static MainFrame getFrame()
	{
		return frame;
	}

	public static MainModel getModel()
	{
		return model;
	}

    // Methods
	
    public void setPanel(ViewPanel panel)
	{
		Container content=getContentPane();
		content.removeAll();
		content.add(panel);
		content.add(panel.getDetailPanel());
		content.validate();
		this.repaint();
	}

    // Main program

	public static void main(String[] args)
	{
		new MainFrame("Student Advisor Expert System");
	    model.loadStudentRecord(9999999L);
        model.addFilter(new PrereqFilter());
        model.addFilter(new SemesterFilter());
        model.addFilter(new LevelFilter());
        model.addFilter(new CreditFilter());
	    model.computeScores();
	    for(Course course : model.getAllCourses())
        {
	        if (course.getScore() > 0)
	        {
	            System.out.println(course.getNumber());
	        }
        }
	    System.out.println("Done");
	}
}
