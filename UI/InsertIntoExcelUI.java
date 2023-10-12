package UI;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import Utils.GradeToGradePoint;
import Utils.InsertIntoExcel;

public class InsertIntoExcelUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
//					InsertIntoExcelUI frame = new InsertIntoExcelUI("1", "1", "1");
//					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InsertIntoExcelUI(String name, String leibie, String xuefen, String username) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 556, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("学    期");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 22, 84, 28);
		contentPane.add(lblNewLabel);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "2019年上学期", "2019年下学期", "2020年上学期", "2020年下学期",
				"2021年上学期", "2021年下学期", "2022年上学期", "2022年下学期", "2023年上学期", "2023年下学期", "2024年上学期", "2024年下学期",
				"2025年上学期", "2025年下学期", "2026年上学期", "2026年下学期", "2027年上学期", "2027年下学期", "2028年上学期", "2028年下学期" }));
		comboBox.setBounds(116, 26, 101, 23);
		contentPane.add(comboBox);

		JLabel lblNewLabel_1 = new JLabel("课程名称");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 71, 84, 28);
		contentPane.add(lblNewLabel_1);

		JLabel name_lable = new JLabel(name);
		name_lable.setFont(new Font("宋体", Font.PLAIN, 18));
		name_lable.setBounds(116, 71, 84, 28);
		contentPane.add(name_lable);

		JLabel lblNewLabel_1_1 = new JLabel("课程类别");
		lblNewLabel_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1_1.setBounds(10, 129, 84, 28);
		contentPane.add(lblNewLabel_1_1);

		JLabel leibie_lable = new JLabel(leibie);
		leibie_lable.setFont(new Font("宋体", Font.PLAIN, 18));
		leibie_lable.setBounds(116, 129, 84, 28);
		contentPane.add(leibie_lable);

		JLabel lblNewLabel_1_1_1 = new JLabel("课程学分");
		lblNewLabel_1_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1_1_1.setBounds(10, 183, 84, 28);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel xuefen_lable = new JLabel(xuefen);
		xuefen_lable.setFont(new Font("宋体", Font.PLAIN, 18));
		xuefen_lable.setBounds(116, 183, 84, 28);
		contentPane.add(xuefen_lable);

		JLabel lblNewLabel_1_1_1_1 = new JLabel("课程成绩");
		lblNewLabel_1_1_1_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1_1_1_1.setBounds(275, 22, 84, 28);
		contentPane.add(lblNewLabel_1_1_1_1);

		textField = new JTextField();
		textField.setBounds(381, 27, 66, 21);
		contentPane.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1_1_1_2 = new JLabel("课程绩点");
		lblNewLabel_1_1_1_2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1_1_1_2.setBounds(275, 71, 84, 28);
		contentPane.add(lblNewLabel_1_1_1_2);

		JLabel point_lable = new JLabel("");
		point_lable.setFont(new Font("宋体", Font.PLAIN, 18));
		point_lable.setBounds(381, 71, 84, 28);
		contentPane.add(point_lable);

		JButton btnNewButton = new JButton("确 定");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InsertIntoExcel.insertIntoExcel(comboBox.getSelectedItem().toString(), username, name, leibie, xuefen,
						textField.getText(), point_lable.getText(), 1);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(340, 195, 107, 42);
		contentPane.add(btnNewButton);

		JLabel score_right = new JLabel("✓");
		score_right.setForeground(new Color(255, 0, 0));
		score_right.setBounds(344, 58, 15, 15);
		contentPane.add(score_right);
		score_right.setVisible(false);

		JLabel score_false = new JLabel("请输入100以内的数字");
		score_false.setForeground(Color.RED);
		score_false.setBounds(340, 58, 121, 15);
		contentPane.add(score_false);
		score_false.setVisible(false);

		// 添加 DocumentListener 来监听文本变化事件
		textField.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateLabel();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateLabel();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// Plain text components do not fire these events
			}

			private void updateLabel() {
				String inputText = textField.getText(); // 获取文本字段的值
//						System.out.println(inputText);

				try {
					double number = Double.parseDouble(inputText);
					if (number <= 100) {
						score_right.setVisible(true);
						score_false.setVisible(false);
						point_lable.setText(GradeToGradePoint.GradeToGradePoint(inputText)); // 的文本

					} else if (number > 100) {
						score_right.setVisible(false);
						score_false.setVisible(true);
						point_lable.setText("");
					}

//							System.out.println("是数字：" + number);
				} catch (NumberFormatException ex) {
					System.out.println("不是数字");

					score_right.setVisible(false);
					score_false.setVisible(true);
					point_lable.setText("");
					if (inputText == null || inputText.isEmpty()) {
						point_lable.setText("");
						score_right.setVisible(false);
						score_false.setVisible(false);
					}
				}
			}
		});
	}
}
