package Utils;


import java.io.File;

import UI.TanChuang;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class InsertIntoExcel {
	public static int insertIntoExcel(String xueqi, String username, String name, String leibie, String xuefen,
			String grade, String gradePoind, int flag) {
		try {
			// 打开现有的Excel文件
			Workbook existingWorkbook = Workbook.getWorkbook(new File(username + "同学的成绩.xls"));

			// 创建一个可写的副本
			WorkbookSettings settings = new WorkbookSettings();
			WritableWorkbook workbook = Workbook.createWorkbook(new File(username + "同学的成绩.xls"), existingWorkbook,
					settings);

			// 获取要插入数据的工作表
			WritableSheet sheet = workbook.getSheet(0); // 假设工作表位于索引0

			Label xueqiValue = new Label(0, sheet.getRows(), xueqi);
			Label nameValue = new Label(1, sheet.getRows(), name);
			Label leibieValue = new Label(2, sheet.getRows(), leibie);
			Label xuefenValue = new Label(3, sheet.getRows(), xuefen);
			Label gradeValue = new Label(4, sheet.getRows(), grade);
			Label gradePointValue = new Label(5, sheet.getRows(), gradePoind);

			String beizhu = "及格";
			if (flag == 1) {
				beizhu = "补考";
			}
			if (Integer.parseInt(grade) < 60) {
				beizhu = "不及格";
			}
			Label beizhuValue = new Label(6, sheet.getRows(), beizhu);

			sheet.addCell(xueqiValue);
			sheet.addCell(nameValue);
			sheet.addCell(leibieValue);
			sheet.addCell(xuefenValue);
			sheet.addCell(gradeValue);
			sheet.addCell(gradePointValue);
			sheet.addCell(beizhuValue);

			// 保存Excel文件
			workbook.write();
			workbook.close();
			System.out.println("数据已插入到现有的Excel文件中。");
			TanChuang tanChuang = new TanChuang("录入Excel成功！");
			tanChuang.setVisible(true);
			return 1;

		} catch (Exception e) {
			e.printStackTrace();
			TanChuang tanChuang = new TanChuang("录入Excel失败，请重新录入！");
			tanChuang.setVisible(true);
			return 0;

		}
	}

	public static void main(String[] args) {
//		insertIntoExcel("1j", "1", "1", "1", "1", "1", "1", "1", 1);
	}
}
