package panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import main.MainFrame;

public class LoginPanel extends ViewPanel 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nameField, passwordField;
	private JButton loginButton, exitButton;
	public LoginPanel()
	{
		ActionListener al=new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				MainFrame.getFrame().setPanel(new RecordPanel());
			}
			
		};
		nameField=new JTextField("", 16);
		passwordField=new JTextField("", 16);
		loginButton=createButton("Login", al);
		exitButton=createButton("Exit", null);
		
		GroupLayout layout=new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				   layout.createSequentialGroup()
				      	
				      	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				      			.addComponent(nameField)
						      	.addComponent(passwordField)
						      	.addGroup(layout.createSequentialGroup()
						      		.addComponent(loginButton)
						      		.addComponent(exitButton)))
				);
				layout.setVerticalGroup(
				   layout.createSequentialGroup()
				   	  	.addComponent(nameField)
				   	  	.addComponent(passwordField)
				   	  	.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
				    		  .addComponent(exitButton)
				    		  .addComponent(loginButton))				      
				);
	}
	@Override
	public DetailPanel getDetailPanel() 
	{
		String text="Please input your username and your password to access your record.";
		return createDetailPanel(text);
	}
	
}
