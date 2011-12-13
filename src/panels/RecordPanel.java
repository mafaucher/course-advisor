package panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;

import main.*;

public class RecordPanel extends ViewPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel nameLabel, coursesTakenLabel, creditsTakenLabel, gradesTakenLabel, coursesLabel, takenLabel, gpaLabel;
	private JButton proceedButton, backButton;
	public RecordPanel()
	{
		//declare vars
		StudentRecord record=MainFrame.getModel().getRecord();
		String nameStr, coursesTakenStr, creditsTakenStr, gradesTakenStr, coursesStr;
		List<Course> coursesTaken=record.getCoursesTaken();
		List<Course> courses=record.getCourses();
		//init vars
		nameStr=record.getName()+" "+record.getId();
		//courses str
		coursesStr=new String("Courses this semester: ");
		for(Course course : courses)
			coursesStr+=course.getName()+"; ";
		coursesStr=coursesStr.trim();
		//all taken strings
		coursesTakenStr=getHead()+"Course<br>";
		creditsTakenStr=getHead()+"Credits<br>";
		gradesTakenStr=getHead()+"Grades<br>";
		if(coursesTaken.size()>0)
			for(Course course : coursesTaken)
			{
				coursesTakenStr+=course.getName()+"<br>";
				creditsTakenStr+=course.getCredits()+"<br>";
				gradesTakenStr+=course.getGrade()+"<br>";
			}
		else
		{
			coursesTakenStr+="N/A";
			gradesTakenStr+="N/A";
			creditsTakenStr+="N/A";
		}
		setTail(coursesTakenStr);
		setTail(gradesTakenStr);
		setTail(creditsTakenStr);
		//create the labels
		nameLabel=new JLabel(nameStr);
		coursesLabel=new JLabel(coursesStr);
		coursesTakenLabel=new JLabel(coursesTakenStr);
		creditsTakenLabel=new JLabel(creditsTakenStr);
		gradesTakenLabel=new JLabel(gradesTakenStr);
		takenLabel=new JLabel("Courses Completed:");
		gpaLabel=new JLabel(Controller.embedHtml("Average GPA: "+Controller.getRecord().getGpa()));
		ActionListener al=new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				MainFrame.getFrame().setPanel(new CreditPanel());
			}
		};
		if(Controller.getRecord().getGpa()<2.0)
			al=null;
		proceedButton=createButton("Proceed", al);
		al=new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				MainFrame.getFrame().setPanel(new LoginPanel());
			}
		};
		backButton=createButton("Logout", al);
		GroupLayout layout=new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				   layout.createSequentialGroup()
				      	
				      	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				      			.addComponent(nameLabel)
				      			.addComponent(takenLabel)
						      	.addGroup(layout.createSequentialGroup()
						      		.addComponent(coursesTakenLabel)
						      		.addComponent(creditsTakenLabel)
						      		.addComponent(gradesTakenLabel))
						      	.addComponent(coursesLabel)
						      	.addComponent(gpaLabel)
						      	.addGroup(layout.createSequentialGroup()
						      			.addComponent(proceedButton)
						      			.addComponent(backButton)))
				);
				layout.setVerticalGroup(
				   layout.createSequentialGroup()
				   	  	.addComponent(nameLabel)
				      	.addComponent(takenLabel)
				   	  	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				   	  		.addComponent(coursesTakenLabel)
				      		.addComponent(creditsTakenLabel)
				      		.addComponent(gradesTakenLabel))
				      	.addComponent(coursesLabel)
				      	.addComponent(gpaLabel)
				      	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				      			.addComponent(proceedButton)
				      			.addComponent(backButton))
				);
	}
	@Override
	public DetailPanel getDetailPanel() 
	{
		String text;
		if(Controller.getRecord().getGpa()<2.0)
			text="Your gpa is lower than 2.0. Gtfo!";
		else text="You are in good standing.<br>Click on proceed to, well, proceed.";
		return createDetailPanel(text);
	}
}
