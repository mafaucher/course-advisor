package filters;

import main.Course;
import main.MainFrame;

/**
 * Binary filter, sets score to 0 if prereqs not satisfied,
 * Returns score if prereqs are satisfied.
 */
public class PrereqFilter implements IFilter {

	@Override
	public double processScore(Course course, double score) {
	    for (Course prereq : course.getPrereqs())
	    {
	        if (!prereq.wasTaken())
	        {
	            if ( !MainFrame.getModel().getRecord().isRegistered(prereq) )
	                return 0;
	        }
	    }
	    return score;
	}
}