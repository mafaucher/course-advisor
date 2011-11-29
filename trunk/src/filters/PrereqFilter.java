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
        if course.wasTaken()
            return 0;
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
