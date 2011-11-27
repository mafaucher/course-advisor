package filters;

import main.Course;

public interface IFilter 
{
	public float ProcessScore(Course course, float score);
}
