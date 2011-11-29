package filters;

import main.Course;
import main.MainFrame;

/**
 * Binary filter, sets score to 0 for 400 level classes
 * if all 200 level classes of the same type were not completed.
 */
public class LevelFilter implements IFilter {

	@Override
	public double processScore(Course course, double score) {
	    switch (course.level())
	    {
	    case 1:
	    case 2:
	    case 3:
	        return score;
	    case 4:
	        return ( testQualification(course) ? score : 0.0 );
	    case 0:
	    default:
	        return 0.0;
	        // TODO: proper error system
	    }
	}
	
	// Check if student has taken all 200 level core COMP courses
	private boolean testQualification(Course testedCourse)
	{
	    for (Course course : MainFrame.getModel().getAllCourses())
	    {
	        if ( course.level() == 2 && !course.wasTaken()
	                && MainFrame.getModel().dominantGroup(course.getGroups()).equals(Course.Group.COMP_CORE) )
	        {
                return false;
	        }
	    }
	    return true;
	}
}
