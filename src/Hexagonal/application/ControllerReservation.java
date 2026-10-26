package Hexagonal.application;

import Hexagonal.domain.Reservation;
import Hexagonal.domain.ReservationService;

import java.io.IOException;
import java.util.Scanner;

public class ControllerReservation {
    Scanner sc = new Scanner(System.in);
    ReservationService rs;

    public ControllerReservation(ReservationRepository rr){
        rs = new ReservationService(rr);
    }

    public void print(String text){
        System.out.println(text);
    }

    public int menuOpcio(){
        print("Escoje una opción");
        print("1. Reservar");
        print("2. Lista de reservas");
        print("0. Salir");
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
        print("Que quieres mostrar?");
        print("1. Todas las reservas");
        print("2. Todas las reservas hechas por un usuario");
        print("3. Todas las reservas hechas por el mismo libro");
        print("0. Volver atras");
        int opcio = sc.nextInt();
        sc.nextLine();
        return opcio;
    }

    public void inici() throws IOException {
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

    public void reservar() throws IOException {
        String a = ask("Has reservat un altre vegada?");
        if (a.equalsIgnoreCase("si") || a.equalsIgnoreCase("sí")){
            int id = Integer.parseInt(ask("¿Cual es tu ID?"));
            if (id>= rs.getUsers().size() || id<0) print("Error: No existe ningun usuario con este ID");
            else {
                String nomBook = ask("¿Cual es el nombre del libro?");
                Reservation r = rs.reserva(id, nomBook);
                print("Tu reserva se ha confirmado: " + r.getU().getNom() + " | " + r.getB().getNom());
            }
        }
        else{
            String nomUser = ask("¿Cual es tu nombre?");
            String nomBook = ask("¿Cual es el nombre del libro?");
            Reservation r = rs.reserva(nomUser, nomBook);
            print("Tu reserva se ha confirmado: " + r.getU().getNom() + " | " + r.getB().getNom());
        }
    }

    public void llistar() throws IOException {
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

    public void mostrarReservas() throws IOException {
        for (int i = 0; i < rs.getReservas().size(); i++) {
            print("La reserva con numero: " + i + " es del usuario con ID " + rs.getReservas().get(i).getU().getId() + " | " + rs.getReservas().get(i).getU().getNom() + " ha reservado el libro " + rs.getReservas().get(i).getB().getNom());
        }
    }

    public void mostrarReservasUser() throws IOException {
        int id = Integer.parseInt(ask("¿Que ID quieres ver?"));
        if (id>=rs.getUsers().size()) print("Error: No existe ningun usuario con este ID");
        else {
            print("El usuario con id: " + id + " llamado " +  rs.getUsers().get(id).getNom() +" ha reservado los siguientes libros: ");
            for (int i = 0; i < rs.getUsers().get(id).getReservados().size(); i++) {
                print(rs.getUsers().get(id).getReservados().get(i).getId() + " | " + rs.getUsers().get(id).getReservados().get(i).getNom());
            }
        }
    }

    public void mostrarReservasLlibre() throws IOException {
        String nom = ask("¿Que libro quieres ver?");
        int id = rs.comprobarLlibre(nom);

        if (id<0) print("No existe este libro");
        else {
            print("Este libro ha sido reservado por: ");
            for (int i = 0; i < rs.getBooks().get(id).getUsers().size(); i++) {
                print(rs.getBooks().get(id).getUsers().get(i).getId() + " | " + rs.getBooks().get(id).getUsers().get(i).getNom());
            }
        }
    }




}
