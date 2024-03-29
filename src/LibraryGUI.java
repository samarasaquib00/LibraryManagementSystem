import javax.swing.*;
import java.awt.*;

/**
 * This program represents a software system for a library
 */

public class LibraryGUI {
    private JFrame frame;
    private JPanel mainPanel;
    private JButton bookManagementButton;
    private JButton userManagementButton;
    private JButton fileOperationsButton;
    private Catalog catalog;
    private ManageBooksGUI manageBooksGUI;

    public LibraryGUI(Catalog catalog) {
        this.catalog = catalog;
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        mainPanel = new JPanel(new CardLayout());

        // Create main menu panel
        JPanel menuPanel = new JPanel(new GridLayout(3, 1));
        bookManagementButton = new JButton("Manage Books");
        bookManagementButton.addActionListener(e -> showManageBooksPanel());
        userManagementButton = new JButton("Manage Users");
        userManagementButton.addActionListener(e -> {
            // Open user management window
        });
        fileOperationsButton = new JButton("Manage Files");
        fileOperationsButton.addActionListener(e -> {
            // Open file operations window
        });
        menuPanel.add(bookManagementButton);
        menuPanel.add(userManagementButton);
        menuPanel.add(fileOperationsButton);
        mainPanel.add(menuPanel, "menu");

        // Create manage books panel
        manageBooksGUI = new ManageBooksGUI(catalog, frame);
        mainPanel.add(manageBooksGUI.getMainPanel(), "manageBooks");

        // Initially show the main menu panel
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "menu");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    // Show the manage books panel
    private void showManageBooksPanel() {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, "manageBooks");
    }

    public static void main(String[] args) {
        Catalog catalog = new Catalog(); // Assuming you have an instance of Catalog
        SwingUtilities.invokeLater(() -> new LibraryGUI(catalog));
    }
}
