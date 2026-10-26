package Arqs.Singleton;

public class MainDB {
    public static void main(String[] args) {
        ConnexioDB connect = ConnexioDB.getInstance();
        connect.setTipus("a");
        System.out.println("main connect, tipus " + connect.getTipus());
        con();
        System.out.println("main connect, tipus " + connect.getTipus());
    }

    public static void con(){
        ConnexioDB connect = ConnexioDB.getInstance();
        connect.setTipus("b");
        System.out.println("metode connect, tipus " + connect.getTipus());
    }
}
