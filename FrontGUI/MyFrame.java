package FrontGUI;

import javax.swing.*;

/**
 * LibraryApplication의 프레임
 *
 * @author (2022320014 정재헌)
 * @version (2025.11.26)
 */
public class MyFrame extends JFrame
{
    public MyFrame(){
        this.setTitle("도서관 관리 시스템");
        this.setSize(297, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        
        this.add(new MyPanel());
    }
}
