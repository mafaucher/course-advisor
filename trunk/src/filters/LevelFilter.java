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
	    switch (level(course))
	    {
	    case 1:
	    case 2:
	    case 3:
	        return score;
	    case 4:
	        return (testQualification(course) ? score : 0.0);
	    case 0:
	    default:
	        return 0.0;
	        // TODO: proper error system
	    }
	}
	
	// Check if student has taken all 200 level classes for the same type (COMP, MAST...)
	private boolean testQualification(Course testedCourse)
	{
	    for (Course course : MainFrame.getModel().getAllCourses())
	    {
	        // Check if this class is necessary
	        if (type(course).equals(type(testedCourse)) && level(course) == 2)
	        {
	            if (!course.wasTaken())
	                return false;
	        }
	    }
	    return true;
	}
	
    public String type(Course course)
    {
        return course.getNumber().substring(0, 4);
    }
    
    // TODO: Proper error system
    public int level(Course course)
    {
        try
        {
            return (Integer.valueOf(course.getNumber().charAt(5)));
        }
        catch (NumberFormatException e)
        {
            System.out.println(e);
        }
        return 0;
    }
	
}
