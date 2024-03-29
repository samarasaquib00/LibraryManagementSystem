import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ManageBooksGUI {
    private JPanel mainPanel;
    private JTable bookTable;
    private DefaultTableModel tableModel;
    private JButton addButton;
    private JButton removeButton;
    private JButton loanButton;
    private JButton backButton; // New back button
    private JTextField searchField;
    private JButton searchButton;

    private Catalog catalog;
    private JFrame parentFrame; // Reference to the parent frame

    public ManageBooksGUI(Catalog catalog, JFrame parentFrame) {
        this.catalog = catalog;
        this.parentFrame = parentFrame;
        mainPanel = new JPanel(new BorderLayout());

        // Table for displaying books
        String[] columnNames = {"ID", "Title", "Author", "Publisher", "Publication Year", "Checkouts", "Price", "Loaned"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(bookTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // Buttons for actions
        JPanel buttonPanel = new JPanel(new FlowLayout());
        addButton = new JButton("Add Book");
        removeButton = new JButton("Remove Book");
        loanButton = new JButton("Loan Book");
        backButton = new JButton("Back"); // Initialize back button
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(loanButton);
        buttonPanel.add(backButton); // Add back button to the panel
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Search functionality
        JPanel searchPanel = new JPanel(new FlowLayout());
        searchField = new JTextField(20);
        searchButton = new JButton("Search");
        searchPanel.add(new JLabel("Search: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        mainPanel.add(searchPanel, BorderLayout.NORTH);

        // Populate table with existing books
        populateBookTable();

        // Add action listeners
        /*addButton.addActionListener(e -> {
            // Open add book dialog
            AddBookDialog dialog = new AddBookDialog(parentFrame, catalog, this);
            dialog.setVisible(true);
        });*/

        removeButton.addActionListener(e -> {
            // Remove selected book
            int selectedRow = bookTable.getSelectedRow();
            if (selectedRow != -1) {
                int bookId = (int) bookTable.getValueAt(selectedRow, 0);
                catalog.removeBook(bookId);
                populateBookTable();
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Please select a book to remove.");
            }
        });

        loanButton.addActionListener(e -> {
            // Loan selected book
            int selectedRow = bookTable.getSelectedRow();
            if (selectedRow != -1) {
                int bookId = (int) bookTable.getValueAt(selectedRow, 0);
                catalog.loanBook(bookId);
                populateBookTable();
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Please select a book to loan.");
            }
        });

        searchButton.addActionListener(e -> {
            // Search for books
            String searchString = searchField.getText().trim();
            ArrayList<Book> searchResults = catalog.findBooks(searchString);
            updateBookTable(searchResults);
        });

        // Add action listener for the back button
        backButton.addActionListener(e -> {
            // Go back to the main menu
            parentFrame.dispose(); // Close the current frame
            new LibraryGUI(catalog); // Open the main menu
        });
    }

    // Populate table with books from catalog
    private void populateBookTable() {
        tableModel.setRowCount(0);
        ArrayList<Book> books = catalog.getCatalog();
        for (Book book : books) {
            Object[] rowData = {book.getId(), book.getTitle(), book.getAuthorFirstName() + " " + book.getAuthorLastName(),
                    book.getPublisher(), book.getPublicationYear(), book.getNumCheckouts(), book.getPurchasePrice(),
                    book.isCurrentlyLoaned() ? "Yes" : "No"};
            tableModel.addRow(rowData);
        }
    }

    // Update table with search results
    public void updateBookTable(ArrayList<Book> books) {
        tableModel.setRowCount(0);
        for (Book book : books) {
            Object[] rowData = {book.getId(), book.getTitle(), book.getAuthorFirstName() + " " + book.getAuthorLastName(),
                    book.getPublisher(), book.getPublicationYear(), book.getNumCheckouts(), book.getPurchasePrice(),
                    book.isCurrentlyLoaned() ? "Yes" : "No"};
            tableModel.addRow(rowData);
        }
    }

    // Get the main panel of ManageBooksGUI
    public JPanel getMainPanel() {
        return mainPanel;
    }
}
