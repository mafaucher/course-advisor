package filters;

import main.Course;
import main.MainFrame;

public class GradeFilter implements IFilter {

	@Override
	public double processScore(Course course, double score) {
		double gpa = MainFrame.getModel().getRecord().getGpa();
		double mult = 1.0;
		if (gpa < 2.5)
		{
			if (course.level() == 4)
				mult = 0.2;
			else if (course.level() == 3)
				mult = 0.6;
		}
		else if (gpa < 3)
		{
			if (course.level() == 4)
				mult = 0.5;
		}
		return mult*score;
	}

	@Override
	public String getName() {
		return "Grade Filter";
	}

	@Override
	public String getDetails(Course course) {
		if (processScore(course, 1.00) < 1.00)
			return "Your GPA is too low, it is unadvised to take "+course.level()+"00 level courses.";
		return null;
	}

}