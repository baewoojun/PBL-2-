package FrontGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.HashSet;
import CoreEngine.*;
/**
 * LibraryApplication의 GUI
 *
 * @author (2022320014_정재헌, 2022320035_배우준, 20220320018_이성민)
 * @version (2025.12.04)
 */
public class MyPanel extends JPanel implements ActionListener {
    protected JLabel ml_BorrowerName, ml_BorrowerEmail, ml_BookTitle, ml_BookAuthor, ml_BookID;
    protected JTextField mtf_BorrowerName, mtf_BorrowerEmail, mtf_BookTitle, mtf_BookAuthor, mtf_BookID;
    protected JButton mb_Run, mb_Clear;
    protected JTextArea mta;
    protected String[] loanORreturn = {"이용자 등록", "이용자 삭제", "책 등록", "책 삭제", "대출", "반납"};
    protected JComboBox mcb_loanORreturn;
    protected String output = "";
    protected int index;

    public MyPanel() {
        setLayout(new BorderLayout(10, 10));
        
        JPanel northPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        ml_BorrowerName = new JLabel("이용자 이름");
        ml_BorrowerEmail = new JLabel("이용자 이메일");
        mtf_BorrowerName = new JTextField("Your Name", 15);
        mtf_BorrowerEmail = new JTextField("Your Email", 15);

        northPanel.add(ml_BorrowerName);
        northPanel.add(mtf_BorrowerName);
        northPanel.add(ml_BorrowerEmail);
        northPanel.add(mtf_BorrowerEmail);

        add(northPanel, BorderLayout.NORTH);
        
        mta = new JTextArea(20, 40);
        mta.setEditable(false);
        add(new JScrollPane(mta), BorderLayout.CENTER);

        JPanel southPanel = new JPanel(new GridLayout(4, 2, 5, 5));

        ml_BookTitle = new JLabel("책 제목");
        ml_BookAuthor = new JLabel("책 저자");
        ml_BookID = new JLabel("책 고유번호");

        mtf_BookTitle = new JTextField("Book Title", 15);
        mtf_BookAuthor = new JTextField("Book Author", 15);
        mtf_BookID = new JTextField("Book ID", 15);

        // 책 정보 입력란
        southPanel.add(ml_BookTitle);
        southPanel.add(mtf_BookTitle);
        southPanel.add(ml_BookAuthor);
        southPanel.add(mtf_BookAuthor);
        southPanel.add(ml_BookID);
        southPanel.add(mtf_BookID);

        // 실행 버튼
        JPanel btnPanel = new JPanel();

        mcb_loanORreturn = new JComboBox(loanORreturn);
        mb_Run = new JButton("실행");
        mb_Clear = new JButton("Clear");

        btnPanel.add(new JLabel("선택"));
        btnPanel.add(mcb_loanORreturn);
        btnPanel.add(mb_Run);
        btnPanel.add(mb_Clear);

        southPanel.add(btnPanel);

        add(southPanel, BorderLayout.SOUTH);

        // 리스너 등록
        mcb_loanORreturn.addActionListener(this);
        mb_Run.addActionListener(this);
        mb_Clear.addActionListener(this);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(mcb_loanORreturn)) {
            JComboBox cb = (JComboBox) e.getSource();
            index = cb.getSelectedIndex();
        }
        else if (index == 0 && e.getSource().equals(mb_Run)) {
            mta.append("[이용자 등록]\n");
            mta.append("이름: " + mtf_BorrowerName.getText() + "\n");
            mta.append("이메일: " + mtf_BorrowerEmail.getText() + "\n");
            mta.append("---------------------------------\n");
        }
        else if (index == 1 && e.getSource().equals(mb_Run)) {
            mta.append("[이용자 삭제가 완료 되었습니다]\n");
            mta.append("이름: " + mtf_BorrowerName.getText() + "\n");
            mta.append("이메일: " + mtf_BorrowerEmail.getText() + "\n");
        }
        else if (index == 2 && e.getSource().equals(mb_Run)) {
            String title = mtf_BookTitle.getText();
            String author = mtf_BookAuthor.getText();
            String id = mtf_BookID.getText();

            mta.append("[책 등록이 완료 되었습니다]\n");
            mta.append("책 제목: " + title + "\n");
            mta.append("책 저자이름: " + author + "\n");
            mta.append("책 등록번호: " + id + "\n");
            mta.append("---------------------------------\n");
        }
        else if (index == 3 && e.getSource().equals(mb_Run)) {
            String title = mtf_BookTitle.getText();
            String author = mtf_BookAuthor.getText();
            String id = mtf_BookID.getText();
            
            mta.append("[책 삭제가 완료 되었습니다]\n");
            mta.append("책 제목: " + title + "\n");
            mta.append("책 저자이름: " + author + "\n");
            mta.append("책 등록번호: " + id + "\n");
        }
        else if(index == 4 && e.getSource().equals(mb_Run)) {
            String borrower = mtf_BorrowerName.getText();
            String email = mtf_BorrowerEmail.getText();
            String title = mtf_BookTitle.getText();
            String id = mtf_BookID.getText();
            
            mta.append("[책이 대출되었습니다]\n");
            mta.append("반납자 : " + borrower + "\n");
            mta.append("반납책 제목 : " + title + "\n");
            mta.append("반납책 저자 : " + mtf_BookAuthor.getText() + "\n");
            mta.append("반납책 고유번호 : " + id + "\n");
            mta.append("---------------------------------\n");
        }
        else if (index == 5 && e.getSource().equals(mb_Run)) {
            String borrower = mtf_BorrowerName.getText();
            String email = mtf_BorrowerEmail.getText();
            String title = mtf_BookTitle.getText();
            String id = mtf_BookID.getText();
            
            mta.append("[책이 반납되었습니다]\n");
            mta.append("반납자 : " + borrower + "\n");
            mta.append("반납책 제목 : " + title + "\n");
            mta.append("반납책 저자 : " + mtf_BookAuthor.getText() + "\n");
            mta.append("반납책 고유번호 : " + id + "\n");
            mta.append("---------------------------------\n");
        }
        else if (e.getSource().equals(mb_Clear)) {
            mtf_BorrowerName.setText("");
            mtf_BorrowerEmail.setText("");
            mtf_BookTitle.setText("");
            mtf_BookAuthor.setText("");
            mtf_BookID.setText("");
        }
    }
}