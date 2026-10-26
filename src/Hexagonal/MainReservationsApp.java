package Hexagonal;

import Hexagonal.application.ControllerReservation;
import Hexagonal.infraestructure.adapter.FileReservationRepositoryAdapter;
import Hexagonal.infraestructure.adapter.InMemoryReservationRepositoryAdapter;

import java.io.IOException;

public class MainReservationsApp {
    public static void main(String[] args) {
        try {
            InMemoryReservationRepositoryAdapter m = new InMemoryReservationRepositoryAdapter();
            FileReservationRepositoryAdapter f = new FileReservationRepositoryAdapter();

            ControllerReservation c = new ControllerReservation(f);
            c.inici();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }
}
