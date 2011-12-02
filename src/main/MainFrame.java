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
	private ViewPanel mainPanel, detailPanel;
	private MainFrame(String name)
	{
		super(name);
		frame=this;
		model=new MainModel();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(700,800);
		//setLayout(new FlowLayout(FlowLayout.CENTER));
		setLayout(new BorderLayout());
		setPanel(new LoginPanel());
		setVisible(true);
	}
	
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
    	mainPanel=panel;
    	detailPanel=panel.getDetailPanel();
    	updatePanels();		
	}
    public void setDetailPanel(ViewPanel panel)
    {
    	detailPanel=panel;
    	updatePanels();
    }
    private void updatePanels()
    {
    	Container content=getContentPane();
		content.removeAll();
		content.add(mainPanel, BorderLayout.NORTH);
		content.add(detailPanel, BorderLayout.SOUTH);
		content.validate();
		this.repaint();
    }
    public static boolean prereqSatisfied(Course prereq)
    {
    	if (!prereq.wasTaken())
    	{
    		if ( !MainFrame.getModel().getRecord().isRegistered(prereq) )
    			return false;
    	}
    	return true;
    }
    // Main program
	public static void main(String[] args)
	{
		new MainFrame("Student Advisor Expert System");
        model.addFilter(new PrereqFilter());
        model.addFilter(new SemesterFilter());
        model.addFilter(new GradeFilter());
        model.addFilter(new LevelFilter());
        model.addFilter(new CreditFilter());
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
