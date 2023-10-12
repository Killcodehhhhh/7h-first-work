package Utils;

public class GradeToGradePoint {
	public static String GradeToGradePoint(String grade1) {
		double gradePoint = 0.0;
		if (grade1 != null && !grade1.isEmpty()) {
			double grade = Double.parseDouble(grade1);
			if (grade >= 90 && grade <= 100) {
				gradePoint = 4.0;
			}
			if (grade >= 85 && grade <= 89) {
				gradePoint = 3.7;
			}
			if (grade >= 82 && grade <= 84) {
				gradePoint = 3.3;
			}
			if (grade >= 78 && grade <= 81) {
				gradePoint = 3.0;
			}
			if (grade >= 75 && grade <= 77) {
				gradePoint = 2.7;
			}
			if (grade >= 72 && grade <= 74) {
				gradePoint = 2.3;
			}
			if (grade >= 68 && grade <= 71) {
				gradePoint = 2.0;
			}
			if (grade >= 64 && grade <= 67) {
				gradePoint = 1.5;
			}
			if (grade >= 60 && grade <= 63) {
				gradePoint = 1.0;
			}

//			System.out.println(gradePoint);
			return String.valueOf(gradePoint);
		} else {

			return "";
		}

	}

}
