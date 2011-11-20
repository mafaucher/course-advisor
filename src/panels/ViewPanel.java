package panels;

import java.awt.*;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ViewPanel extends JPanel
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
}
