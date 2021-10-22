package edu.unbosque.taller4.services;

import edu.unbosque.taller4.dtos.Owner;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class OwnersService {

    // Objects for handling connection
    private Connection conn;

    public OwnersService(Connection conn) {
        this.conn = conn;
    }

    public void updateOwner(Owner owner) {

        // Objects for handling SQL statement
        Statement stmt = null;

        try {

            // Executing a SQL query
            System.out.println("=> Updating owner...");
            stmt = conn.createStatement();
            String sql = "UPDATE Owner SET name = '" + owner.getName() + "' WHERE person_id = " + owner.getPerson_id();
            System.out.println(sql);
            int rowsUpdated = stmt.executeUpdate(sql);

            // Printing results
            System.out.println("Rows updated: " + rowsUpdated + "\n");

            // Closing resources
            stmt.close();

        } catch(SQLException se) {
            se.printStackTrace(); // Handling errors from database
        } finally {
            // Cleaning-up environment
            try {
                if(stmt != null) stmt.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }

}
