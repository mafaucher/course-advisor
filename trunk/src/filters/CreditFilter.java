package filters;

import main.Course;
import main.MainFrame;

/**
 * Determines what course group the course fits in, and
 * finds whether the student needs more credits for this group.
 * Needed core class:    1.00*score
 * Needed elective class: .80*score
 * Other elective class:  .60*score
 * No category:          0.0
 */
public class CreditFilter implements IFilter {
	private static final double coreScore = 	1.00;
	private static final double electiveScore = 0.80;
	private static final double generalScore = 	0.60;
	
    @Override
    public double processScore(Course course, double score) {
        double creditsReq = 0.0;
        Course.Group group = MainFrame.getModel().dominantGroup(course.getGroups());
        // Core courses are always required
        switch(group)
        {
        case COMP_CORE:
        case ENCS_CORE:
            return coreScore*score;
        case COMP_ELEC:
            creditsReq = MainFrame.getModel().getRecord().requirements.compElec;
            break;
        case MATH_ELEC:
            creditsReq = MainFrame.getModel().getRecord().requirements.mathElec;
            break;
        case GAME_ELEC:
        case WEB_ELEC:
        case CSYS_ELEC:
        case SSYS_ELEC:
        case ISYS_ELEC:
        case ART_ELEC:
        case STAT_ELEC:
            creditsReq = MainFrame.getModel().getRecord().requirements.specialElec;
            break;
        }

        // Electives are prioritized if they go towards an elective group
        if ( (creditsReq - creditsTaken(group)) >= course.getCredits() )
            return electiveScore * score;
        
        // MATH electives, COMP courses can go towards general electives
        if (group == Course.Group.GEN_ELEC || group == Course.Group.MATH_ELEC || course.type().equals("COMP"))
        {
            creditsReq = MainFrame.getModel().getRecord().requirements.genElec;
            if ( (creditsReq - creditsTaken(Course.Group.GEN_ELEC)) >= course.getCredits() )
                return generalScore * score;
            System.out.println(course.getNumber());
            System.out.println(creditsReq);
            System.out.println(creditsTaken(Course.Group.GEN_ELEC));
            System.out.println(course.getCredits());
        }
        // Course is not required and cannot go towards general electives
        return 0.0;
    }

    private double creditsTaken(Course.Group group)
    {
        double credits = 0.0; 
        for (Course course : MainFrame.getModel().getAllCourses())
        {
            if ( course.wasTaken() && MainFrame.getModel().dominantGroup(course.getGroups()) == group )
                credits += course.getCredits();
        }
        return credits;
    }
    
	@Override
	public String getName() 
	{
		return "Credit Filter";
	}

	@Override
	public String getDetails(Course course) 
	{
		if (processScore(course, 1.00) > 0.0)
			return "This course is needed to satisfy a " + Course.groupToString(MainFrame.getModel().dominantGroup(course.getGroups())) + " requirement.";
		return null;
	}
}
