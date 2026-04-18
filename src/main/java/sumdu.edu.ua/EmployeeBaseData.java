package sumdu.edu.ua;

public class EmployeeBaseData {
    private final String nameSurname;
    private final int age;
    private final double salary;
    private final Position position;

    public EmployeeBaseData(String nameSurname, int age, double salary, Position position) {
        this.nameSurname = nameSurname;
        this.age = age;
        this.salary = salary;
        this.position = position;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public int getAge() {
        return age;
    }

    public double getSalary() {
        return salary;
    }

    public Position getPosition() {
        return position;
    }
}
