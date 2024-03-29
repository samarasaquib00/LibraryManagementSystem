/**
 * This program represents a software system for a library
 */
public class Book {
    //fields
    private int id;
    private String title;
    private String authorFirstName;
    private String authorLastName;
    private String publisher;
    private String publisherCity;
    private int publicationYear;
    private int numCheckouts;
    private double purchasePrice;
    private boolean currentlyLoaned;

    //default constructor
    public Book() {
    }

    public Book(int id, String title, String authorFirstName, String authorLastName,
                String publisher, String publisherCity, int publicationYear,
                int numCheckouts, double purchasePrice, boolean currentlyLoaned) {
        this.id = id;
        this.title = title;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.publisher = publisher;
        this.publisherCity = publisherCity;
        this.publicationYear = publicationYear;
        this.numCheckouts = numCheckouts;
        this.purchasePrice = purchasePrice;
        this.currentlyLoaned = currentlyLoaned;
    }

    //methods
    public double averageLoanCost() {
        return purchasePrice / numCheckouts;
    }

    //getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublisherCity() {
        return publisherCity;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public int getNumCheckouts() {
        return numCheckouts;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public boolean isCurrentlyLoaned() {
        return currentlyLoaned;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublisherCity(String publisherCity) {
        this.publisherCity = publisherCity;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public void setNumCheckouts(int numCheckouts) {
        this.numCheckouts = numCheckouts;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setCurrentlyLoaned(boolean currentlyLoaned) {
        this.currentlyLoaned = currentlyLoaned;
    }
}
