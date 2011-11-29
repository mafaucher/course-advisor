package filters;

import main.Course;
import main.MainFrame;
import java.util.List;

/**
 * Determines what course group the course fits in, and
 * finds whether the student needs more credits for this group.
 * Needed core class:    1.00*score
 * Needed elective class: .80*score
 * Other elective class:  .60*score
 * No category:          0.0
 */
public class CreditFilter implements IFilter {

    @Override
    public double processScore(Course course, double score) {
        double creditsReq = 0.0;
        Course.Group group = MainFrame.getModel().dominantGroup(course.getGroups());
        // Core courses are always required
        switch(group)
        {
        case COMP_CORE:
        case ENCS_CORE:
            return score;
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
        // Electives are prioritised if they go towards an elective group
        if ( (creditsReq - creditsTaken(group)) > course.getCredits() )
            return 0.8 * score;
        
        // MATH electives, COMP courses can go towards general electives
        if (group == Course.Group.GEN_ELEC || group == Course.Group.MATH_ELEC || course.type().equals("COMP"))
        {
            creditsReq = MainFrame.getModel().getRecord().requirements.genElec;
            if ( (creditsReq - creditsTaken(Course.Group.GEN_ELEC)) > course.getCredits() )
                return 0.6 * score;
        }
        // Course is not required and cannot go towards general electives
        return 0.0;
    }

    private double creditsTaken(Course.Group group)
    {
        double credits = 0.0; 
        for (Course course : MainFrame.getModel().getAllCourses())
        {
            if ( MainFrame.getModel().dominantGroup(course.getGroups()) == group )
                credits += course.getCredits();
        }
        return credits;
    }
}
