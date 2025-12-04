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
public class MyPanel extends JPanel implements ActionListener, ItemListener
{
    protected JLabel ml_BorrowerName, ml_BorrowerEmail, ml_BookTitle, ml_BookAuthor, ml_BookID;
    protected JButton mb_Run, mb_Clear, mb_Display;
    protected JRadioButton mrb_ForLoan, mrb_OnLoan;
    protected JTextField mtf_BorrowerName, mtf_BorrowerEmail, mtf_BookTitle, mtf_BookAuthor, mtf_BookID;
    protected JTextArea mta;
    protected String[] registerLoanReturn = {"이용자 등록", "책 등록", "대출", "책 반납"};
    protected JComboBox mcb_registerLoanReturn;
    protected String output = "";
    protected int index;
    protected String displayInfo;

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

        this.add(northPanel, BorderLayout.NORTH);
        
        mta = new JTextArea(20, 40);
        this.add(new JScrollPane(mta), BorderLayout.CENTER);

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

        mcb_registerLoanReturn = new JComboBox(registerLoanReturn);
        mb_Run = new JButton("실행");
        mb_Clear = new JButton("Clear");
        mrb_ForLoan = new JRadioButton("대출 가능 책");
        mrb_OnLoan = new JRadioButton("대출 중인 책");
        ButtonGroup displayG = new ButtonGroup();
        displayG.add(mrb_ForLoan);
        displayG.add(mrb_OnLoan);
        mb_Display = new JButton("Display");

        btnPanel.add(new JLabel("선택"));
        btnPanel.add(mcb_registerLoanReturn);
        btnPanel.add(mb_Run);
        btnPanel.add(mb_Clear);
        btnPanel.add(mrb_ForLoan);
        btnPanel.add(mrb_OnLoan);
        btnPanel.add(mb_Display);

        southPanel.add(btnPanel);

        this.add(southPanel, BorderLayout.SOUTH);

        // 리스너 등록
        mcb_registerLoanReturn.addActionListener(this);
        mb_Run.addActionListener(this);
        mb_Clear.addActionListener(this);
        mrb_OnLoan.addItemListener(this);
        mrb_ForLoan.addItemListener(this);
        mb_Display.addActionListener(this);
        
    }
    
    public void actionPerformed(ActionEvent e) {
        LibraryApplication libApp = new LibraryApplication("선문대학교 중앙도서관");
        
        String borrower = mtf_BorrowerName.getText();
        String email = mtf_BorrowerEmail.getText();
        String title = mtf_BookTitle.getText();
        String author = mtf_BookAuthor.getText();
        String id = mtf_BookID.getText();
            

        if(e.getSource().equals(mcb_registerLoanReturn)){
            JComboBox cb = (JComboBox)e.getSource();
            index = cb.getSelectedIndex(); 

            output = registerLoanReturn[index] + "자 : " + borrower + "\n"
            + registerLoanReturn[index] + "책 제목 : " + title + "\n"
            + registerLoanReturn[index] + "책 저자 : " + author + "\n"
            + registerLoanReturn[index] + "책 등록번호 : " + id + "\n"
            + "-------------------------------------------------" + "\n";
        }
        
        if(index == 0 && e.getSource().equals(mb_Run)){
            String libOutput = libApp.registerOneBorrower(borrower);
            
            mta.append(libOutput + "\n");
            mta.append("[등록된 이용자 정보]\n");
            mta.append("이름: " + borrower + "\n");
            mta.append("이메일: " + email + "\n");
            mta.append("---------------------------------\n");
        }
        else if(index == 1 && e.getSource().equals(mb_Run)){
            String libOutput = libApp.registerOneBook(title, author, id);
            
            mta.append(libOutput + "\n");
            mta.append("[등록된 책 정보]\n");
            mta.append("책 제목: " + title + "\n");
            mta.append("책 저자이름: " + author + "\n");
            mta.append("책 등록번호: " + id + "\n");
            mta.append("---------------------------------\n");
        }
        else if(index == 2 && e.getSource().equals(mb_Run)){
            String libOutput = libApp.loanOneBook(borrower, id);

            mta.append(libOutput + "\n" + output);
        }
        else if(index == 3 && e.getSource().equals(mb_Run)){
            String libOutput = libApp.returnOneBook(id);

            mta.append(libOutput + "\n" + output);
        }
        else if(e.getSource().equals(mb_Clear)){
            mtf_BorrowerName.setText("");
            mtf_BorrowerEmail.setText("");
            mtf_BookTitle.setText("");
            mtf_BookAuthor.setText("");
            mtf_BookID.setText("");
        }
        else if(e.getSource().equals(mb_Display)){
            mta.append(displayInfo + "\n");
        }
    }
    
    /**
     * 메소드 itemStateChanged
     *
     * @param e 파라미터
     */
    public void itemStateChanged(ItemEvent e){
        LibraryApplication libApp = new LibraryApplication("선문대학교 중앙도서관");
        
        if(e.getStateChange() == ItemEvent.DESELECTED){
            return;
        }
        
        if(e.getStateChange() == ItemEvent.SELECTED){
            if((e.getItem()).equals(mrb_ForLoan)){
                displayInfo = libApp.displayBookForLoan();
            }
            else if((e.getItem()).equals(mrb_OnLoan)){
                displayInfo = libApp.displayBookOnLoan();
            }
        }
    }
}