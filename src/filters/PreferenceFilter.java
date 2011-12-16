package filters;

import java.util.List;

import main.Course;
import main.MainFrame;

/*
 * If all keywords match: 1.00
 * If no keywords match:  0.80
 */
public class PreferenceFilter implements IFilter {
    private static final double matchScore =    1.00;
    private static final double mismatchScore = 0.80;
    
    @Override
	public double processScore(Course course, double score) 
	{
	    int totalNumber = course.getKeywords().size();
	    int matchNumber = countMatchingKeywords(course.getKeywords());

	    double modifier = (((double)matchNumber)/((double)totalNumber))*(matchScore - mismatchScore) + mismatchScore;
	    return (modifier*score);
	}
    
    private int countMatchingKeywords(List<String> keywords)
    {
        int matchNumber = 0;
        for (String keyword : keywords)
        {
            for (String preference : MainFrame.getModel().getSelectedKeywords())
            {
                if (keyword.equals(preference))
                {
                    matchNumber++;
                    break;
                }
            }
        }
        return matchNumber;
    }
    
	@Override
	public String getName() 
	{
		return "Preference Filter";
	}

	@Override
	public String getDetails(Course course) 
	{
	    return (countMatchingKeywords(course.getKeywords()) + " out of " + course.getKeywords().size() + " keywords match your preferences.");
	}
}