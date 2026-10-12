/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author karen
 */
public class BookManager {
    
    private String CodigoEjemplar;
    private AddBookModel Libro;
    private String Estado;
    
    public BookManager(){
    }
    
    public BookManager(String codigo){
        this.CodigoEjemplar=codigo;
    }
    
    public BookManager(String codigo, AddBookModel libro, String estado){
        this.CodigoEjemplar=codigo;
        this.Libro=libro;
        this.Estado=estado;
    }

    public String getCodigoEjemplar() {
        return CodigoEjemplar;
    }

    public void setCodigoEjemplar(String CodigoEjemplar) {
        this.CodigoEjemplar = CodigoEjemplar;
    }

    public AddBookModel getLibro() {
        return Libro;
    }

    public void setLibro(AddBookModel Libro) {
        this.Libro = Libro;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String Estado) {
        this.Estado = Estado;
    }
    
    
}
