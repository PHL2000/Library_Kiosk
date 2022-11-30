package library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class login extends JFrame {
	private JLabel title = new JLabel("동국대학교 중앙도서관 키오스크");
	private JLabel StudentNumber = new JLabel("학번");
	private JTextField input_sn = new JTextField(30);
	private JLabel StudentPassword = new JLabel("비밀번호");
	private JTextField input_sp = new JTextField(30);
	private JButton btn_rogin = new JButton("로그인");
	private JButton btn_home = new JButton("홈");
	private JButton keyborad_sn = new JButton("입력1");
	private JButton keyborad_sp = new JButton("입력2");

	public login() {
		setTitle("로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container con = getContentPane();
		con.setBackground(new Color(245, 245, 245));
		con.setLayout(null);

		// 폰트
		Font font_all = new Font("NanumSquare", Font.BOLD, 25);

		// 상단 라벨
		JLabel label = new JLabel("로그인");
		label.setSize(700, 100);
		label.setLocation(0, 0);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setOpaque(true);
		label.setBackground(new Color(255, 204, 153));
		
		Font big_font = new Font("NanumSquare", Font.BOLD, 45);
		label.setFont(big_font);

		// 라벨 사이즈 및 폰트 지정
		StudentNumber.setSize(90, 30);
		StudentNumber.setFont(font_all);
		StudentPassword.setSize(180, 30);
		StudentPassword.setFont(font_all);
		input_sn.setSize(410, 62);
		input_sp.setSize(410, 62);

		// 위치 설정
		StudentNumber.setLocation(45, 265);
		StudentPassword.setLocation(25, 365);
		input_sn.setLocation(142, 250);
		input_sp.setLocation(142, 350);

		//키보드 버튼 설정(사이즈, 위치, 색, 레이아웃, 글꼴)
		keyborad_sn.setSize(110,62);
		keyborad_sp.setSize(110,62);

		keyborad_sn.setLocation(560, 250);
		keyborad_sp.setLocation(560, 350);

		keyborad_sn.setBackground(new Color(255, 153, 102));
		keyborad_sp.setBackground(new Color(255, 153, 102));

		keyborad_sn.setBorderPainted(false);
		keyborad_sp.setBorderPainted(false);

		keyborad_sn.setFont(font_all);
		keyborad_sp.setFont(font_all);

		keyborad_sn.setForeground(new Color(255,255,255));
		keyborad_sp.setForeground(new Color(255,255,255));

		// 로그인 버튼
		Font font_rogin = new Font("NanumSquare", Font.BOLD, 20);
		btn_rogin.setBackground(new Color(255, 153, 51));
		btn_rogin.setFont(font_rogin);
		btn_rogin.setForeground(new Color(255,255,255));
		btn_rogin.setBorderPainted(false);
		btn_rogin.setSize(410, 62);
		btn_rogin.setLocation(142, 465);

		//홈버튼
		ImageIcon homeIcon = new ImageIcon("images/home.png");
		Image homeimg = homeIcon.getImage();
		homeimg = homeimg.getScaledInstance(52, 52,  java.awt.Image.SCALE_SMOOTH);
		homeIcon = new ImageIcon(homeimg);
		btn_home = new JButton("HOME", homeIcon);
		btn_home.setBackground(new Color(204, 204, 204));
		btn_home.setFont(font_all);
		btn_home.setForeground(new Color(255,255,255));
		btn_home.setBorderPainted(false);
		btn_home.setSize(210, 100);
		btn_home.setLocation(245, 640);

		// 리스너 추가
		btn_rogin.addActionListener(new rogin_ActionListener());
		btn_home.addActionListener(new inActionListener());
		keyborad_sn.addActionListener(new keyboardActionListener());
		keyborad_sp.addActionListener(new keyboardActionListener());

		// 추가
		con.add(label);
		con.add(StudentNumber);
		con.add(input_sn);
		con.add(StudentPassword);
		con.add(input_sp);
		con.add(keyborad_sn);
		con.add(keyborad_sp);
		con.add(btn_rogin);
		con.add(btn_rogin);
		con.add(btn_home);

		setSize(700, 800);
		setVisible(true);
	}
	
	class rogin_ActionListener implements ActionListener {		
		Database db = new Database();
		public void actionPerformed(ActionEvent e) {
			
			String id = input_sn.getText().trim();
			String pw = input_sp.getText().trim();
			
			if(id.equals("") || pw.equals("")) {
				JOptionPane.showMessageDialog(null, "아이디와 비밀번호 모두 입력해주세요", "로그인 실패", JOptionPane.ERROR_MESSAGE);
				System.out.println("로그인 실패 > 로그인 정보 미입력");
			}
			
			else if(id != null && pw != null) {
				if(db.logincheck(id, pw) == true) {	//이 부분이 데이터베이스에 접속해 로그인 정보를 확인하는 부분이다.
					System.out.println("로그인 성공");
					JOptionPane.showMessageDialog(null, "로그인에 성공하였습니다");
					new borrow();
					setVisible(false);
					// 대출, 예약, 연장 총 화면으로 넘어가기
					
				} else {
					System.out.println("로그인 실패 > 로그인 정보 불일치");
					JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다");
				}
            }
		}
	}
	
	class inActionListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();				
			if (b.getText().equals("홈"))	{
				new library_main();
				dispose();
			}
		}
	}
	
	class keyboardActionListener implements ActionListener {		
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			if (b.getText().equals("입력1"))	{
				new keyBorad1();
				
			}
			else if (b.getText().equals("입력2"))	{
				new keyBorad2();
			}
		}
	}

	class keyBorad1 extends JFrame {
		private JPanel p1 = new JPanel();
		private JPanel p2_KEY = new JPanel();
		public JTextArea input = new JTextArea();
		private JButton[] btn_key = new JButton[40];
		private String[] key = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", " q", "w", "e", "r", "t", "y", "u", "i",
				"o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m", "shift_A", "특수기호",
				"<HTML><body><center>모두<br>지우기</center></body></HTML>", "입력"};
		private String[] shift_key = {" Q", "W", "E", "R", "T", "Y",
				"U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M",
				"shift_a"};
		private String[] special_key = { "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "숫자"};

		public keyBorad1() {
			setTitle("키보드");
			Container con = getContentPane();
			con.setBackground(Color.WHITE);
			con.setLayout(null);

			// 상단라벨
			JLabel label = new JLabel("학번 입력 키보드");
			label.setSize(700, 50);
			label.setLocation(0, 0);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setOpaque(true);
			label.setBackground(new Color(255, 204, 153));
			Font big_font = new Font("a옛날사진관4", Font.BOLD, 30);
			label.setFont(big_font);

			// 입력확인창
			p1.setLayout(new FlowLayout());
			p1.setBackground(Color.WHITE);
			p1.add(input);
			p1.setSize(700, 30);
			p1.setLocation(0, 55);

			// 입력 글씨 글꼴 및 글씨 크기
			Font font1 = new Font("NanumSquare", Font.BOLD, 20);
			input.setFont(font1);
			input.setSize(100, 50);

			// 키패드
			GridLayout grid = new GridLayout(5, 8);
			grid.setVgap(5);
			p2_KEY.setBackground(Color.WHITE);
			p2_KEY.setLayout(grid);
			p2_KEY.setSize(680, 200);
			p2_KEY.setLocation(4, 90);

			for (int i = 0; i < key.length; ++i) {
				btn_key[i] = new JButton(key[i]);
				btn_key[i].addActionListener(new inputkey_ActionListener());
				btn_key[i].setBackground(new Color(255, 229, 216));
				p2_KEY.add(btn_key[i]);
			}

			con.add(label);
			con.add(p1);
			con.add(p2_KEY);

			setSize(700, 330);
			setVisible(true);
		}

		class inputkey_ActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				if (b.getText().equals("1")) {
					input.append("1");
				} else if (b.getText().equals("2")) {
					input.append("2");
				} else if (b.getText().equals("3")) {
					input.append("3");
				} else if (b.getText().equals("4")) {
					input.append("4");
				} else if (b.getText().equals("5")) {
					input.append("5");
				} else if (b.getText().equals("6")) {
					input.append("6");
				} else if (b.getText().equals("7")) {
					input.append("7");
				} else if (b.getText().equals("8")) {
					input.append("8");
				} else if (b.getText().equals("9")) {
					input.append("9");
				} else if (b.getText().equals("0")) {
					input.append("0");
				} else if (b.getText().equals("q")) {
					input.append("q");
				} else if (b.getText().equals("w")) {
					input.append("w");
				} else if (b.getText().equals("e")) {
					input.append("e");
				} else if (b.getText().equals("r")) {
					input.append("r");
				} else if (b.getText().equals("t")) {
					input.append("t");
				} else if (b.getText().equals("y")) {
					input.append("y");
				} else if (b.getText().equals("u")) {
					input.append("u");
				} else if (b.getText().equals("i")) {
					input.append("i");
				} else if (b.getText().equals("o")) {
					input.append("o");
				} else if (b.getText().equals("p")) {
					input.append("p");
				} else if (b.getText().equals("a")) {
					input.append("a");
				} else if (b.getText().equals("s")) {
					input.append("s");
				} else if (b.getText().equals("d")) {
					input.append("d");
				} else if (b.getText().equals("f")) {
					input.append("f");
				} else if (b.getText().equals("g")) {
					input.append("g");
				} else if (b.getText().equals("h")) {
					input.append("h");
				} else if (b.getText().equals("j")) {
					input.append("j");
				} else if (b.getText().equals("k")) {
					input.append("k");
				} else if (b.getText().equals("l")) {
					input.append("l");
				} else if (b.getText().equals("z")) {
					input.append("z");
				} else if (b.getText().equals("x")) {
					input.append("x");
				} else if (b.getText().equals("c")) {
					input.append("c");
				} else if (b.getText().equals("v")) {
					input.append("v");
				} else if (b.getText().equals("b")) {
					input.append("b");
				} else if (b.getText().equals("n")) {
					input.append("n");
				} else if (b.getText().equals("m")) {
					input.append("m");
				}

				// shift_A
				else if (b.getText().equals("!")) {
					input.append("!");
				} else if (b.getText().equals("@")) {
					input.append("@");
				} else if (b.getText().equals("#")) {
					input.append("#");
				} else if (b.getText().equals("$")) {
					input.append("$");
				} else if (b.getText().equals("%")) {
					input.append("%");
				} else if (b.getText().equals("^")) {
					input.append("^");
				} else if (b.getText().equals("&")) {
					input.append("&");
				} else if (b.getText().equals("*")) {
					input.append("*");
				} else if (b.getText().equals("(")) {
					input.append("(");
				} else if (b.getText().equals(")")) {
					input.append(")");
				} else if (b.getText().equals("Q")) {
					input.append("Q");
				} else if (b.getText().equals("W")) {
					input.append("W");
				} else if (b.getText().equals("E")) {
					input.append("E");
				} else if (b.getText().equals("R")) {
					input.append("R");
				} else if (b.getText().equals("T")) {
					input.append("T");
				} else if (b.getText().equals("Y")) {
					input.append("Y");
				} else if (b.getText().equals("U")) {
					input.append("U");
				} else if (b.getText().equals("I")) {
					input.append("I");
				} else if (b.getText().equals("O")) {
					input.append("O");
				} else if (b.getText().equals("P")) {
					input.append("P");
				} else if (b.getText().equals("A")) {
					input.append("A");
				} else if (b.getText().equals("S")) {
					input.append("S");
				} else if (b.getText().equals("D")) {
					input.append("D");
				} else if (b.getText().equals("F")) {
					input.append("F");
				} else if (b.getText().equals("G")) {
					input.append("G");
				} else if (b.getText().equals("H")) {
					input.append("H");
				} else if (b.getText().equals("J")) {
					input.append("J");
				} else if (b.getText().equals("K")) {
					input.append("K");
				} else if (b.getText().equals("L")) {
					input.append("L");
				} else if (b.getText().equals("Z")) {
					input.append("Z");
				} else if (b.getText().equals("X")) {
					input.append("X");
				} else if (b.getText().equals("C")) {
					input.append("C");
				} else if (b.getText().equals("V")) {
					input.append("V");
				} else if (b.getText().equals("B")) {
					input.append("B");
				} else if (b.getText().equals("N")) {
					input.append("N");
				} else if (b.getText().equals("M")) {
					input.append("M");
				} else if (b.getText().equals("<HTML><body><center>모두<br>지우기</center></body></HTML>")) {
					input.setText("");
				} else if (b.getText().equals("shift_A")) {
					int j=0;
					for (int i = 10; i < 37; ++i) {
						btn_key[i].setText(shift_key[j]);
						++j;
					}
				} else if (b.getText().equals("shift_a")) {
					for (int i = 10; i < 37; ++i) {
						btn_key[i].setText(key[i]);
					}
					btn_key[36].setText(key[36]
				} else if (b.getText().equals("특수기호")) {
					for (int i = 0; i < 10; ++i) {
						btn_key[i].setText(special_key[i]);
					}
					btn_key[37].setText(special_key[10]);
				} else if (b.getText().equals("숫자")) {
					for (int i = 0; i < 10; ++i) {
						btn_key[i].setText(key[i]);
					}
					btn_key[37].setText(key[37]);
				} else if (b.getText().equals("입력")) {
					input_sn.setText(input.getText());
					dispose();
				}
			}
		}
	}

	class keyBorad2 extends JFrame {
		private JPanel p1 = new JPanel();
		private JPanel p2_KEY = new JPanel();
		public JTextArea input = new JTextArea();
		private JButton[] btn_key = new JButton[40];
		private String[] key = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "0", " q", "w", "e", "r", "t", "y", "u", "i",
				"o", "p", "a", "s", "d", "f", "g", "h", "j", "k", "l", "z", "x", "c", "v", "b", "n", "m", "shift_A", "특수기호",
				"<HTML><body><center>모두<br>지우기</center></body></HTML>","입력"};
		private String[] shift_key = {" Q", "W", "E", "R", "T", "Y",
				"U", "I", "O", "P", "A", "S", "D", "F", "G", "H", "J", "K", "L", "Z", "X", "C", "V", "B", "N", "M",
				"shift_a"};
		private String[] special_key = { "!", "@", "#", "$", "%", "^", "&", "*", "(", ")", "숫자"};

		public keyBorad2() {
			setTitle("키보드");
			Container con = getContentPane();
			con.setBackground(Color.WHITE);
			con.setLayout(null);

			// 상단라벨
			JLabel label = new JLabel("비밀번호 입력 키보드");
			label.setSize(700, 50);
			label.setLocation(0, 0);
			label.setHorizontalAlignment(JLabel.CENTER);
			label.setOpaque(true);
			label.setBackground(new Color(255, 204, 153));
			Font big_font = new Font("NanumSquare", Font.BOLD, 30);
			label.setFont(big_font);

			// 입력확인창
			p1.setLayout(new FlowLayout());
			p1.setBackground(Color.WHITE);
			p1.add(input);
			p1.setSize(700, 30);
			p1.setLocation(0, 55);

			// 입력 글씨 글꼴 및 글씨 크기
			Font font1 = new Font("NanumSquare", Font.BOLD, 20);
			input.setFont(font1);
			input.setSize(100, 50);

			// 키패드
			GridLayout grid = new GridLayout(5, 8);
			grid.setVgap(5);
			p2_KEY.setBackground(Color.WHITE);
			p2_KEY.setLayout(grid);
			p2_KEY.setSize(680, 200);
			p2_KEY.setLocation(4, 90);

			for (int i = 0; i < key.length; ++i) {
				btn_key[i] = new JButton(key[i]);
				btn_key[i].addActionListener(new inputkey_ActionListener());
				btn_key[i].setBackground(new Color(255, 229, 216));
				p2_KEY.add(btn_key[i]);
			}

			con.add(label);
			con.add(p1);
			con.add(p2_KEY);

			setSize(700, 330);
			setVisible(true);
		}

		class inputkey_ActionListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				if (b.getText().equals("1")) {
					input.append("1");
				} else if (b.getText().equals("2")) {
					input.append("2");
				} else if (b.getText().equals("3")) {
					input.append("3");
				} else if (b.getText().equals("4")) {
					input.append("4");
				} else if (b.getText().equals("5")) {
					input.append("5");
				} else if (b.getText().equals("6")) {
					input.append("6");
				} else if (b.getText().equals("7")) {
					input.append("7");
				} else if (b.getText().equals("8")) {
					input.append("8");
				} else if (b.getText().equals("9")) {
					input.append("9");
				} else if (b.getText().equals("0")) {
					input.append("0");
				} else if (b.getText().equals("q")) {
					input.append("q");
				} else if (b.getText().equals("w")) {
					input.append("w");
				} else if (b.getText().equals("e")) {
					input.append("e");
				} else if (b.getText().equals("r")) {
					input.append("r");
				} else if (b.getText().equals("t")) {
					input.append("t");
				} else if (b.getText().equals("y")) {
					input.append("y");
				} else if (b.getText().equals("u")) {
					input.append("u");
				} else if (b.getText().equals("i")) {
					input.append("i");
				} else if (b.getText().equals("o")) {
					input.append("o");
				} else if (b.getText().equals("p")) {
					input.append("p");
				} else if (b.getText().equals("a")) {
					input.append("a");
				} else if (b.getText().equals("s")) {
					input.append("s");
				} else if (b.getText().equals("d")) {
					input.append("d");
				} else if (b.getText().equals("f")) {
					input.append("f");
				} else if (b.getText().equals("g")) {
					input.append("g");
				} else if (b.getText().equals("h")) {
					input.append("h");
				} else if (b.getText().equals("j")) {
					input.append("j");
				} else if (b.getText().equals("k")) {
					input.append("k");
				} else if (b.getText().equals("l")) {
					input.append("l");
				} else if (b.getText().equals("z")) {
					input.append("z");
				} else if (b.getText().equals("x")) {
					input.append("x");
				} else if (b.getText().equals("c")) {
					input.append("c");
				} else if (b.getText().equals("v")) {
					input.append("v");
				} else if (b.getText().equals("b")) {
					input.append("b");
				} else if (b.getText().equals("n")) {
					input.append("n");
				} else if (b.getText().equals("m")) {
					input.append("m");
				}

				// shift_A
				else if (b.getText().equals("!")) {
					input.append("!");
				} else if (b.getText().equals("@")) {
					input.append("@");
				} else if (b.getText().equals("#")) {
					input.append("#");
				} else if (b.getText().equals("$")) {
					input.append("$");
				} else if (b.getText().equals("%")) {
					input.append("%");
				} else if (b.getText().equals("^")) {
					input.append("^");
				} else if (b.getText().equals("&")) {
					input.append("&");
				} else if (b.getText().equals("*")) {
					input.append("*");
				} else if (b.getText().equals("(")) {
					input.append("(");
				} else if (b.getText().equals(")")) {
					input.append(")");
				} else if (b.getText().equals("Q")) {
					input.append("Q");
				} else if (b.getText().equals("W")) {
					input.append("W");
				} else if (b.getText().equals("E")) {
					input.append("E");
				} else if (b.getText().equals("R")) {
					input.append("R");
				} else if (b.getText().equals("T")) {
					input.append("T");
				} else if (b.getText().equals("Y")) {
					input.append("Y");
				} else if (b.getText().equals("U")) {
					input.append("U");
				} else if (b.getText().equals("I")) {
					input.append("I");
				} else if (b.getText().equals("O")) {
					input.append("O");
				} else if (b.getText().equals("P")) {
					input.append("P");
				} else if (b.getText().equals("A")) {
					input.append("A");
				} else if (b.getText().equals("S")) {
					input.append("S");
				} else if (b.getText().equals("D")) {
					input.append("D");
				} else if (b.getText().equals("F")) {
					input.append("F");
				} else if (b.getText().equals("G")) {
					input.append("G");
				} else if (b.getText().equals("H")) {
					input.append("H");
				} else if (b.getText().equals("J")) {
					input.append("J");
				} else if (b.getText().equals("K")) {
					input.append("K");
				} else if (b.getText().equals("L")) {
					input.append("L");
				} else if (b.getText().equals("Z")) {
					input.append("Z");
				} else if (b.getText().equals("X")) {
					input.append("X");
				} else if (b.getText().equals("C")) {
					input.append("C");
				} else if (b.getText().equals("V")) {
					input.append("V");
				} else if (b.getText().equals("B")) {
					input.append("B");
				} else if (b.getText().equals("N")) {
					input.append("N");
				} else if (b.getText().equals("M")) {
					input.append("M");
				} else if (b.getText().equals("<HTML><body><center>모두<br>지우기</center></body></HTML>")) {
					input.setText("");
				} else if (b.getText().equals("shift_A")) {
					int j=0;
					for (int i = 10; i < 37; ++i) {
						btn_key[i].setText(shift_key[j]);
						++j;
					}
				} else if (b.getText().equals("shift_a")) {
					for (int i = 10; i < 37; ++i) {
						btn_key[i].setText(key[i]);
					}
					btn_key[36].setText(key[36]);
				} else if (b.getText().equals("특수기호")) {
					for (int i = 0; i < 10; ++i) {
						btn_key[i].setText(special_key[i]);
					}
					btn_key[37].setText(special_key[10]);
				} else if (b.getText().equals("숫자")) {
					for (int i = 0; i < 10; ++i) {
						btn_key[i].setText(key[i]);
					}
					btn_key[37].setText(key[37]);
				} else if (b.getText().equals("입력")) {
					input_sp.setText(input.getText());
					dispose();
				}
			}
		}
	}
}
