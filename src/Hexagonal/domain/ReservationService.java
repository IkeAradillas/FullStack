package Hexagonal.domain;

import Hexagonal.application.ReservationRepository;

import java.io.IOException;
import java.util.List;

public class ReservationService {
    ReservationRepository rr;

    public ReservationService(ReservationRepository rr) {
        this.rr = rr;
    }

    public Reservation reserva(String nom, String llibre) throws IOException {
        int idLlibre= rr.crearLlibre(llibre);
        int idUsuari = rr.altaUser(nom);


        Reservation r = new Reservation(rr.findUsers().get(idUsuari), rr.findBooks().get(idLlibre));

        rr.findUsers().get(idUsuari).addBook(rr.findBooks().get(idLlibre));
        rr.findBooks().get(idLlibre).addUser(rr.findUsers().get(idUsuari));

        rr.guardar(r);

        return r;
    }


    public Reservation reserva(int idUser, String llibre) throws IOException {
        int idLlibre= rr.crearLlibre(llibre);

        Reservation r = new Reservation(rr.findUsers().get(idUser), rr.findBooks().get(idLlibre));

        rr.findUsers().get(idUser).addBook(rr.findBooks().get(idLlibre));
        rr.findBooks().get(idLlibre).addUser(rr.findUsers().get(idUser));

        rr.guardar(r);

        return r;
    }

    public List<Reservation> getReservas() throws IOException {
        return rr.findAll();
    }

    public List<User> getUsers() throws IOException {
        return rr.findUsers();
    }

    public List<Book> getBooks() throws IOException {
        return rr.findBooks();
    }

    public int comprobarLlibre(String nom){return rr.comprobarLlibre(nom);}



}
