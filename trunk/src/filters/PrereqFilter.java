package filters;

import main.Course;
import main.MainFrame;

/**
 * Binary filter, returns the score if prereqs are satisfied.
 * Sets score to 0 if course was taken or prereqs are not satisfied.
 */
public class PrereqFilter implements IFilter {

	@Override
	public double processScore(Course course, double score) {
        if (course.wasTaken())
            return 0;
	    for (Course prereq : course.getPrereqs())
	    {
	        if (!MainFrame.prereqSatisfied(prereq))
	        {
	            return 0;
	        }
	    }
	    return score;
	}
	
	public String missingPrereqs(Course course)
	{
		String result = "";
		for (Course prereq : course.getPrereqs())
		{
	        if (!MainFrame.prereqSatisfied(prereq))
	        {
	        	result += prereq.getNumber()+", ";
	        }
		}
		return result;
	}
	
	
	
	@Override
	public String getName() 
	{
		return "Prerequisites Filter";
	}

	@Override
	public String getDetails(Course course) 
	{
		if (processScore(course, 1.00) == 0)
			return "The following prerequisites have not been satisfied: "+missingPrereqs(course);
		return null;
	}
}
