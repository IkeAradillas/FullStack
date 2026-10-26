package MVC.Model;

public class Reservation {
    private User u;
    private Book b;

    public Reservation(User u, Book b){
        this.u = u;
        this.b = b;
    }

    public User getU() {
        return u;
    }

    public Book getB() {
        return b;
    }
}


