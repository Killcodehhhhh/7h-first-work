package UI;
import java.awt.EventQueue;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class CreditUI extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CreditUI frame = new CreditUI("1");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CreditUI(String username) {
		// 读取Excel文件中的数据
		Map<String, Double> creditsByCategory = new HashMap<>();

		try {
			Workbook existingWorkbook = Workbook.getWorkbook(new File(username + "同学的成绩.xls"));
			Sheet sheet = existingWorkbook.getSheet(0); // 假设工作表位于索引0

			for (int row = 1; row < sheet.getRows(); row++) {
				Cell[] rowData = sheet.getRow(row);
				String courseCategory = rowData[2].getContents(); // 课程类别

				if (Double.valueOf(rowData[5].getContents()) > 0) {
					double credits = Double.parseDouble(rowData[3].getContents()); // 学分

					// 将学分累加到相应的课程类别中
					creditsByCategory.put(courseCategory,
							creditsByCategory.getOrDefault(courseCategory, 0.0) + credits);

				} else {
					// 将学分累加到相应的课程类别中
					creditsByCategory.put(courseCategory, creditsByCategory.getOrDefault(courseCategory, 0.0) + 0);

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 478, 380);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 创建一个 JTable 来显示已修学分
		JTable table1 = new JTable();
		DefaultTableModel model1 = new DefaultTableModel();
		model1.setColumnIdentifiers(new Object[] { "课程类别", "已修学分" });
		for (Map.Entry<String, Double> entry : creditsByCategory.entrySet()) {
			model1.addRow(new Object[] { entry.getKey(), entry.getValue() });
		}
		table1.setModel(model1);
		JScrollPane scrollPane1 = new JScrollPane(table1);
		scrollPane1.setBounds(10, 42, 235, 120);
		contentPane.add(scrollPane1);

		// 计算总学分
		double totalCredits = 0.0;
		for (Double credits : creditsByCategory.values()) {
			totalCredits += credits;
		}

		// 创建一个 JTable 来显示未修学分
		JTable table2 = new JTable();
		DefaultTableModel model2 = new DefaultTableModel();

		model2.setColumnIdentifiers(new Object[] { "课程类别", "未修学分" });

		for (Map.Entry<String, Double> entry : creditsByCategory.entrySet()) {
			if ("通识选修课".equals(entry.getKey())) {
				model2.addRow(new Object[] { entry.getKey(), 50.0 - entry.getValue() });
			}
			if ("通识必修课".equals(entry.getKey())) {
				model2.addRow(new Object[] { entry.getKey(), 30.0 - entry.getValue() });
			}
			if ("通识限选课".equals(entry.getKey())) {
				model2.addRow(new Object[] { entry.getKey(), 20.0 - entry.getValue() });
			}

		}

		table2.setModel(model2);
		JScrollPane scrollPane2 = new JScrollPane(table2);
		scrollPane2.setBounds(10, 180, 235, 120);
		contentPane.add(scrollPane2);
	}
}
