package Utils;

import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class NewExcel {
	public static boolean newExcel(String name) {
		try {
			// 创建一个新的Excel工作簿
			WritableWorkbook workbook = Workbook.createWorkbook(new File(name + "同学的成绩.xls"));

			// 创建一个工作表
			WritableSheet sheet = workbook.createSheet("课程表", 0);

			// 创建表头行
			Label label = new Label(0, 0, "学期");
			Label label1 = new Label(1, 0, "课程");
			Label label2 = new Label(2, 0, "课程类别");
			Label label5 = new Label(3, 0, "学分");
			Label label4 = new Label(4, 0, "分数");
			Label label3 = new Label(5, 0, "绩点");
			Label label6 = new Label(6, 0, "备注");

			// 将表头添加到工作表
			sheet.addCell(label);
			sheet.addCell(label1);
			sheet.addCell(label2);
			sheet.addCell(label3);
			sheet.addCell(label4);
			sheet.addCell(label5);
			sheet.addCell(label6);

			// 保存Excel文件
			workbook.write();
			workbook.close();
			System.out.println("Excel文件已创建成功！");
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public static void main(String[] args) {
		newExcel("1");
	}
}
