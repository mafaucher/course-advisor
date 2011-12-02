package panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.MainFrame;

public class PreferencePanel extends ViewPanel 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel prefLabel;
	private JTextField prefText;
	private JButton nextButton, backButton;
	public PreferencePanel()
	{
		prefLabel=new JLabel("Enter keywords that describe your preferences in the text area below:");
		prefText=new JTextField("", 20);
		ActionListener al=new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				MainFrame.getFrame().setPanel(new ResultPanel());				
			}
		};
		nextButton=createButton("Next", al);
		al=new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				MainFrame.getFrame().setPanel(new CreditPanel());				
			}
		};
		backButton=createButton("Back", al);
		
		GroupLayout layout=new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					   		.addComponent(prefLabel)
					   		.addComponent(prefText)
					   		.addGroup(layout.createSequentialGroup()
					   				.addComponent(nextButton)
					   				.addComponent(backButton)))
				);
				layout.setVerticalGroup(
				   layout.createSequentialGroup()
				   	  	.addComponent(prefLabel)
				      	.addComponent(prefText)
				      	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				      		.addComponent(nextButton)
				      		.addComponent(backButton))
				);
	}
	public DetailPanel getDetailPanel() 
	{
		return createDetailPanel("Input keywords that correspond to your course preferences.");
	}

}
