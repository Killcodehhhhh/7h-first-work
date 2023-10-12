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

public class InputUI extends JFrame {

	private JPanel contentPane;
	private JTextField name_textField;
	private JTextField xuefen_textField;
	private JTextField score_textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InputUI frame = new InputUI("1");
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
	public InputUI(String username) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 414, 324);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("课程名称");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel.setBounds(10, 69, 81, 29);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("课程类别");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_1.setBounds(10, 116, 81, 29);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("课程学分");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_2.setBounds(10, 161, 81, 29);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("课程成绩");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_3.setBounds(10, 202, 81, 29);
		contentPane.add(lblNewLabel_3);

		JLabel lblNewLabel_4 = new JLabel("课程绩点");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(10, 248, 81, 29);
		contentPane.add(lblNewLabel_4);

		JLabel gradePoint_label = new JLabel("");
		gradePoint_label.setFont(new Font("宋体", Font.PLAIN, 18));
		gradePoint_label.setBounds(120, 248, 81, 29);
		contentPane.add(gradePoint_label);

		name_textField = new JTextField();
		name_textField.setBounds(112, 74, 105, 21);
		contentPane.add(name_textField);
		name_textField.setColumns(10);

		xuefen_textField = new JTextField();
		xuefen_textField.setColumns(10);
		xuefen_textField.setBounds(112, 161, 105, 21);
		contentPane.add(xuefen_textField);

		score_textField = new JTextField();
		score_textField.setColumns(10);
		score_textField.setBounds(112, 207, 105, 21);
		contentPane.add(score_textField);

		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "通识必修课", "通识选修课", "通识限选课" }));
		comboBox.setBounds(112, 120, 105, 23);
		contentPane.add(comboBox);
		JComboBox comboBox_xueqi = new JComboBox();
		comboBox_xueqi.setModel(new DefaultComboBoxModel(new String[] { "2019年上学期", "2019年下学期", "2020年上学期", "2020年下学期",
				"2021年上学期", "2021年下学期", "2022年上学期", "2022年下学期", "2023年上学期", "2023年下学期", "2024年上学期", "2024年下学期",
				"2025年上学期", "2025年下学期", "2026年上学期", "2026年下学期", "2027年上学期", "2027年下学期", "2028年上学期", "2028年下学期" }));
		comboBox_xueqi.setBounds(112, 31, 105, 23);
		contentPane.add(comboBox_xueqi);
		JButton btnNewButton = new JButton("录 入");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int flag = InsertIntoExcel.insertIntoExcel(comboBox_xueqi.getSelectedItem().toString(), username,
						name_textField.getText(), comboBox.getSelectedItem().toString(), xuefen_textField.getText(),
						score_textField.getText(), gradePoint_label.getText(), 0);
				if (flag == 1) {
					name_textField.setText("");
					score_textField.setText("");
					xuefen_textField.setText("");
				}

			}
		});
		btnNewButton.setBounds(246, 248, 105, 29);
		contentPane.add(btnNewButton);

		JLabel score_right = new JLabel("✓");
		score_right.setForeground(new Color(255, 0, 0));
		score_right.setBounds(227, 210, 15, 15);
		contentPane.add(score_right);
		score_right.setVisible(false);

		JLabel score_false = new JLabel("请输入100以内的数字");
		score_false.setForeground(Color.RED);
		score_false.setBounds(230, 210, 121, 15);
		contentPane.add(score_false);
		score_false.setVisible(false);

		JLabel xuefen_false = new JLabel("请输入5以内的数字");
		xuefen_false.setForeground(Color.RED);
		xuefen_false.setBounds(227, 169, 121, 15);
		contentPane.add(xuefen_false);
		xuefen_false.setVisible(false);

		JLabel xuefen_right = new JLabel("✓");
		xuefen_right.setForeground(Color.RED);
		xuefen_right.setBounds(227, 169, 15, 15);
		contentPane.add(xuefen_right);

		JLabel lblNewLabel_5 = new JLabel("学    期");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 18));
		lblNewLabel_5.setBounds(10, 27, 81, 29);
		contentPane.add(lblNewLabel_5);

		xuefen_right.setVisible(false);

		// 添加 ActionListener 来监听文本字段的变化
		score_textField.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

			}
		});

		// 添加 DocumentListener 来监听文本变化事件
		score_textField.getDocument().addDocumentListener(new DocumentListener() {
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
				String inputText = score_textField.getText(); // 获取文本字段的值
//				System.out.println(inputText);

				try {
					double number = Double.parseDouble(inputText);
					if (number <= 100) {
						score_right.setVisible(true);
						score_false.setVisible(false);
						gradePoint_label.setText(GradeToGradePoint.GradeToGradePoint(inputText)); // 的文本

					} else if (number > 100) {
						score_right.setVisible(false);
						score_false.setVisible(true);
						gradePoint_label.setText("");
					}

//					System.out.println("是数字：" + number);
				} catch (NumberFormatException ex) {
					System.out.println("不是数字");

					score_right.setVisible(false);
					score_false.setVisible(true);
					gradePoint_label.setText("");
					if (inputText == null || inputText.isEmpty()) {
						gradePoint_label.setText("");
						score_right.setVisible(false);
						score_false.setVisible(false);
					}
				}
			}
		});

		// 添加 DocumentListener 来监听文本变化事件
		xuefen_textField.getDocument().addDocumentListener(new DocumentListener() {
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
				String inputText = xuefen_textField.getText(); // 获取文本字段的值
//						System.out.println(inputText);

				try {
					double number = Double.parseDouble(inputText);
					if (number <= 5) {
						xuefen_right.setVisible(true);
						xuefen_false.setVisible(false);
					} else {
						xuefen_right.setVisible(false);
						xuefen_false.setVisible(true);
					}

//							System.out.println("是数字：" + number);
				} catch (NumberFormatException ex) {
//							System.out.println("不是数字");
					xuefen_right.setVisible(false);
					xuefen_false.setVisible(true);
					if (inputText == null || inputText.isEmpty()) {
						xuefen_right.setVisible(false);
						xuefen_false.setVisible(false);
					}
				}
			}
		});
	}
}
