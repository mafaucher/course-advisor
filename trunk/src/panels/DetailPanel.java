package panels;

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
		textLabel=new JLabel("WTF");
		add(new JScrollPane(textLabel));
		setText("Details:<br>"+text);
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
