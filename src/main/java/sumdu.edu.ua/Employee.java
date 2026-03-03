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

}
