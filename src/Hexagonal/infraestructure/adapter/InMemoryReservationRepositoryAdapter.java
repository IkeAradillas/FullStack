package Hexagonal.infraestructure.adapter;

import Hexagonal.application.ReservationRepository;
import Hexagonal.domain.Book;
import Hexagonal.domain.Reservation;
import Hexagonal.domain.User;

import java.util.ArrayList;
import java.util.List;

public class InMemoryReservationRepositoryAdapter implements ReservationRepository {
    private int idUser = 0;
    private int idBook = 0;
    private List<Reservation> reservas = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Book> books = new ArrayList<>();

    @Override
    public void guardar(Reservation r) {
        reservas.add(r);
    }

    @Override
    public List<Reservation> findAll() {
        return reservas;
    }

    @Override
    public List<User> findUsers() {
        return users;
    }

    @Override
    public List<Book> findBooks() {
        return books;
    }

    @Override
    public int comprobarLlibre(String llibre){
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getNom().equals(llibre)) return i;
        }

        return -1;
    }

    @Override
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

    @Override
    public int altaUser(String nom){
        User u = new User(idUser, nom);
        users.add(idUser, u);
        int idUsuari = idUser;
        idUser++;
        return idUsuari;
    }
}
