package filters;

import main.Course;

public interface IFilter 
{
	public double processScore(Course course, double score);
}
