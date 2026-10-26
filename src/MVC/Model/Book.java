package MVC.Model;

import java.util.ArrayList;
import java.util.List;

public class Book {
    private int id;
    private String nom;
    private List<User> users;

    public Book(int id, String nom){
        this.id = id;
        this.nom = nom;
        users = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public int getId() {
        return id;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User u){
        users.add(u);
    }
}
