package UI;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class GradeUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GradeUI frame = new GradeUI("1");
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
	public GradeUI(String username) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JTable table = new JTable();
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane);
		// 设置 JScrollPane 的首选大小（高度）
		scrollPane.setPreferredSize(new Dimension(400, 200));
		// 指定Excel文件的文件名
		String excelFileName = username + "同学的成绩.xls";

		try {
			Workbook workbook = Workbook.getWorkbook(new File(excelFileName));
			Sheet sheet = workbook.getSheet(0);

			DefaultTableModel model = new DefaultTableModel();

			// 添加表头
			Cell[] headerCells = sheet.getRow(0);
			for (Cell cell : headerCells) {
				model.addColumn(cell.getContents());
				System.out.println(cell.getContents());
			}

			// 添加数据行
			for (int rowIndex = 1; rowIndex < sheet.getRows(); rowIndex++) {
				Cell[] dataCells = sheet.getRow(rowIndex);
				Object[] rowData = new Object[dataCells.length];
				for (int cellIndex = 0; cellIndex < dataCells.length; cellIndex++) {
					rowData[cellIndex] = dataCells[cellIndex].getContents();
				}
				model.addRow(rowData);
			}

			table.setModel(model);

			workbook.close();

		} catch (IOException | BiffException e) {
			e.printStackTrace();
		}

		setContentPane(contentPane);
	}

}
