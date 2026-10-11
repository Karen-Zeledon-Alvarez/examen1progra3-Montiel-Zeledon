package Model;

import java.time.LocalDate;

public class PrestamoModel {
    
    private String bookCode, state;
    private LocalDate prestamoDate, limitDate, returnDate;

    public void setBookCode(String bookCode) {this.bookCode = bookCode;}
    public String getBookCode() {return bookCode;}

    public void setPrestamoDate(LocalDate prestamoDate) {this.prestamoDate = prestamoDate;}
    public LocalDate getPrestamoDate() {return prestamoDate;}

    public void setLimitDate(LocalDate limitDate) {this.limitDate = limitDate;}
    public LocalDate getLimitDate() {return limitDate;}

    public void setReturnDate(LocalDate returnDate) {this.returnDate = returnDate;}
    public LocalDate getReturnDate() {return returnDate;}

    public void setState(String state) {this.state = state;}
    public String getState() {return state;} 
}
