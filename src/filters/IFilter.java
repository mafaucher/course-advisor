package filters;

import main.Course;

public interface IFilter 
{
	public float processScore(Course course, float score);
}
