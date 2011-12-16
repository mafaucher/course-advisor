package panels;

import java.awt.event.*;
import javax.swing.*;

import main.Controller;
import main.MainFrame;

public class LoginPanel extends ViewPanel 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField idField; //, passwordField;
	private JButton loginButton, exitButton;

	public LoginPanel() 
	{
		ActionListener al = new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String id=idField.getText();
				try
				{
					MainFrame.getModel().loadStudentRecord(Long.valueOf(id));
					if(Controller.getRecord()==null)
						 Controller.reportError("There is no user with the id number "+id);
				}
				catch(Exception e)
				{
					Controller.reportError("Please input a 7 digit number with no alpha characters");
				}
				if(Controller.getRecord()!=null)
					MainFrame.getFrame().setPanel(new RecordPanel());
				else MainFrame.getFrame().setPanel(new LoginPanel());
			}
		};
		idField = new JTextField("", 16);
		// passwordField=new JTextField(16);
		loginButton = createButton("Login", al);
		exitButton = createButton("Exit", null);

		GroupLayout layout = new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(layout.createSequentialGroup()

		.addGroup(
		        layout.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addComponent(idField)
						// .addComponent(passwordField)
						.addGroup(
								layout.createSequentialGroup()
										.addComponent(loginButton)
										.addComponent(exitButton))));
		layout.setVerticalGroup(layout
				.createSequentialGroup()
				.addComponent(idField)
				// .addComponent(passwordField)
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.BASELINE)
								.addComponent(exitButton)
								.addComponent(loginButton)));
		
	}
	@Override
	public DetailPanel getDetailPanel() 
	{
		String text="Please input your student ID to access your record.";
		return createDetailPanel(text);
	}
	
}
