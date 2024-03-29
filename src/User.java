import java.io.*;
import java.util.ArrayList;

/**
 * This program represents a software system for a library
 */

public class User {
    //fields
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Patron> patrons = new ArrayList<>();

    //default constructor
    public User() {
    }

    //methods
    public void loadUsers(File employeeRecords, File patronRecords) throws UnsupportedFileException {

        //read employeeRecords
        try {
            FileReader fr = new FileReader(employeeRecords);
            BufferedReader br = new BufferedReader(fr);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] row = line.split(", ", -1);
                String s1 = row[0]; //int
                int p1 = Integer.parseInt(s1);
                String p2 = row[1];
                String p3 = row[2];
                String s4 = row[3]; //double
                double p4 = Double.parseDouble(s4);
                String s5 = row[4]; //double
                double p5 = Double.parseDouble(s5);
                String s6 = row[5];
                ArrayList<String> p6 = new ArrayList<>();
                p6.add(s6);
                if (s1.isEmpty() || p2.isEmpty() || p3.isEmpty() || s4.isEmpty() ||
                        s5.isEmpty() || s6.isEmpty()) {
                    throw new UnsupportedFileException("The input file is not valid");
                } else {
                    Employee e = new Employee(p1, p2, p3, p4, p5, p6);
                    employees.add(e);

                }
            }
            /*for (int i = 0; i < employees.size(); i++) {
                System.out.println("Object " + i + ": " + employees.get(i));
                String action = "";
                for (int j = 0; j < employees.get(i).getActionList().size(); j++) {
                    action = action + employees.get(i).getActionList().get(j) + " ";
                }
                System.out.printf("%d, %s, %s, %.2f, %.0f, %s\n", employees.get(i).getId(), employees.get(i).getName(),
                        employees.get(i).getAddress(), employees.get(i).getHourlyRate(),
                        employees.get(i).getHoursWorked(), action);
            }*/
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //read patronRecords
        try {
            FileReader fr = new FileReader(patronRecords);
            BufferedReader br = new BufferedReader(fr);
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                String[] row = line.split(", ", -1);
                String s1 = row[0]; //int
                int p1 = Integer.parseInt(s1);
                String p2 = row[1];
                String p3 = row[2];
                String p4 = row[3];
                String p5 = row[4];
                String s6 = row[5]; //int then to integer arrayList
                String[] stringArray = s6.split(" ", -1);
                ArrayList<String> stringList = new ArrayList<>();
                for (int i = 0; i < stringArray.length; i++) {
                    stringList.add(stringArray[i]);
                }
                ArrayList<Integer> p6 = new ArrayList<>();
                for (int i = 0; i < stringList.size(); i++) {
                    p6.add(Integer.parseInt(stringList.get(i)));
                }
                if (s1.isEmpty() || p2.isEmpty() || p3.isEmpty() || p4.isEmpty() ||
                        p5.isEmpty() || s6.isEmpty()) {
                    throw new UnsupportedFileException("The input file is not valid");
                } else {
                    Patron p = new Patron(p1, p2, p3, p4, p5, p6);
                    patrons.add(p);
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeUsers(File employeeRecords, File patronRecords) throws FileNotFoundException {
        FileOutputStream fos = new FileOutputStream(employeeRecords);
        PrintWriter pw = new PrintWriter(fos);

        int count = 0;
        for (int i = 0; i < employees.size(); i++) {
            //    public Employee(int id, String name, String address, double hourlyRate,
            //                    double hoursWorked, ArrayList<String> actionList) {
            count += 1;
            String action = "";
            for (int j = 0; j < employees.get(i).getActionList().size(); j++) {
                action = action + employees.get(i).getActionList().get(j) + " ";
            }
            action = action.substring(0, action.length() - 1);
            if (count != employees.size()) {
                pw.printf("%d, %s, %s, %.2f, %.0f, %s\n", employees.get(i).getId(), employees.get(i).getName(),
                        employees.get(i).getAddress(), employees.get(i).getHourlyRate(),
                        employees.get(i).getHoursWorked(), action);
            } else {
                pw.printf("%d, %s, %s, %.2f, %.0f, %s", employees.get(i).getId(), employees.get(i).getName(),
                        employees.get(i).getAddress(), employees.get(i).getHourlyRate(),
                        employees.get(i).getHoursWorked(), action);
            }
        }
        pw.close();

        FileOutputStream fos2 = new FileOutputStream(patronRecords);
        PrintWriter pw2 = new PrintWriter(fos2);
        for (int i = 0; i < patrons.size(); i++) {
            //    public Patron(int id, String name, String address, String email,
            //                  String phoneNumber, ArrayList<Integer> bookLoanList) {
            String list = "";
            for (int j = 0; j < patrons.get(i).getBookLoanList().size(); j++) {
                list = list + patrons.get(i).getBookLoanList().get(j) + " ";
            }
            list = list.substring(0, list.length() - 1);
            pw2.printf("%d, %s, %s, %s, %s, %s\n", patrons.get(i).getId(), patrons.get(i).getName(),
                    patrons.get(i).getAddress(), patrons.get(i).getEmail(),
                    patrons.get(i).getPhoneNumber(), list);
        }
        pw2.close();
    }

/*
    public static void main(String[] args) throws UnsupportedFileException, FileNotFoundException {

        //Check loadCatalog
        User u = new User();
        u.loadUsers(new File("employeeFile"), new File("patronFile"));
        u.writeUsers(new File("employeeOutput"), new File("patronOutput"));


    }
*/

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addPatron(Patron patron) {
        patrons.add(patron);
    }

    public boolean removeEmployee(int id) {
        int index = -1;
        for (int i = 0; i < employees.size(); i++) {
            if (employees.get(i).getId() == id) {
                index = i;
            }
        }

        if (index != -1) {
            employees.remove(employees.get(index));
            return true;
        } else {
            return false;
        }
    }

    public boolean removePatron(int id) {
        int index = -1;
        for (int i = 0; i < patrons.size(); i++) {
            if (patrons.get(i).getId() == id) {
                index = i;
            }
        }

        if (index != -1) {
            patrons.remove(patrons.get(index));
            return true;
        } else {
            return false;
        }
    }

    //getters
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public ArrayList<Patron> getPatrons() {
        return patrons;
    }

    //setters
    public void setEmployees(ArrayList<Employee> employees) {
        this.employees = employees;
    }

    public void setPatrons(ArrayList<Patron> patrons) {
        this.patrons = patrons;
    }
}
