package UI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class FailUI extends JFrame {

	private JPanel contentPane;
	private JTable table;
	String courseName = null;
	String courseCategory = null;
	String credit = null;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FailUI frame = new FailUI("熊二");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FailUI(String username) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 544, 300);
		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
//		contentPane.setLayout(new BorderLayout(0, 0));

		// 创建表格
		table = new JTable();
//		contentPane.add(new JScrollPane(table), BorderLayout.CENTER);

		// 创建表格模型
		DefaultTableModel model = new DefaultTableModel();

		// 添加列名
		model.addColumn("学期");
		model.addColumn("课程");
		model.addColumn("课程类别");
		model.addColumn("学分");
		model.addColumn("分数");
		model.addColumn("绩点");
		model.addColumn("备注");

		// 读取Excel文件并筛选不及格成绩
		try {
			Workbook workbook = Workbook.getWorkbook(new File(username + "同学的成绩.xls"));
			Sheet sheet = workbook.getSheet(0); // 假设工作表位于索引0

			for (int row = 1; row < sheet.getRows(); row++) {
				Cell[] rowData = sheet.getRow(row);

				String semester = rowData[0].getContents();
				String courseName = rowData[1].getContents();
				String courseCategory = rowData[2].getContents();
				String credit = rowData[3].getContents();
				String score = rowData[4].getContents();
				String gradePoint = rowData[5].getContents();
				String remark = rowData[6].getContents();

				// 这里可以根据你的不及格条件来筛选数据
				// 假设你的不及格条件是分数低于60
				double grade = Double.parseDouble(score);
				if (grade < 60) {
					model.addRow(
							new Object[] { semester, courseName, courseCategory, credit, score, gradePoint, remark });
				}
			}

			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		contentPane.setLayout(null);

		table.setModel(model);
		// 创建一个滚动窗口，用于显示JTable
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(65, 5, 400, 200);
		scrollPane.setPreferredSize(new Dimension(400, 200));

		contentPane.add(scrollPane);

		JButton btnNewButton_1 = new JButton("录入补考成绩");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (courseName != null) {
					InsertIntoExcelUI insertIntoExcelUI = new InsertIntoExcelUI(courseName, courseCategory, credit,
							username);
					insertIntoExcelUI.setVisible(true);

				} else {
					System.out.println("请选择");
				}
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_1.setBounds(281, 222, 184, 31);
		contentPane.add(btnNewButton_1);

		// 添加表格行选择监听器
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int selectedRow = table.getSelectedRow();
					if (selectedRow != -1) { // 如果有行被选中
						// 获取选中行的值
						courseName = table.getValueAt(selectedRow, 1).toString();
						courseCategory = table.getValueAt(selectedRow, 2).toString();
						credit = table.getValueAt(selectedRow, 3).toString();

						System.out.println("课程: " + courseName);
						System.out.println("课程类别: " + courseCategory);
						System.out.println("学分: " + credit);

					}
				}
			}
		});

	}
}
