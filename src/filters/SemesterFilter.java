package filters;

import main.Course;

/**
 * TODO: need to be able to control semester from the GUI
 * and make this available to this filter (currently fixed to 2)
 */
public class SemesterFilter implements IFilter {

    @Override
    public double processScore(Course course, double score) {
        int semester = 2;
        if ( course.getSemesters()[semester-1] )
        {
            return score;
        }
        return 0;
    }
	@Override
	public String getName() 
	{
		return "Semester Filter";
	}

	@Override
	public String getDetails(Course course) 
	{
		if (processScore(course, 1.00) == 0)
			return "This class is not offered this semester.";
		return null;
	}
}