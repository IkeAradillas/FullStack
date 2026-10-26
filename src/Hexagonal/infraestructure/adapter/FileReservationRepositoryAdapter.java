package Hexagonal.infraestructure.adapter;

import Hexagonal.application.ReservationRepository;
import Hexagonal.domain.Book;
import Hexagonal.domain.Reservation;
import Hexagonal.domain.User;

import java.io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReservationRepositoryAdapter implements ReservationRepository {
    private int idUser = 0;
    private int idBook = 0;

    private List<Reservation> reservas = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Book> books = new ArrayList<>();

    private final File dataFolderR = new File("src/Hexagonal/data");
    private final File contactFolderR = new File(dataFolderR, "reservas.txt");
    private final File contactFolderU = new File(dataFolderR, "users.txt");
    private final File contactFolderB = new File(dataFolderR, "books.txt");

    public FileReservationRepositoryAdapter() throws IOException {
        dataFolderR.mkdirs();
        contactFolderU.createNewFile();
        contactFolderB.createNewFile();
        contactFolderR.createNewFile();

        cargarUsers();
        cargarBooks();
        cargarReservas();
    }


    private List<String> leer(File archivo) throws IOException {
        List<String> lineas = new ArrayList<>();
        Scanner sc = new Scanner(archivo);

        while (sc.hasNextLine()) lineas.add(sc.nextLine());

        sc.close();
        return lineas;
    }

    private void escribir(File archivo, List<String> lineas) throws IOException {
        FileWriter fw = new FileWriter(archivo);

        for (String linea : lineas) {
            fw.write(linea + "\n");
        }

        fw.close();
    }

    private void cargarUsers() throws IOException {
        users.clear();
        idUser = 0;

        for (String linea : leer(contactFolderU)) {
            String[] datos = linea.split(";", -1);

            if (datos.length >= 2) {
                int id = Integer.parseInt(datos[0]);
                User u = new User(id, datos[1]);

                users.add(u);
                idUser = Math.max(idUser, id + 1);
            }
        }
    }

    private void cargarBooks() throws IOException {
        books.clear();
        idBook = 0;

        for (String linea : leer(contactFolderB)) {
            String[] datos = linea.split(";", -1);

            if (datos.length >= 2) {
                int id = Integer.parseInt(datos[0]);
                Book b = new Book(id, datos[1]);

                books.add(b);
                idBook = Math.max(idBook, id + 1);
            }
        }
    }

    private void cargarReservas() throws IOException {
        reservas.clear();

        for (String linea : leer(contactFolderR)) {
            String[] datos = linea.split(";", -1);

            if (datos.length == 4) {
                int idUser = Integer.parseInt(datos[0]);
                int idBook = Integer.parseInt(datos[2]);

                User u = users.get(idUser);
                Book b = books.get(idBook);

                Reservation r = new Reservation(u, b);
                reservas.add(r);

                u.addBook(b);
                b.addUser(u);
            }
        }
    }

    private void guardarUsers() throws IOException {
        List<String> lineas = new ArrayList<>();

        for (User u : users) {
            StringBuilder libros = new StringBuilder();

            for (Book b : u.getReservados()) {
                if (!libros.isEmpty()) {
                    libros.append(",");
                }
                libros.append(b.getId()).append(":").append(b.getNom());
            }

            lineas.add(u.getId() + ";" + u.getNom() + ";" + libros);
        }

        escribir(contactFolderU, lineas);
    }

    private void guardarBooks() throws IOException {
        List<String> lineas = new ArrayList<>();

        for (Book b : books) {
            StringBuilder usuarios = new StringBuilder();

            for (User u : b.getUsers()) {
                if (!usuarios.isEmpty()) {
                    usuarios.append(",");
                }

                usuarios.append(u.getId()).append(":").append(u.getNom());
            }

            lineas.add(b.getId() + ";" + b.getNom() + ";" + usuarios);
        }

        escribir(contactFolderB, lineas);
    }

    @Override
    public void guardar(Reservation r) throws IOException {
        reservas.add(r);

        FileWriter fw = new FileWriter(contactFolderR, true);

        fw.write(r.getU().getId() + ";" + r.getU().getNom() + ";" + r.getB().getId() + ";" + r.getB().getNom() + ";");
        fw.close();

        guardarUsers();
        guardarBooks();
    }

    @Override
    public List<Reservation> findAll() {
        return new ArrayList<>(reservas);
    }

    @Override
    public List<Book> findBooks() {
        return new ArrayList<>(books);
    }

    @Override
    public List<User> findUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public int comprobarLlibre(String llibre) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getNom().equals(llibre)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int crearLlibre(String llibre) throws IOException {
        int idLlibre = comprobarLlibre(llibre);

        if (idLlibre == -1) {
            Book b = new Book(idBook, llibre);
            books.add(b);
            idLlibre = idBook;
            idBook++;
            guardarBooks();
        }
        return idLlibre;
    }

    @Override
    public int altaUser(String nom) throws IOException {
        User u = new User(idUser, nom);
        users.add(u);

        int idUsuari = idUser;
        idUser++;

        guardarUsers();
        return idUsuari;
    }
}
