package CoreEngine;


/**
 * Mock-Up(LibraryApplication : 6개의 Use Case 제공)
 *
 * @author (profsHwang)
 * @version (2025.11.26)
 */
public class LibraryApplication
{
    private String libraryName;
    public LibraryApplication(String name){
        libraryName = name;
    }
    
    // UC#1 : 이용자 등록
    public String registerOneBorrower(String name){
        return "이용자(" + name + ") 등록작업을 완료하였습니다.";
    }
    
    // UC#2 : 책 등록
    public String registerOneBook(String title, String author, String bookID){
        return "책(" + title + ", " + author + ", " + bookID + ") 등록작업을 완료하였습니다.";
    }
    
    // UC#3 : 대출가능한 책 목록 Display
    public String displayBookForLoan(){
        return "대출가능한 책 목록을 Display하였습니다.";
    }
    
    // UC#4 : 대출중인 책 목록 Display
    public String displayBookOnLoan(){
        return "대출가능한 책 목록을 Display하였습니다.";
    }
    
    // UC#5 : 책 1권 대출
    public String loanOneBook(String name, String bookID){
        return "책(" + bookID + ")이 이용자(" + name + ")에게 대출되었습니다.";
    }
    
    // UC#6 : 책 1권 반납
    public String returnOneBook(String bookID){
       return "책(" + bookID +")이 반납되었습니다.";
    }
}