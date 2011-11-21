package panels;

import java.util.List;

import javax.swing.*;

import main.*;

public class RecordPanel extends ViewPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel nameLabel, coursesTakenLabel, creditsTakenLabel, gradesTakenLabel, coursesLabel, takenLabel;
	private JButton proceedButton;
	public RecordPanel()
	{
		//declare vars
		StudentRecord record=MainFrame.getFrame().getModel().getRecord();
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
		for(Course course : coursesTaken)
		{
			coursesTakenStr+=course.getName()+"<br>";
			creditsTakenStr+=course.getCredits()+"<br>";
			gradesTakenStr+=course.getGrade()+"<br>";
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
		proceedButton=createButton("Proceed", null);
		
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
						      	.addComponent(proceedButton))
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
				      	.addComponent(proceedButton)
				);
	}
}
