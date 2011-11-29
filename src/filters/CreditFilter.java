package filters;

import main.Course;
import main.MainFrame;

/**
 * Determines what course group the course fits in, and
 * finds whether the student needs more credits for this group.
 * Needed core class:    1.00*score
 * Needed elective class: .80*score
 * Other elective class:  .60*score
 */
public class CreditFilter implements IFilter {

    @Override
    public double processScore(Course course, double score) {
        return 0;
    }

    // Return the Group which corresponds to the studentRecord option
    private Course.Group dominantGroup(List<Course.Group> groups)
    {
        Course.Group dominantGroup = Course.Group.NONE;
        if (groups.size() == 1)
        {
            return groups.get(1);
        }
        else if (groups.size() > 1)
        {
            Course.Group studentGroup = Course.asGroup( MainFrame.getModel().getRecord().getOption() )
            for (Course.Group group : groups)
            {
                if (studentGroup == group)
                    return group;
            }
        }
        return Course.Group.NONE;
    }
}
