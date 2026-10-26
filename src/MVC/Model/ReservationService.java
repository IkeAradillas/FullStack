package MVC.Model;

import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private int idUser = 0;
    private int idBook = 0;
    private List<Reservation> reservas = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Book> books = new ArrayList<>();

    public Reservation reserva(String nom, String llibre){
        int idLlibre= crearLlibre(llibre);
        int idUsuari = altaUser(nom);


        Reservation r = new Reservation(users.get(idUsuari), books.get(idLlibre));

        users.get(idUsuari).addBook(books.get(idLlibre));
        books.get(idLlibre).addUser(users.get(idUsuari));

        reservas.add(r);

        return r;
    }


    public Reservation reserva(int idUser, String llibre){
        int idLlibre= crearLlibre(llibre);

        Reservation r = new Reservation(users.get(idUser), books.get(idLlibre));

        users.get(idUser).addBook(books.get(idLlibre));
        books.get(idLlibre).addUser(users.get(idUser));

        reservas.add(r);


        return r;
    }

    public int comprobarLlibre(String llibre){
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getNom().equals(llibre)) return i;
        }

        return -1;
    }

    public int crearLlibre(String llibre){
        int idLlibre= comprobarLlibre(llibre);
        if (idLlibre<0){
            Book b = new Book(idBook, llibre);
            books.add(idBook, b);
            idLlibre=idBook;
            idBook++;
        }
        return idLlibre;
    }

    public int altaUser(String nom){
        User u = new User(idUser, nom);
        users.add(idUser, u);
        int idUsuari = idUser;
        idUser++;
        return idUsuari;
    }

    public List<Reservation> getReservas() {
        return reservas;
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Book> getBooks() {
        return books;
    }
}
