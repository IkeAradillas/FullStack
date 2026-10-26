package Lambdas;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

public class Alumne implements Comparable<Alumne>{
    private LocalDate dataNaixament;
    private String nom;

    public Alumne(LocalDate dataNaixament, String nom) {
        this.dataNaixament = dataNaixament;
        this.nom = nom;
    }

    public Alumne(String nom) {
        this.nom = nom;
    }

    public LocalDate getDataNaixament() {
        return dataNaixament;
    }

    public void setDataNaixament(LocalDate dataNaixament) {
        this.dataNaixament = dataNaixament;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Lambdas.Alumne{" +
                "dataNaixament=" + dataNaixament +
                ", nom='" + nom + '\'' +
                '}';
    }

    @Override
    public int compareTo(Alumne o) {
        if(dataNaixament.isBefore(o.getDataNaixament())) return -1;
        else if(dataNaixament.isAfter(o.getDataNaixament())) return 1;
        else return 0;
    }


    public static void main(String[] args) throws InterruptedException {
        Alumne a1 = new Alumne(LocalDate.of(1990,5,20),"Rubén");
        Alumne a2 = new Alumne(LocalDate.of(1994,6,2),"Raimón");
        Alumne a3 = new Alumne(LocalDate.of(1980,1,1),"Kevin");

        List<Alumne> llistaAlumnes = Arrays.asList(a1,a2,a3);

        //classe anònima
        llistaAlumnes.forEach(new Consumer<Alumne>() {
            @Override
            public void accept(Alumne alumne) {
                System.out.println(alumne);
            }
        });
        //lambda sense simplificar
        llistaAlumnes.forEach((Alumne alumne) -> { System.out.println(alumne);});
        //lambda simplificada
        llistaAlumnes.forEach(alumne ->  System.out.println(alumne));
        //method reference
        llistaAlumnes.forEach(System.out::println);

        Collections.sort(llistaAlumnes);
        llistaAlumnes.forEach(System.out::println);
        System.out.println("------------------------------------------");
        Collections.sort(llistaAlumnes, new Comparator<Alumne>() {
            @Override
            public int compare(Alumne o1, Alumne o2) {
                return o1.getNom().compareTo(o2.getNom());
            }
        });


        Collections.sort(llistaAlumnes,( o1,  o2) ->  o1.getNom().compareTo(o2.getNom()));
        llistaAlumnes.forEach(System.out::println);


        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hola sóc un procés");
            }
        };

        Runnable r2 = () ->  System.out.println("Hola sóc el procés 2");
        r2.run();

        Thread th = new Thread(() -> System.out.println("soc el thread"));
        th.start();
        th.join();

        System.out.println("fi");


        long num = llistaAlumnes.stream().filter(a -> a.getDataNaixament().getYear()<1995)
                .count();
        long qqq = llistaAlumnes.stream().filter(a -> a.getNom().contains("R")).count();
        System.out.println(num);

        List<String> noms = Arrays.asList("Joan","Maria","Pere","Anna","Pau","Marta");
        List<Alumne> al = noms.stream()
                .map(Alumne::new)
                .toList();

    }


}
