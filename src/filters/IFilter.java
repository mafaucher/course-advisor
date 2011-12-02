package filters;

import main.Course;

public interface IFilter 
{
	public double processScore(Course course, double score);
	public String getName();
	public String getDetails(Course course);
}
