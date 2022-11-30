package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import library.borrow.inActionListener;

public class extension extends JFrame {
	Database db = new Database();
	private JButton btn1 = new JButton("대출");
	private JButton btn2 = new JButton("예약");
	private JButton btn3 = new JButton("연장");
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JLabel id = new JLabel("학번");
	private JLabel name = new JLabel("이름");
	private JTextField idtf = new JTextField(18);
	private JTextField nametf = new JTextField(18);
	private JLabel lb = new JLabel("연장가능 여부를 확인하고 표를 클릭하여 예약하세요.");
	private JButton exten = new JButton("연장");
	private JTextArea db_connect = new JTextArea("db연동");

	public extension() {
		setTitle("연장");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		c.setLayout(null);

		// 버튼 설정
		btn1.setLocation(1,0);
		btn2.setLocation(233,0);
		btn3.setLocation(466,0);
		btn1.setSize(233,100);
		btn2.setSize(233,100);
		btn3.setSize(233,100);
		btn1.setBackground(Color.white);
		btn2.setBackground(Color.white);
		btn3.setBackground(new Color(255, 204, 153));

		Font big_font = new Font("NanumSquare", Font.BOLD, 30); // 폰트 객체 생성
		btn1.setFont(big_font);
		btn2.setFont(big_font);
		btn3.setFont(big_font);

		// 학번, 이름 표시하는 패널 설정
		panel1.add(id);
		panel1.add(idtf);
		panel2.add(name);
		panel2.add(nametf);
		panel1.setSize(560, 61);
		panel1.setLocation(59, 115);
		panel2.setSize(560, 61);
		panel2.setLocation(59, 170);

		// 연장 방법 알려주는 라벨 설정
		lb.setHorizontalAlignment(NORMAL); // 라벨 가운데 정렬
		lb.setLocation(56,250);
		lb.setSize(588,34);

		Font plain_font = new Font("NanumSquare", Font.PLAIN, 25); // 폰트 객체 생성
		id.setFont(plain_font);
		name.setFont(plain_font);
		idtf.setFont(plain_font);
		nametf.setFont(plain_font);
		lb.setFont(plain_font);

		// 예약버튼
		exten.setSize(210,100);
		exten.setLocation(128 ,670);
		exten.setForeground(new Color(255,255,255));
		exten.setBackground(new Color(255,153,051));
		exten.setFont(big_font);

		// 홈버튼
		ImageIcon homeIcon = new ImageIcon("images/home.png");
		Image homeimg = homeIcon.getImage();
		homeimg = homeimg.getScaledInstance(52, 52,  java.awt.Image.SCALE_SMOOTH);
		homeIcon = new ImageIcon(homeimg);

		JButton btn_home = new JButton("Home", homeIcon);

		btn_home.setBackground(Color.gray);
		btn_home.setFont(big_font); // 폰트 설정
		btn_home.setForeground(new Color(255,255,255)); // 텍스트 색 설정
		btn_home.setSize(210, 100);
		btn_home.setLocation(363, 670);

		// 리스너 달기
		btn1.addActionListener(new inActionListener());
		btn2.addActionListener(new inActionListener());
		btn3.addActionListener(new inActionListener());
		btn_home.addActionListener(new inActionListener());
		
		// 컨테이너에 컴포넌트들 추가
		c.add(btn1);
		c.add(btn2);
		c.add(btn3);
		c.add(panel1);
		c.add(panel2);
		c.add(lb);
		c.add(exten);
		c.add(btn_home);
		c.add(db_connect);

		setSize(700, 800);
		setVisible(true);
	}

	class inActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("대출"))   {
				new borrow();
				dispose();
			}
			else if (b.getText().equals("예약")){
				new reservation();
				setVisible(false);
			}
			else if (b.getText().equals("연장")){
				new extension();
				setVisible(false);
			}
			else if (b.getText().equals("Home"))   {
				new library_main();
				db.logout();
				dispose();
			}
		}
	}

}
