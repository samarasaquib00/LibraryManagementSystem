import java.util.ArrayList;


/**
 * This program represents a software system for a library
 */
 
public class Employee {
    //fields
    private int id;
    private String name;
    private String address;
    private double hourlyRate;
    private double hoursWorked;
    private ArrayList<String> actionList = new ArrayList<>();

    //default constructor
    public Employee() {
    }

    //constructor
    public Employee(int id, String name, String address, double hourlyRate,
                    double hoursWorked, ArrayList<String> actionList) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.actionList = actionList;
    }

    //methods
    public void recordAction(int patronId, int bookId, int code) {
        actionList.add(patronId + "-" + bookId + "-" + code);
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

    public double getHourlyRate() {
        return hourlyRate;
    }

    public double getHoursWorked() {
        return hoursWorked;
    }

    public ArrayList<String> getActionList() {
        return actionList;
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

    public void setHourlyRate(double hourlyRate) {
        this.hourlyRate = hourlyRate;
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public void setActionList(ArrayList<String> actionList) {
        this.actionList = actionList;
    }
}
