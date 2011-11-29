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
}