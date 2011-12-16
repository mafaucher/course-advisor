package panels;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import main.Controller;
import main.MainFrame;

public class PreferencePanel extends ViewPanel 
{

	private static final long serialVersionUID = 1L;
	private JLabel prefLabel;
	//private JTextField prefText;
	private JButton nextButton, backButton;
	private JPanel checkPanel;
	public PreferencePanel()
	{
		prefLabel=new JLabel("Choose the keywords that describe your preferences from the checklist below:");
		//prefText=new JTextField("", 20);
		checkPanel=new JPanel(new GridLayout(0, 1));
		List<String> keywords=Controller.getKeywords();
		for(String k : keywords)
		{
			JCheckBox chb=new JCheckBox(k);
			chb.setSelected(false);
			checkPanel.add(chb);
		}
		JScrollPane checkPane=new JScrollPane(checkPanel);
		Dimension d=new Dimension(500, 300);
		checkPane.setPreferredSize(d);
		ActionListener al=new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
                fetchKeywords();
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
					   		.addComponent(checkPane)
					   		.addGroup(layout.createSequentialGroup()
					   				.addComponent(nextButton)
					   				.addComponent(backButton)))
				);
				layout.setVerticalGroup(
				   layout.createSequentialGroup()
				   	  	.addComponent(prefLabel)
				      	.addComponent(checkPane)
				      	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				      		.addComponent(nextButton)
				      		.addComponent(backButton))
				);
	}
	private void fetchKeywords()
	{
		ArrayList<String> keywords=new ArrayList<String>();
		Component[] components=checkPanel.getComponents();
		for(int i=0;i<components.length;i++)
		{
			Component c=components[i];
			if(c instanceof JCheckBox)
			{
				JCheckBox ch=(JCheckBox)c;
				if(ch.isSelected())
				{
				    System.out.println(ch.getText());
					keywords.add(ch.getText());
				}
			}
		}
		Controller.setSelectedKeywords(keywords);
		return;
		
	}
	public DetailPanel getDetailPanel() 
	{
		return createDetailPanel("Input keywords that correspond to your course preferences.");
	}

}
