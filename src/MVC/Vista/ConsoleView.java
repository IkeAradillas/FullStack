package MVC.Vista;

import MVC.Controlador.ReservationController;
import MVC.Model.Reservation;

import java.util.Scanner;

public class ConsoleView {
    Scanner sc = new Scanner(System.in);
    ReservationController rc = new ReservationController();

    public void inici(){
        print("¡Bienvenido a la Biblioteca!");
        bucle:
        while (true){
            switch (menuOpcio()){
                case 1 -> reservar();
                case 2 -> llistar();
                case 0 -> {
                    print("¡Hasta la próxima!");
                    break bucle;
                }
                default -> print("Error: Has escogido una opción no valida");
            }
        }
    }

    public void print(String text){
        System.out.println(text);
    }

    public int menuOpcio(){
        System.out.println("Escoje una opción");
        System.out.println("1. Reservar");
        System.out.println("2. Lista de reservas");
        System.out.println("0. Salir");
        int opcio = sc.nextInt();
        sc.nextLine();
        return opcio;
    }

    public String ask(String text){
        System.out.println(text);
        String a = sc.nextLine();
        return a;
    }

    public int menu2(){
        System.out.println("Que quieres mostrar?");
        System.out.println("1. Todas las reservas");
        System.out.println("2. Todas las reservas hechas por un usuario");
        System.out.println("3. Todas las reservas hechas por el mismo libro");
        System.out.println("0. Volver atras");
        int opcio = sc.nextInt();
        sc.nextLine();
        return opcio;
    }

    public void reservar() {
        String a = ask("Has reservat un altre vegada?");
        if (a.equalsIgnoreCase("si") || a.equalsIgnoreCase("sí")){
            int id = Integer.parseInt(ask("¿Cual es tu ID?"));
            if (rc.comprobarID(id)) print("Error: No existe ningun usuario con este ID");
            else {
                String nomBook = ask("¿Cual es el nombre del libro?");
                Reservation r = rc.reservar(id, nomBook);
                print("Tu reserva se ha confirmado: " + r.getU().getNom() + " | " + r.getB().getNom());
            }
        }
        else{
            String nomUser = ask("¿Cual es tu nombre?");
            String nomBook = ask("¿Cual es el nombre del libro?");
            Reservation r = rc.reservar(nomUser, nomBook);
            print("Tu reserva se ha confirmado: " + r.getU().getNom() + " | " + r.getB().getNom());
        }
    }

    public void llistar(){
        bucle:
        while (true){
            switch (menu2()){
                case 1 -> mostrarReservas();
                case 2 -> mostrarReservasUser();
                case 3 -> mostrarReservasLlibre();
                case 0 -> {
                    break bucle;
                }
                default -> print("Error: Has escogido una opción no valida");
            }
        }
    }

    public void mostrarReservas(){
        for (int i = 0; i < rc.getReservas().size(); i++) {
            print("La reserva con numero: " + i + " es del usuario con ID " + rc.getReservas().get(i).getU().getId() + " | " + rc.getReservas().get(i).getU().getNom() + " ha reservado el libro " + rc.getReservas().get(i).getB().getNom());
        }
    }

    public void mostrarReservasUser(){
        int id = Integer.parseInt(ask("¿Que ID quieres ver?"));
        if (rc.comprobarID(id)) print("Error: No existe ningun usuario con este ID");
        else {
            print("El usuario con id: " + id + " ha reservado los siguientes libros: ");
            for (int i = 0; i < rc.getUsers().get(id).getReservados().size(); i++) {
                print(rc.getUsers().get(id).getReservados().get(i).getId() + " | " + rc.getUsers().get(id).getReservados().get(i).getNom());
            }
        }
    }

    public void mostrarReservasLlibre(){
        String nom = ask("¿Que libro quieres ver?");
        int id = rc.comprobarLlibre(nom);

        if (id<0) print("No existe este libro");
        else {
            print("Este libro ha sido reservado por: ");
            for (int i = 0; i < rc.getBooks().get(id).getUsers().size(); i++) {
                print(rc.getBooks().get(id).getUsers().get(i).getId() + " | " + rc.getBooks().get(id).getUsers().get(i).getNom());
            }
        }
    }
}
