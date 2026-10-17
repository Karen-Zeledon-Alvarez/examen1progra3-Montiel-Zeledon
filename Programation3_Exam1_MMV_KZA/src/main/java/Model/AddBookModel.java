package Model;

public class AddBookModel {
  
    private String bookName, bookCode, bookAuthor;
    private int bookYear;
    
    public AddBookModel(){}
    
    public AddBookModel(String bookCode, String bookName, String bookAuthor, int bookYear) {
        this.bookCode = bookCode;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookYear = bookYear;
    }
 
    public void setbookName(String bookName_){this.bookName = bookName_;}
    public String getbookName(){return bookName;}
    
    public void setbookCode(String bookCode_){this.bookCode = bookCode_;}
    public String getbookCode(){return bookCode;}
    
    public void setbookAuthor(String bookAuthor_){this.bookAuthor = bookAuthor_;}
    public String getbookAuthor(){return bookAuthor;}
    
    public void setbookYear(int bookYear_){this.bookYear = bookYear_;}
    public int getbookYear(){return bookYear;}
    
    @Override
    public String toString() {
    return bookName;
    }
}
