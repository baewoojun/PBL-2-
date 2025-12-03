package FrontGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import CoreEngine.*;
/**
 * LibraryApplication의 패널(Event Listener Object의 역할 겸용)
 *
 * @author (profsHwang)
 * @version (2025.11.26)
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

        if(e.getSource().equals(mcb_loanORreturn)){
            JComboBox cb = (JComboBox)e.getSource();
            index = cb.getSelectedIndex(); 

            output = loanORreturn[index] + "자 : " + mtf_BorrowerName.getText() + "\n"
            + loanORreturn[index] + "책 제목 : " + mtf_BookTitle.getText() + "\n"
            + loanORreturn[index] + "책 저자 : " + mtf_BookAuthor.getText() + "\n"
            + loanORreturn[index] + "책 등록번호 : " + mtf_BookID.getText() + "\n"
            + "-------------------------------------------------" + "\n";
        }   

        if(index == 0 && e.getSource().equals(mb_Run)){
            String outputTitle = libApp.loanOneBook(mtf_BorrowerName.getText(), mtf_BookID.getText());
            mta.append(outputTitle + "\n" + output);
        }
        else if(index == 1 && e.getSource().equals(mb_Run)){
            String outputTitle = libApp.returnOneBook(mtf_BookID.getText());
            mta.append(outputTitle + "\n" + output);
        }

        else if(index == 2 && e.getSource().equals(mb_Run)){
            mta.append("[이용자 등록]\n");
            mta.append("이름: " + mtf_BorrowerName.getText() + "\n");
            mta.append("이메일: " + mtf_BorrowerEmail.getText() + "\n");
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