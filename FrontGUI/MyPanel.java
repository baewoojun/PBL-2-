package FrontGUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.HashSet;
import CoreEngine.*;
/**
 * LibraryApplication의 패널(Event Listener Object의 역할 겸용)
 * @author (2022320014_정재헌, 2022320035_배우준, 20220320018_이성민)
 * @version (2025.12.04)
 */
public class MyPanel extends JPanel implements ActionListener
{
    protected JPanel buttonPanel;
    protected JPanel outputDataPanel;

    protected JLabel ml_BorrowerName, ml_BorrowerEmail, ml_BookTitle, ml_BookAuthor, ml_BookID;
    protected JTextField mtf_BorrowerName, mtf_BorrowerEmail, mtf_BookTitle, mtf_BookAuthor, mtf_BookID;
    protected JButton mb_Run, mb_Clear;
    protected JTextArea mta;
    protected String[] loanORreturn = {"대출", "반납","등록","삭제"};
    protected JComboBox mcb_loanORreturn;
    protected String output = "";
    protected int index;

    //GUI에서 관리하는 대출 정보
    protected HashMap<String, String> loanMap = new HashMap<>();// (email → bookID)
    protected HashSet<String> borrowedBookSet = new HashSet<>();// 대출 중 책 목록

    public MyPanel(){
        ml_BorrowerName = new JLabel("이용자 이름");
        ml_BorrowerEmail = new JLabel("이용자 이메일");
        ml_BookTitle = new JLabel("책 제목");
        ml_BookAuthor = new JLabel("책 저자이름");
        ml_BookID = new JLabel("책 등록번호");

        mtf_BorrowerName = new JTextField("Your Name", 20);
        mtf_BorrowerEmail = new JTextField("Your Email",20);
        mtf_BookTitle = new JTextField("Book Title", 20);
        mtf_BookAuthor= new JTextField("Book Author", 20);
        mtf_BookID = new JTextField("Book ID", 20);
        mcb_loanORreturn = new JComboBox(loanORreturn);

        this.add(ml_BorrowerName);
        this.add(mtf_BorrowerName);
        this.add(ml_BorrowerEmail);
        this.add(mtf_BorrowerEmail);
        this.add(ml_BookTitle);
        this.add(mtf_BookTitle);
        this.add(ml_BookAuthor);
        this.add(mtf_BookAuthor);
        this.add(ml_BookID);
        this.add(mtf_BookID);
        this.add(new JLabel("선택"));
        this.add(mcb_loanORreturn);

        mb_Run = new JButton("실행");
        this.add(mb_Run);
        mb_Clear = new JButton("Clear");
        this.add(mb_Clear);

        mta = new JTextArea(20, 25);
        this.add(new JScrollPane(mta));

        mcb_loanORreturn.addActionListener(this);
        mb_Run.addActionListener(this);
        mb_Clear.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        LibraryApplication libApp = new LibraryApplication("선문대학교 중앙도서관");

        // 선택 변경 시 출력 텍스트 구성
        if(e.getSource().equals(mcb_loanORreturn)){
            JComboBox cb = (JComboBox)e.getSource();
            index = cb.getSelectedIndex();

            output = loanORreturn[index] + "자 : " + mtf_BorrowerName.getText() + "\n"
                    + loanORreturn[index] + "책 제목 : " + mtf_BookTitle.getText() + "\n"
                    + loanORreturn[index] + "책 저자 : " + mtf_BookAuthor.getText() + "\n"
                    + loanORreturn[index] + "책 고유번호 : " + mtf_BookID.getText() + "\n"
                    + "-------------------------------------------------" + "\n";
        }

        if(index == 0 && e.getSource().equals(mb_Run)){
            String borrowerEmail = mtf_BorrowerEmail.getText();
            String bookID = mtf_BookID.getText();

            if(borrowedBookSet.contains(bookID)){
                mta.append("[대출 실패] 이 책 (" + bookID + ") 은 이미 대출 중입니다!\n");
                mta.append("---------------------------------\n");
                return;
            }
            String outputTitle = libApp.loanOneBook(mtf_BorrowerName.getText(), bookID);
            mta.append(outputTitle + "\n" + output);

            loanMap.put(borrowerEmail, bookID);
            borrowedBookSet.add(bookID);
        }
        else if(index == 1 && e.getSource().equals(mb_Run)){
            String borrowerEmail = mtf_BorrowerEmail.getText();
            String bookID = mtf_BookID.getText();

            String outputTitle = libApp.returnOneBook(bookID);
            mta.append(outputTitle + "\n" + output);

            if(loanMap.containsKey(borrowerEmail)){
                loanMap.remove(borrowerEmail);
            }

            borrowedBookSet.remove(bookID);

            mta.append("[반납 완료] 현재 이용자는 대출 중이 아닙니다.\n");
            mta.append("---------------------------------\n");
        }
        else if(index == 2 && e.getSource().equals(mb_Run)){
            mta.append("[이용자 등록]\n");
            mta.append("이름: " + mtf_BorrowerName.getText() + "\n");
            mta.append("이메일: " + mtf_BorrowerEmail.getText() + "\n");
            mta.append("---------------------------------\n");
        }
        else if(index == 3 && e.getSource().equals(mb_Run)){
            String borrowerEmail = mtf_BorrowerEmail.getText();

            if(loanMap.containsKey(borrowerEmail)){
                mta.append("[삭제 실패] 이 이용자는 현재 책을 대출 중입니다!\n");
                mta.append("반납 후 삭제 가능합니다.\n");
            } else {
                mta.append("[이용자 삭제]\n");
                mta.append("이름: " + mtf_BorrowerName.getText() + "\n");
                mta.append("이메일: " + borrowerEmail + "\n");
            }
            mta.append("---------------------------------\n");
        }
        else if(e.getSource().equals(mb_Clear)){
            mtf_BorrowerName.setText("");
            mtf_BookTitle.setText("");
            mtf_BookAuthor.setText("");
            mtf_BookID.setText("");
        }
    }
}
