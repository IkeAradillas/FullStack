package Arqs.Singleton;

public class ConnexioDB {
    private String tipus;
    static ConnexioDB instance = null;

    private ConnexioDB(){}

    private ConnexioDB(String tipus){
        this.tipus=tipus;

        // Codi connexio bbdd
    }

    public static ConnexioDB getInstance(){
        if (instance==null) instance= new ConnexioDB();
        return instance;
    }

    public String getTipus() {
        return tipus;
    }

    public void setTipus(String tipus) {
        this.tipus = tipus;
    }
}
