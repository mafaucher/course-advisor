package panels;
import java.awt.Dimension;

import javax.swing.*;

import main.MainFrame;

public class ResultPanel extends ViewPanel 
{
	private static final long serialVersionUID = 1L;
	private ResultTableModel tableModel;
	private JButton backButton;
	private JTable table;
	public ResultPanel()
	{
		MainFrame.getModel().computeScores();
		tableModel=new ResultTableModel(MainFrame.getModel().getAllCourses());
		table=new JTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(400, 350));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        backButton=createButton("Back", null);
        GroupLayout layout=new GroupLayout(this);
		setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setAutoCreateContainerGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
					.addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
					   		.addComponent(scrollPane)
					   		.addComponent(backButton))
				);
				layout.setVerticalGroup(
				   layout.createSequentialGroup()
				   	  	.addComponent(scrollPane)
				      	.addComponent(backButton)
				);
	}
	@Override
	public DetailPanel getDetailPanel() 
	{
		// TODO Auto-generated method stub
		return createDetailPanel("Details about the results go here");
	}

}
