package filters;

import main.Course;

public class PreferenceFilter implements IFilter {

	@Override
	public double processScore(Course course, double score) 
	{
		// TODO Auto-generated method stub
		return score;
	}
	@Override
	public String getName() 
	{
		return "Preference Filter";
	}

	@Override
	public String getDetails(Course course) 
	{
		return null;
	}
}