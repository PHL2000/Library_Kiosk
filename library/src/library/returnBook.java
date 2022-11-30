package library;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import library.extension.inActionListener;

public class returnBook extends JFrame  {
   JLabel labelTitle = new JLabel("반납할 도서ID를 입력해주세요.");
   JLabel labelId = new JLabel("도서 ID");
   JTextField bookID = new JTextField();
   JButton enterBtn = new JButton("반납");
   JButton btn_home = new JButton("홈");

   public returnBook() {
      setTitle("반납");
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container c = getContentPane();
      c.setBackground(new Color(245, 245, 245));
      c.setLayout(null);


      // 상단 라벨
      JLabel label = new JLabel("도서 반납");
      label.setSize(700, 100);
      label.setLocation(0, 0);
      label.setHorizontalAlignment(JLabel.CENTER);
      label.setOpaque(true);
      label.setBackground(new Color(255, 204, 153));

      Font big_font = new Font("NanumSquare", Font.BOLD, 45);
      label.setFont(big_font);

      // 도서id 라벨
      bookID.setSize(525, 62);
      bookID.setLocation(130, 315);

      // 라벨 사이즈 및 폰트 지정
      Font font_all = new Font("NanumSquare", Font.BOLD, 25);
      labelTitle.setSize(400, 30);
      labelTitle.setFont(font_all);
      labelTitle.setLocation(150, 200);
      Font font_la = new Font("NanumSquare", Font.BOLD, 25);
      labelId.setSize(90, 30);
      labelId.setFont(font_la);
      labelId.setLocation(25, 330);

      // 로그인 버튼
      Font font_rogin = new Font("NanumSquare", Font.BOLD, 20);
      enterBtn.setBackground(new Color(255, 153, 51));
      enterBtn.setFont(font_rogin);
      enterBtn.setForeground(new Color(255, 255, 255));
      enterBtn.setBorderPainted(false);
      enterBtn.setSize(410, 62);
      enterBtn.setLocation(142, 400);

      // 홈버튼
      ImageIcon homeIcon = new ImageIcon("images/home.png");
      Image homeimg = homeIcon.getImage();
      homeimg = homeimg.getScaledInstance(52, 52, java.awt.Image.SCALE_SMOOTH);
      homeIcon = new ImageIcon(homeimg);
      btn_home = new JButton("HOME", homeIcon);
      btn_home.setBackground(new Color(204, 204, 204));
      btn_home.setFont(font_all);
      btn_home.setForeground(new Color(255, 255, 255));
      btn_home.setBorderPainted(false);
      btn_home.setSize(210, 100);
      btn_home.setLocation(245, 640);

      // 리스너 추가
      enterBtn.addActionListener(new inActionListener());
      btn_home.addActionListener(new inActionListener());

      // 추가
      c.add(label);
      c.add(labelTitle);
      c.add(labelId);
      c.add(bookID);
      c.add(enterBtn);
      c.add(btn_home);

      labelTitle.setHorizontalAlignment(JLabel.CENTER);
      labelId.setHorizontalAlignment(JLabel.CENTER);

      setSize(700, 800);
      setVisible(true);

   }
   class inActionListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         JButton b = (JButton)e.getSource();
         if (b.getText().equals("반납"))   {
            int result = JOptionPane.showConfirmDialog(null,"["+ bookID.getText()+"]\n반납하시겠습니까?" , "도서 반납", JOptionPane.YES_NO_OPTION );
            if(result == JOptionPane.YES_OPTION) {
               JOptionPane.showMessageDialog(null, "반납이 완료되었습니다.", "반납", JOptionPane.DEFAULT_OPTION);

               //반납 시 필요한 쿼리 작성
               //1. 도서테이블의 해당 도서 대출가능여부 Y로 변경 (대여테이블에서 땡겨오기)
               //2. 회원테이블의 대출가능권수 +1 (대여테이블에서 땡겨오기)
               //3. 대여테이블의 해당 대여 행 삭제? or 대출 종료 여부 추가

            }
            else if(result == JOptionPane.NO_OPTION || result == JOptionPane.CLOSED_OPTION) {
               //No 혹은 창 종료 시 실행될 부분
            }
         }
         else if(b.getText().equals("")) {
            //메인화면 이동
         }
         else if (b.getText().equals("홈"))	{
				new library_main();
				dispose();
			}

      }
   }
}
