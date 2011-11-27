package panels;
import javax.swing.*;

import main.Course;

public class ConcentrationPanel extends ViewPanel 
{
	private static final long serialVersionUID = 1L;
	private JComboBox optionBox;
	private JButton nextButton, backButton;
	private JLabel concentrationLabel;
	public ConcentrationPanel()
	{
		optionBox=new JComboBox(getOptionStrings());
		concentrationLabel=new JLabel("Choose the concentration: ");
		nextButton=createButton("Next", null);
		backButton=createButton("Back", null);
		
		GroupLayout layout=new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				      	.addGroup(layout.createSequentialGroup()
				   		.addComponent(concentrationLabel)
				   		.addComponent(optionBox))
				.addGroup(layout.createSequentialGroup()
				   		.addComponent(nextButton)
				   		.addComponent(backButton)))
				);
				layout.setVerticalGroup(
				   layout.createSequentialGroup()
				   	  	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				   	  		.addComponent(concentrationLabel)
				      		.addComponent(optionBox))
				      	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				      		.addComponent(nextButton)
				      		.addComponent(backButton))
				);
		
	}
	private String[] getOptionStrings()
	{
		Course.Group[] groups=Course.Group.values();
		String[] strs=new String[groups.length];
		for(int i=0;i<groups.length;i++)
		{
			strs[i]=groups[i].toString();
		}
		return strs;
	}
	public DetailPanel getDetailPanel() 
	{
		return createDetailPanel("Time to tdddddduel...");
	}
	
}
