package Model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PrestamoModel {
    
    public static final double MULTA_POR_DIA = 200; 
    private int prestamoCode;
    private BookManager bookCode; 
    private CreateUserModel userCode;
    private String state;
    private Date prestamoDate, limitDate, returnDate;
    private double multa;
    private boolean multaPagada;

    public PrestamoModel(){}
    
    public PrestamoModel(int code){
        this.prestamoCode = code;
    }
    
    public PrestamoModel(int code, BookManager bookCode_, CreateUserModel userCode_, String state_, Date prestamoDate_, Date limitDate_, Date returnDate_){
        
       this.prestamoCode = code;
       this.bookCode = bookCode_;
       this.userCode = userCode_;
       this.state = state_;
       this.prestamoDate = prestamoDate_;
       this.limitDate = limitDate_;
       this.returnDate = returnDate_;
    }

    public void setPrestamoCode(int prestamoCode) { this.prestamoCode = prestamoCode; }
    public int getPrestamoCode() { return prestamoCode; }

    public void setUserCode(CreateUserModel userCode) { this.userCode = userCode; }
    public CreateUserModel getUserCode() { return userCode; }
    
    public void setBookCode(BookManager bookCode) { this.bookCode = bookCode; }
    public BookManager getBookCode() { return bookCode; }

    public void setPrestamoDate(Date prestamoDate) { this.prestamoDate = prestamoDate; }
    public Date getPrestamoDate() { return prestamoDate; }

    public void setLimitDate(Date limitDate) { this.limitDate = limitDate; }
    public Date getLimitDate() { return limitDate; }

    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
    public Date getReturnDate() { return returnDate; }

    public void setState(String state) { this.state = state; }
    public String getState() { return state; }

    public double getMulta() { return multa; }
    public void setMulta(double multa) { this.multa = multa; }

    public boolean getMultaPagada() { return multaPagada; }
    public void setMultaPagada(boolean multaPagada) { this.multaPagada = multaPagada; }

    // RN-N1: calcula la multa según la fecha límite y la de devolución
    public void calcularMulta() {
        
        if (returnDate != null && limitDate != null && returnDate.after(limitDate)) {
            
            long dias = ChronoUnit.DAYS.between(limitDate.toLocalDate(), returnDate.toLocalDate()); multa = dias * MULTA_POR_DIA;}
            else {multa = 0;}
    }

}