package UI;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MessageUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessageUI frame = new MessageUI("熊大");
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
	public MessageUI(String username) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton = new JButton("录入成绩");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InputUI inputUI = new InputUI(username);
//				setVisible(false);
				inputUI.setVisible(true);
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton.setBounds(40, 24, 122, 36);
		contentPane.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("查看成绩单");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GradeUI gradeUI = new GradeUI(username);
//				setVisible(false);
				gradeUI.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_1.setBounds(213, 95, 151, 36);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("不及格成绩管理");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FailUI failUI = new FailUI(username);
				failUI.setVisible(true);
			}
		});
		btnNewButton_2.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_2.setBounds(213, 153, 161, 36);
		contentPane.add(btnNewButton_2);

		JButton btnNewButton_3 = new JButton("查看绩点");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GradePointUI gradePointUI = new GradePointUI(username);
				gradePointUI.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_3.setBounds(40, 95, 122, 36);
		contentPane.add(btnNewButton_3);

		JButton btnNewButton_3_1 = new JButton("查看学分");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CreditUI creditUI = new CreditUI(username);
				creditUI.setVisible(true);
			}
		});
		btnNewButton_3_1.setFont(new Font("宋体", Font.PLAIN, 18));
		btnNewButton_3_1.setBounds(40, 153, 122, 36);
		contentPane.add(btnNewButton_3_1);
	}

}
