package UI;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import Utils.SelectGradePoint;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class GradePointUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GradePointUI frame = new GradePointUI("1");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GradePointUI(String username) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 544, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("总绩点");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(54, 55, 76, 37);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_2 = new JLabel("平均学分绩点");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(54, 182, 200, 37);
		contentPane.add(lblNewLabel_2);

		JLabel sum_label = new JLabel("");
		sum_label.setFont(new Font("宋体", Font.PLAIN, 18));
		sum_label.setBounds(161, 55, 98, 37);
		contentPane.add(sum_label);

		JLabel avg_label = new JLabel("");
		avg_label.setFont(new Font("宋体", Font.PLAIN, 18));
		avg_label.setBounds(161, 182, 98, 37);
		contentPane.add(avg_label);

		Object[] gradePointsArray = SelectGradePoint.selectGradePoint(username);
		// 从数组中取出总绩点和平均绩点
		double totalGradePoints = (double) gradePointsArray[0]; // 总绩点
		double averageGPA = (double) gradePointsArray[1]; // 平均绩点

		// 从数组中取出学分绩点（数组）
		double[] creditGradePoints = (double[]) gradePointsArray[2]; // 学分绩点数组

		// 现在你可以使用这些变量来操作相应的值
		sum_label.setText(String.valueOf(totalGradePoints));
		avg_label.setText(String.valueOf(averageGPA));

		// 创建一个 JTable
		JTable table = new JTable();

		// 创建一个默认的表格模型
		DefaultTableModel model = new DefaultTableModel();

		// 设置表头
		model.setColumnIdentifiers(new Object[] { "课程名称", "课程学分绩点" });

		try {
			// 打开现有的Excel文件
			Workbook existingWorkbook = Workbook.getWorkbook(new File(username + "同学的成绩.xls"));

			// 获取要插入数据的工作表
			Sheet sheet = existingWorkbook.getSheet(0); // 假设工作表位于索引0

			System.out.println(creditGradePoints.length + "---" + sheet.getRows());
			// 遍历Excel表格中的数据，并添加到表格模型中
			for (int row = 1; row <= sheet.getRows() - 1; row++) {
				Cell[] rowData = sheet.getRow(row);
				// 获取课程名称和对应的creditGradePoints值
				String courseName = rowData[1].getContents();
				double creditGradePoint = creditGradePoints[row - 1]; // 使用与行号对应的creditGradePoints值

				model.addRow(new Object[] { courseName, creditGradePoint });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		// 将模型设置给JTable
		table.setModel(model);

		// 创建一个滚动窗口，用于显示JTable
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(295, 66, 235, 187); // 设置合适的坐标和大小

		contentPane.add(scrollPane);

		JLabel lblNewLabel_1_1 = new JLabel("课程学分绩点");
		lblNewLabel_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(346, 19, 110, 37);
		contentPane.add(lblNewLabel_1_1);

	}
}
