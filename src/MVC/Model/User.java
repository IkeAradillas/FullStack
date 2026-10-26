package MVC.Model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String nom;
    private List<Book> reservados;

    public User(int id, String nom){
        this.id = id;
        this.nom=nom;
        reservados = new ArrayList<>();
    }

    public void addBook(Book book){
        reservados.add(book);
    }

    public String getNom() {
        return nom;
    }

    public List<Book> getReservados() {
        return reservados;
    }

    public int getId() {
        return id;
    }
}
