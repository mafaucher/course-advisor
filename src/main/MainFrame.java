package main;

import java.awt.*;

import javax.swing.*;

import panels.*;

public class MainFrame extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static MainFrame frame;
	private MainFrame(String name)
	{
		super(name);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(1000,700);
		setLayout(new FlowLayout(FlowLayout.CENTER));
		setPanel(new LoginPanel());
		setVisible(true);
	}
	public void setPanel(ViewPanel panel)
	{
		Container content=getContentPane();
		content.removeAll();
		content.add(panel, BorderLayout.CENTER);
	}
	public static MainFrame getFrame()
	{
		return frame;
	}
	public static void main(String[] args)
	{
		frame=new MainFrame("Some Expert System");
	}
}
