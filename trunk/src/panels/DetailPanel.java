package panels;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JScrollPane;

import main.Controller;

public class DetailPanel extends ViewPanel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel textLabel;
	public DetailPanel(String text)
	{
		textLabel=new JLabel("Details");
		JScrollPane sp=new JScrollPane(textLabel);
		setText("Details:<br>"+text);
		Dimension d=new Dimension(500, 300);
		sp.setPreferredSize(d);
		//sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//VERTICAL_SCROLLBAR_AS_NEEDED
		add(sp);
	}
	public void setText(String text)
	{
		textLabel.setText(Controller.embedHtml(text));
	}
	public DetailPanel getDetailPanel() 
	{
		return null;
	}
}
