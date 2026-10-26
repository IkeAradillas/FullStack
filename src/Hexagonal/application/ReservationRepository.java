package Hexagonal.application;

import Hexagonal.domain.Book;
import Hexagonal.domain.Reservation;
import Hexagonal.domain.User;

import java.io.IOException;
import java.util.List;


public interface ReservationRepository {

    void guardar(Reservation r) throws IOException;

    List<Reservation> findAll() throws IOException;

    List<Book> findBooks() throws IOException;

    List<User> findUsers() throws IOException;

    int crearLlibre(String llibre) throws IOException;

    int altaUser(String nom) throws IOException;

    public int comprobarLlibre(String llibre);
}
