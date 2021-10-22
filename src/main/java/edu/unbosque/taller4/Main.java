package edu.unbosque.taller4;

import java.sql.*;

import edu.unbosque.taller4.dtos.Owner;
import edu.unbosque.taller4.services.OwnersService;
import edu.unbosque.taller4.services.PetsService;
import edu.unbosque.taller4.services.UsersService;

public class Main {

    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://localhost/fourpaws";

    // Database credentials
    static final String USER = "postgres";
    static final String PASS = "papito";

    public static void main(String[] args) {

        // Objects for handling connection
        Connection conn = null;

        try {

            // Registering the JDBC driver
            Class.forName(JDBC_DRIVER);

            // Opening database connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            UsersService usersService = new UsersService(conn);
            usersService.listUsers();

            PetsService petsService = new PetsService(conn);
            petsService.countBySpecies("Dog");

            OwnersService ownersService = new OwnersService(conn);
            ownersService.updateOwner(new Owner("owner124", "0850",  "Juan Perez", "Cra 64 #94-108", "Usaquen"));

            // Closing database connection
            conn.close();

        } catch(SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } catch(ClassNotFoundException e) {
            e.printStackTrace(); // Handling errors from JDBC driver
        } finally {
            // Cleaning-up environment
            try {
                if(conn != null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }

    }
}
