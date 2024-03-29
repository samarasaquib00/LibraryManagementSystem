import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program represents a software system for a library
 */

public class Catalog {
    //fields
    private ArrayList<Book> catalog = new ArrayList<>();

    //default constructor
    public Catalog() {
    }

    //methods
    public void loadCatalog(File bookCatalog) throws UnsupportedFileException {
        //Book b = new Book();

        try {
            FileReader fr = new FileReader(bookCatalog);
            BufferedReader bfr = new BufferedReader(fr);
            //ArrayList<String> string = new ArrayList<>();
            while (true) {

                String line = null;

                String[] row = line.split(", ", -1);
                //string.add(row[0]);
                String s1 = row[0]; //int
                int p1 = Integer.parseInt(s1);
                String p2 = row[1];
                String p3 = row[2];
                String p4 = row[3];
                String p5 = row[4];
                String p6 = row[5];
                String s7 = row[6]; //int
                int p7 = Integer.parseInt(s7);
                String s8 = row[7]; //int
                int p8 = Integer.parseInt(s8);
                String s9 = row[8]; //double
                double p9 = Double.parseDouble(s9);
                String s10 = row[9]; //boolean
                boolean p10 = true;
                if (s10.equalsIgnoreCase("no")) {
                    p10 = false;
                }
                if (s1.isEmpty() || p2.isEmpty() || p3.isEmpty() || p4.isEmpty() ||
                        p5.isEmpty() || p6.isEmpty() || s7.isEmpty() || s8.isEmpty() ||
                        s9.isEmpty() || s10.isEmpty()) {
                    throw new UnsupportedFileException("The input file is not valid");
                } else {
                    Book b = new Book(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10);
                    catalog.add(b);
                }
            }
            //bfr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeCatalog(File bookCatalog) throws FileNotFoundException {
        //finish later
        FileOutputStream fos = new FileOutputStream(bookCatalog);
        PrintWriter pw = new PrintWriter(fos);
        //    public Book(int id, String title, String authorFirstName, String authorLastName,
        //                String publisher, String publisherCity, int publicationYear,
        //                int numCheckouts, double purchasePrice, boolean currentlyLoaned)
        for (int i = 0; i < catalog.size(); i++) {
            String lastBool = "";
            if (!catalog.get(i).isCurrentlyLoaned()) {
                lastBool = "No";
            } else {
                lastBool = "Yes";
            }
            pw.printf("%d, %s, %s, %s, %s, %s, %d, %d, %.2f, %s\n", catalog.get(i).getId(),
                    catalog.get(i).getTitle(), catalog.get(i).getAuthorFirstName(),
                    catalog.get(i).getAuthorLastName(), catalog.get(i).getPublisher(),
                    catalog.get(i).getPublisherCity(), catalog.get(i).getPublicationYear(),
                    catalog.get(i).getNumCheckouts(), catalog.get(i).getPurchasePrice(), lastBool);
        }
        pw.close();
    }

    public void addBook(Book book) {
        catalog.add(book);
    }

    public boolean removeBook(int id) {
        int index = -1;
        for (int i = 0; i < catalog.size(); i++) {
            if (catalog.get(i).getId() == id) {
                index = i;
            }
        }

        if (index != -1) {
            catalog.remove(catalog.get(index));
            return true;
        } else {
            return false;
        }
    }

    public boolean loanBook(int id) {
        int loaned = -1;
        for (int i = 0; i < catalog.size(); i++) {
            if (catalog.get(i).getId() == id && !catalog.get(i).isCurrentlyLoaned()) {
                catalog.get(i).setCurrentlyLoaned(true);
                catalog.get(i).setNumCheckouts(catalog.get(i).getNumCheckouts() + 1);
                loaned = 1;
            }
        }
        return loaned == 1;
    }

    public ArrayList<Book> findBooks(String search) {
        //title, authorFirstName, authorLastName, publisher, publisherCity
        search = search.toLowerCase();
        ArrayList<Book> list = new ArrayList<>();
        for (int i = 0; i < catalog.size(); i++) {
            if (catalog.get(i).getTitle().toLowerCase().contains(search) ||
                    catalog.get(i).getAuthorFirstName().toLowerCase().contains(search) ||
                    catalog.get(i).getAuthorLastName().toLowerCase().contains(search) ||
                    catalog.get(i).getPublisher().toLowerCase().contains(search) ||
                    catalog.get(i).getPublisherCity().toLowerCase().contains(search)) {
                list.add(catalog.get(i));
            }
        }
        return list;
    }

    public ArrayList<Book> bookSale() {
        ArrayList<Book> booksForSale = new ArrayList<>();
        for (int i = 0; i < catalog.size(); i++) {
            if (catalog.get(i).getNumCheckouts() < 10 && !catalog.get(i).isCurrentlyLoaned()) {
                booksForSale.add(catalog.get(i));
            }
        }
        return booksForSale;
    }

    public double avgCostPerLoan() {
        double sum = 0.0;
        for (int i = 0; i < catalog.size(); i++) {
            sum += catalog.get(i).averageLoanCost();
        }
        return sum / catalog.size();
    }

    public void printCatalogToFile(int mode, File newFile) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(newFile);
        PrintWriter pw = new PrintWriter(fos);
        if (mode == 1) {            //MLA
            for (int i = 0; i < catalog.size(); i++) {
                pw.printf("MLA: %s, %s. %s. %s, %d.\n", catalog.get(i).getAuthorLastName(),
                        catalog.get(i).getAuthorFirstName(), catalog.get(i).getTitle(),
                        catalog.get(i).getPublisher(), catalog.get(i).getPublicationYear());
            }
        } else if (mode == 2) {     //APA
            for (int i = 0; i < catalog.size(); i++) {
                pw.printf("APA: %s, %c. (%d). %s. %s: %s.\n", catalog.get(i).getAuthorLastName(),
                        catalog.get(i).getAuthorFirstName().charAt(0), catalog.get(i).getPublicationYear(),
                        catalog.get(i).getTitle(), catalog.get(i).getPublisherCity(),
                        catalog.get(i).getPublisher());
            }
        } else if (mode == 3) {     //CS
            for (int i = 0; i < catalog.size(); i++) {
                String checkedOut;
                if (catalog.get(i).isCurrentlyLoaned()) {
                    checkedOut = "Yes";
                } else {
                    checkedOut = "No";
                }
                pw.printf("CS: %s, %s. %s. Number of Checkouts: %d. Currently Checked Out: %s.\n",
                        catalog.get(i).getAuthorLastName(), catalog.get(i).getAuthorFirstName(),
                        catalog.get(i).getTitle(), catalog.get(i).getNumCheckouts(), checkedOut);
            }
        }
        pw.close();
    }

    public int numBooksLoaned() {
        int num = 0;
        for (int i = 0; i < catalog.size(); i++) {
            if (catalog.get(i).isCurrentlyLoaned()) {
                num += 1;
            }
        }
        return num;
    }

    //getter
    public ArrayList<Book> getCatalog() {
        return catalog;
    }

    //setter
    public void setCatalog(ArrayList<Book> catalog) {
        this.catalog = catalog;
    }

/*
    public static void main(String[] args) throws UnsupportedFileException, FileNotFoundException {

        //Check loadCatalog
        Catalog c = new Catalog();
        c.loadCatalog(new File("book"));
        c.writeCatalog(new File("text1"));

        //check findBooks
        System.out.println(c.findBooks("habits"));
        //System.out.println(c.numBooksLoaned());

        //check printing
        //c.printCatalogToFile(1, new File("mla"));
        //c.printCatalogToFile(2, new File("apa"));
        //c.printCatalogToFile(3, new File("cs"));

    }
*/

}