import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This program represents a software system for a library
 */

public class TestGui {

    public static void main(String[] args) {
        // Load the catalog from the test file
        Catalog catalog = loadCatalogFromFile("/Users/samara.saquib/Independent Projects/Library Management System/src/books.txt");

        // Create the main frame
        JFrame frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        // Create and add the ManageBooksGUI panel
        ManageBooksGUI manageBooksGUI = new ManageBooksGUI(catalog, frame);
        JPanel manageBooksPanel = manageBooksGUI.getMainPanel();
        frame.add(manageBooksPanel);

        // Display the frame
        frame.setVisible(true);
    }

    // Method to load the catalog from a file
    private static Catalog loadCatalogFromFile(String filename) {
        Catalog catalog = new Catalog();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 10) {
                    int id = Integer.parseInt(parts[0].trim());
                    String title = parts[1].trim();
                    String authorFirstName = parts[2].trim();
                    String authorLastName = parts[3].trim();
                    String publisher = parts[4].trim();
                    String publisherCity = parts[5].trim();
                    int publicationYear = Integer.parseInt(parts[6].trim());
                    int numCheckouts = Integer.parseInt(parts[7].trim());
                    double purchasePrice = Double.parseDouble(parts[8].trim());
                    boolean currentlyLoaned = parts[9].trim().equalsIgnoreCase("Yes");
                    catalog.addBook(new Book(id, title, authorFirstName, authorLastName, publisher,
                            publisherCity, publicationYear, numCheckouts, purchasePrice, currentlyLoaned));
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return catalog;
    }
}
