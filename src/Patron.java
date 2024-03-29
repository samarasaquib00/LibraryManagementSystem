import java.util.ArrayList;

/**
 * This program represents a software system for a library
 */

public class Patron {
    //fields
    private int id;
    private String name;
    private String address;
    private String email;
    private String phoneNumber;
    private ArrayList<Integer> bookLoanList = new ArrayList<>();

    //default constructor
    public Patron() {
    }

    //constructor
    public Patron(int id, String name, String address, String email,
                  String phoneNumber, ArrayList<Integer> bookLoanList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.bookLoanList = bookLoanList;
    }

    //methods
    public void updateBookLoanList(int bookId) {
        int temp = 0;
        for (int i = 0; i < bookLoanList.size(); i++) {
            if (bookLoanList.get(i) == bookId) {
                bookLoanList.remove(bookLoanList.get(i));
                temp += 1;

            }
        }
        if (temp == 0) {
            bookLoanList.add(bookId);
        }

        /*if (bookLoanList.contains(bookId)) {
            bookLoanList.remove(bookId);
        } else {
            bookLoanList.add(bookId);
        }*/

    }

    //getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public ArrayList<Integer> getBookLoanList() {
        return bookLoanList;
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBookLoanList(ArrayList<Integer> bookLoanList) {
        this.bookLoanList = bookLoanList;
    }
}
