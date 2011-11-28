package panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.*;

public class CreditPanel extends ViewPanel
{
	private static final long serialVersionUID = 1L;
	private JLabel suggestedText;
	private JTextField suggestedCreditsField;
	private JButton proceedButton, backButton;
	public CreditPanel()
	{
		suggestedText=new JLabel("Suggested number of credits: ");
		suggestedCreditsField=new JTextField(Float.toString(Controller.suggestNumberOfCredits()), 3);
		ActionListener al=new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				MainFrame frame=MainFrame.getFrame();
				Controller.getRecord().setSuggestedCredits(getSuggestedCredits());
				if(Controller.getRecord().getOption()==StudentRecord.Option.NONE)
					frame.setPanel(new ConcentrationPanel());
				else frame.setPanel(new PreferencePanel());
			}
		};
		proceedButton=createButton("Next", al);
		al=new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				MainFrame.getFrame().setPanel(new RecordPanel());
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
						      	.addGroup(layout.createSequentialGroup()
						      		.addComponent(suggestedText)
						      		.addComponent(suggestedCreditsField))
						      	.addGroup(layout.createSequentialGroup()
						      			.addComponent(proceedButton)
						      			.addComponent(backButton)))
				);
				layout.setVerticalGroup(
				   layout.createSequentialGroup()
				   	  	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				   	  		.addComponent(suggestedText)
				      		.addComponent(suggestedCreditsField))
				      	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				      			.addComponent(proceedButton)
				      			.addComponent(backButton))
				);
	}
	private double getSuggestedCredits()
	{
		String creditStr=suggestedCreditsField.getText();
		return Float.valueOf(creditStr);
	}
	public DetailPanel getDetailPanel() 
	{
		String text="Based on random ass shit that we came up with, we think that u should take "+suggestedCreditsField.getText()+" credits";
		return createDetailPanel(text);
	}
}
