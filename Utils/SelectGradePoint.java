package Utils;


import java.io.File;
import java.io.IOException;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class SelectGradePoint {
	public static void main(String[] args) {

	}

	public static Object[] selectGradePoint(String username) {

		String excelFileName = username + "同学的成绩.xls";
		RoundDouble roundDouble = new RoundDouble();

		try {
			Workbook workbook = Workbook.getWorkbook(new File(excelFileName));
			Sheet sheet = workbook.getSheet(0);
			// 创建一个数组用于存储课程的学分绩点
			double[] creditGradePoints = new double[sheet.getRows() - 1]; // 减去表头行
			double totalGradePoints = 0.0; // 总绩点
			double totalCredits = 0.0; // 总学分
			double totalcreditGradePoints = 0.0;// 学分绩点之和
			// 遍历表格数据
			for (int rowIndex = 1; rowIndex < sheet.getRows(); rowIndex++) {
				Cell[] dataCells = sheet.getRow(rowIndex);

				// 从表格中获取相应的数据
				double credit = Double.parseDouble(dataCells[3].getContents()); // 学分
				double grade = Double.parseDouble(dataCells[4].getContents()); // 分数

				System.out.println(credit + "****" + grade);
				// 计算课程学分绩点
				double gradePoint = Double.parseDouble(dataCells[5].getContents()); // 绩点

				// 计算总绩点和总学分
				totalGradePoints += gradePoint;
				totalCredits += credit;
				totalcreditGradePoints += credit * gradePoint;
				// 将课程学分绩点添加到数组中
				creditGradePoints[rowIndex - 1] = credit * gradePoint;
				System.out.println(totalGradePoints + "---" + totalCredits + "--- " + credit * gradePoint);
			}

			// 计算平均绩点
			double averageGPA = totalcreditGradePoints / totalCredits;

			System.out.println("总绩点: " + totalGradePoints);
			System.out.println("平均绩点: " + averageGPA);
			System.out.println("总学分绩点：" + totalcreditGradePoints);
			System.out.println("学分：" + totalCredits);
//			System.out.println();
			workbook.close();

			// 创建一个数组用于存储总绩点、平均绩点和学分绩点
			Object[] gradePointsArray = new Object[] { roundDouble.roundDouble(totalGradePoints),
					roundDouble.roundDouble(averageGPA), creditGradePoints };

			// 现在gradePointsArray数组中包含了总绩点、平均绩点和学分绩点

			return gradePointsArray;
		} catch (IOException | BiffException e) {
			e.printStackTrace();
			return null;
		}

	}

}
