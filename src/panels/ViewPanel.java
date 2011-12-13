package panels;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.Controller;

public abstract class ViewPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ViewPanel()
	{
		this(new BorderLayout());
	}
	public ViewPanel(LayoutManager layout)
	{
		super(layout);
		
	}
	protected JButton addButton(String name, ActionListener al, String layout)
	{
		JButton button=createButton(name, al);
		add(button, layout);		
		return button;
	}
	protected JButton createButton(String name, ActionListener al)
	{
		JButton button=new JButton(name);
		if(al==null)
			button.setEnabled(false);
		else button.addActionListener(al);		
		return button;
	}
	protected String getHead()
	{
		return new String("<html><body>");
	}
	protected void setTail(String str)
	{
		str=str.trim();
		str+="</html></body>";
	}
	public static DetailPanel createDetailPanel(String text)
	{
		
		ArrayList<String> errors=Controller.getErrors();
		if(errors.size()>0)
		{
			text+="<br>";
			text+="Errors:<br>";
			for(String err : errors)
				text+=err+"<br>";
		}
		errors.clear();
		return new DetailPanel(text);
	}
	public abstract DetailPanel getDetailPanel();
}
