package panels;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import main.Course;

public class ResultTableModel extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Course> courses;
	private String[] columnNames={"Course", "Name", "Score", "Recommended?"};
	public ResultTableModel(ArrayList<Course> courses)
	{
		this.courses=courses;
	}
	@Override
	public int getColumnCount() 
	{
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public int getRowCount() 
	{
		// TODO Auto-generated method stub
		return courses.size();
	}
	public String getColumnName(int col) 
	{
	    return columnNames[col];
	}
	public boolean isCellEditable(int row, int col) 
    {
        return false;
    }
	@Override
	public Object getValueAt(int row, int col) 
	{
		Course course=courses.get(row);
		switch(col)
		{
		case 0:
			return course.getNumber();
		case 1:
			return course.getName();
		case 2:
			return course.score;
		case 3:
			return new String("lol");
		default:
			return null;
		}
	}

}
