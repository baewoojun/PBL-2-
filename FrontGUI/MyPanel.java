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
    protected String[] loanORreturn = {"대출", "반납", "이용자 등록", "이용자 삭제", "책 등록", "책 삭제"};
    protected JComboBox mcb_loanORreturn;
    protected String output = "";
    protected int index;

    // 데이터 저장
    protected HashMap<String, String> loanMap = new HashMap<>();
    protected HashSet<String> borrowedBookSet = new HashSet<>();
    protected HashMap<String, String[]> bookMap = new HashMap<>();

    public MyPanel() {
        setLayout(new BorderLayout(10, 10));
        
        JPanel northPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        northPanel.setBorder(BorderFactory.createTitledBorder("[이용자 정보]"));

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
        southPanel.setBorder(BorderFactory.createTitledBorder("[도서 정보 입력]"));

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

        LibraryApplication libApp = new LibraryApplication("선문대학교 중앙도서관");

        if (e.getSource().equals(mcb_loanORreturn)) {
            JComboBox cb = (JComboBox) e.getSource();
            index = cb.getSelectedIndex();

            output = loanORreturn[index] + "자 : " + mtf_BorrowerName.getText() + "\n"
                    + loanORreturn[index] + "책 제목 : " + mtf_BookTitle.getText() + "\n"
                    + loanORreturn[index] + "책 저자 : " + mtf_BookAuthor.getText() + "\n"
                    + loanORreturn[index] + "책 고유번호 : " + mtf_BookID.getText() + "\n"
                    + "-------------------------------------------------\n";
        }
        if (index == 0 && e.getSource().equals(mb_Run)) {

            String borrower = mtf_BorrowerName.getText();
            String email = mtf_BorrowerEmail.getText();
            String title = mtf_BookTitle.getText();
            String id = mtf_BookID.getText();

            if (borrowedBookSet.contains(id)) {
                mta.append("[대출 실패] 책(" + title + "," + id + ")은 이미 대출 중입니다.\n");
                mta.append("---------------------------------\n");
                return;
            }

            libApp.loanOneBook(borrower, id);
            mta.append("책(" + title + "," + id + ")이 이용자(" + borrower + ")에게 대출되었습니다.\n");
            mta.append(output);

            loanMap.put(email, id);
            borrowedBookSet.add(id);

            mta.append("[대출 완료] 이용자(" + borrower + ")는 책(" + title + "," + id + ")을 대출 중입니다.\n");
            mta.append("---------------------------------\n");
        }
        else if (index == 1 && e.getSource().equals(mb_Run)) {

            String borrower = mtf_BorrowerName.getText();
            String email = mtf_BorrowerEmail.getText();
            String title = mtf_BookTitle.getText();
            String id = mtf_BookID.getText();

            libApp.returnOneBook(id);

            mta.append("책(" + title + "," + id + ")이 반납되었습니다.\n");
            mta.append("반납자 : " + borrower + "\n");
            mta.append("반납책 제목 : " + title + "\n");
            mta.append("반납책 저자 : " + mtf_BookAuthor.getText() + "\n");
            mta.append("반납책 고유번호 : " + id + "\n");
            mta.append("---------------------------------\n");

            loanMap.remove(email);
            borrowedBookSet.remove(id);

            mta.append("[반납 완료] 이용자(" + borrower + ")는 현재 대출 중이 아닙니다.\n");
            mta.append("---------------------------------\n");
        }
        else if (index == 2 && e.getSource().equals(mb_Run)) {

            mta.append("[이용자 등록]\n");
            mta.append("이름: " + mtf_BorrowerName.getText() + "\n");
            mta.append("이메일: " + mtf_BorrowerEmail.getText() + "\n");
            mta.append("---------------------------------\n");
        }
        else if (index == 3 && e.getSource().equals(mb_Run)) {

            String name = mtf_BorrowerName.getText();
            String email = mtf_BorrowerEmail.getText();

            if (loanMap.containsKey(email)) {
                mta.append("[삭제 실패] 이용자(" + name + ")는 책을 대출 중입니다.\n");
                mta.append("반납 후 삭제 가능합니다.\n");
            } else {
                mta.append("[이용자 삭제]\n");
                mta.append("이름: " + name + "\n");
                mta.append("이메일: " + email + "\n");
            }
            mta.append("---------------------------------\n");
        }
        else if (index == 4 && e.getSource().equals(mb_Run)) {

            String title = mtf_BookTitle.getText();
            String author = mtf_BookAuthor.getText();
            String id = mtf_BookID.getText();

            bookMap.put(id, new String[]{title, author});

            mta.append("[책 등록]\n");
            mta.append("책 제목: " + title + "\n");
            mta.append("책 저자이름: " + author + "\n");
            mta.append("책 등록번호: " + id + "\n");
            mta.append("---------------------------------\n");
        }
        else if (index == 5 && e.getSource().equals(mb_Run)) {

            String id = mtf_BookID.getText().trim();

            if (borrowedBookSet.contains(id)) {
                mta.append("[삭제 실패] 이 책은 대출 중입니다.\n");
                mta.append("반납 후 삭제 가능합니다.\n");
            } else if (bookMap.containsKey(id)) {

                String[] data = bookMap.get(id);

                mta.append("[책 삭제]\n");
                mta.append("책 제목 : " + data[0] + "\n");
                mta.append("책 저자이름 : " + data[1] + "\n");
                mta.append("책 고유번호 : " + id + "\n");

                bookMap.remove(id);

                mta.append("삭제 완료되었습니다.\n");
            } else {
                mta.append("[삭제 실패] 해당 책이 존재하지 않습니다.\n");
            }

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