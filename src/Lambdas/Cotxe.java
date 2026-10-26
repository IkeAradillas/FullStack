package Lambdas;

public class Cotxe {
    private String marca;
    public Cotxe(String m) {
        marca = m;
    }

    public String getMarca() {
        return marca;
    }

    @Override
    public String toString() {
        return "Lambdas.Cotxe{" +
                "marca='" + marca + '\'' +
                '}';
    }
}
