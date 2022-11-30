package library;
//dispose();오케이 버튼에서 이거 안되는듯?
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class library_main extends JFrame {
	private ImageIcon adminIcon = new ImageIcon("images/dgurogo.png");
	private ImageIcon backIcon = new ImageIcon("images/dgurogo2.png");
	private JLabel title = new JLabel("<HTML><body><center>동국대학교 중앙도서관<br>키오스크</center></body></HTML>");
	private MyDialog password;
	// 생성자

	public library_main() {
		setTitle("동국대학교 중앙도서관 키오스크");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setBackground(Color.WHITE);
		c.setLayout(null);

		// 이미지 불러오기
		Image adminimg = adminIcon.getImage();
		adminimg = adminimg.getScaledInstance(48, 48, java.awt.Image.SCALE_SMOOTH);
		adminIcon = new ImageIcon(adminimg);

		// 이미지 불러오기
		Image backimg = backIcon.getImage();
		backimg = backimg.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
		backIcon = new ImageIcon(backimg);
		JLabel back_img = new JLabel(backIcon);
		back_img.setSize(400, 400);
		back_img.setLocation(150, 50);

		// 버튼 생성
		JButton btn1 = new JButton("<HTML><body><center>로그인<br>(대출/연장/예약)</center></body></HTML>");
		btn1.setBackground(new Color(255, 153, 51));
		JButton btn2 = new JButton("반납");
		btn2.setBackground(new Color(255, 153, 102));
		JButton btn_manager = new JButton(adminIcon);

		// 버튼 크기 설정
		btn1.setSize(225, 225);
		btn2.setSize(225, 225);
		btn_manager.setSize(48, 48);

		// 버튼 레이아웃 설정
		btn1.setBorderPainted(false);
		btn2.setBorderPainted(false);
		btn_manager.setBorderPainted(false);

		// 버튼배경 투명 설정
		btn_manager.setContentAreaFilled(false);
		btn_manager.setOpaque(false);

		// 버튼 위치설정
		btn1.setLocation(97, 498);
		btn2.setLocation(378, 498);
		btn_manager.setLocation(603, 32);

		// 리스너 추가
		btn1.addActionListener(new inActionListener());
		btn2.addActionListener(new inActionListener());
		btn_manager.addActionListener(new inActionListener());

		// 폰트 설정
		Font t_font = new Font("NanumSquare", Font.BOLD, 40);
		Font btn1_font = new Font("NanumSquare", Font.BOLD, 25);
		Font btn2_font = new Font("NanumSquare", Font.BOLD, 25);

		title.setFont(t_font);
		title.setLocation(150, 200);
		title.setSize(410, 100);

		btn1.setFont(btn1_font);
		btn2.setFont(btn2_font);

		c.add(title);
		c.add(back_img);
		c.add(btn1);
		c.add(btn2);
		c.add(btn_manager);

		password = new MyDialog(this, "관리자 비밀번호");
		btn_manager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				password.setVisible(true);
			}
		});

		setSize(700, 800);
		setVisible(true);

	}

	class background extends JPanel {
		private ImageIcon back_Image = new ImageIcon("images/back_img.png");
		private Image img = back_Image.getImage(); // 이미지 객체

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
		}
	}

	class MyDialog extends JDialog {
		private JLabel la = new JLabel("비밀번호를 입력하세요.");
		private JTextField tf = new JTextField(15);
		private JButton okButton = new JButton("확인");

		public MyDialog(JFrame frame, String title) {
			super(frame, title);
			setLayout(new FlowLayout());
			add(la);
			add(tf);
			add(okButton);
			setSize(300, 100);
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String input = tf.getText();
					// setVisible(false);
					String T_password = "admin";
					if (input.equals(T_password)) {
						new AdminPage();
						dispose();
					} else {
						JOptionPane.showMessageDialog(null, "비밀번호를 다시 입력해주세요.", "Message", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
	}

	class inActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("<HTML><body><center>로그인<br>(대출/연장/예약)</center></body></HTML>")) {
				new login();
				dispose();
			} else if (b.getText().equals("반납")) {
				new returnBook();
				setVisible(false);
			}
		}
	}

	public static void main(String[] args) {
		new library_main();
		new Database();
	}
}
