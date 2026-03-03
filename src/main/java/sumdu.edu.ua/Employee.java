package sumdu.edu.ua;

public class Employee {
    private int id;
    private static int nextId=1;
    private String nameSurname;
    private double salary;
    private String position;

    public Employee() {
        this.id = nextId++;
    }
    
    public Employee(String nameSurname, double salary, String position) {
        this();
        this.nameSurname = nameSurname;
        this.salary = salary;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
