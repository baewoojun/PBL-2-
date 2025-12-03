package FrontGUI;
import javax.swing.*;

/**
 * LibraryApplication의 프레임
 *
 * @author (2022320014_정재헌, 2022320035_배우준, 20220320018_이성민)
 * @version (2025.12.04)
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
