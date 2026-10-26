package MVC.Controlador;

import MVC.Model.Book;
import MVC.Model.ReservationService;
import MVC.Model.Reservation;
import MVC.Model.User;

import java.util.List;

public class ReservationController {
    ReservationService rs = new ReservationService();

    public Reservation reservar(int id, String nomBook){
        Reservation r = rs.reserva(id, nomBook);
        return r;
    }

    public Reservation reservar(String nomUser, String nomBook){
        Reservation r = rs.reserva(nomUser, nomBook);
        return r;
    }

    public boolean comprobarID(int id){
        return id>= rs.getUsers().size() || id<0;
    }

    public List<Reservation> getReservas(){
        return rs.getReservas();
    }

    public List<User> getUsers(){
        return rs.getUsers();
    }

    public List<Book> getBooks(){
        return rs.getBooks();
    }

    public int comprobarLlibre(String llibre){
        return rs.comprobarLlibre(llibre);
    }
}

