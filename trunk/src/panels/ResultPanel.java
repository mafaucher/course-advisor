package panels;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;

import main.*;

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
		table=new MainTable(tableModel);
		table.setPreferredScrollableViewportSize(new Dimension(450, 350));
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        ActionListener al=new ActionListener()
        {
			public void actionPerformed(ActionEvent arg0) 
			{
				MainFrame.getFrame().setPanel(new PreferencePanel());
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
					   		.addComponent(scrollPane)
					   		.addComponent(backButton))
				);
				layout.setVerticalGroup(
				   layout.createSequentialGroup()
				   	  	.addComponent(scrollPane)
				      	.addComponent(backButton)
				);
	}
	public DetailPanel getDetailPanel() 
	{
		return createDetailPanel("The courses with highest score are most recommended for you<br>" +
				"Click on a course to get information about it");
	}
	private class MainTable extends JTable
	{
		private static final long serialVersionUID = 1L;
		private ResultTableModel model;
		public MainTable(ResultTableModel tableModel) 
		{
			super(tableModel);
			model=tableModel;
		}

		public void valueChanged(ListSelectionEvent e)
		{
			super.valueChanged(e);
			Course course=getSelectedCourse();
			String str=Controller.getCourseDetails(course);
			MainFrame.getFrame().setDetailPanel(ViewPanel.createDetailPanel(str));			
		}
		private Course getSelectedCourse()
		{
			int iRow=this.getSelectedRow();
			Course course=model.getCourse(iRow);
			return course;
		}
		
	}
}
