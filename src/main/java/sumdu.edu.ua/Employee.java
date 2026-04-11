package sumdu.edu.ua;

import java.util.Objects;

public class Employee {
    protected int id;
    protected static int nextId=1;
    protected String nameSurname;
    protected int age;
    protected double salary;
    protected Position position;

    private static int empCount = 0;

    public static int getEmpCount() {
        return empCount;
    }

    public Employee() {
        this.id = nextId++;
        empCount++;
    }
    
    public Employee(String nameSurname, int age, double salary, Position position) {
        this();
        setNameSurname(nameSurname);
        setAge(age);
        setSalary(salary);
        setPosition(position);
    }

    public Employee(Employee other) {
        this();
        this.nameSurname = other.nameSurname;
        this.age = other.age;
        this.salary = other.salary;
        this.position = other.position; 
    }

    public int getId() {
        return id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public static void validateNameSurname (String nameSurname) {
        if (nameSurname == null || nameSurname.isBlank()) {
            throw new IllegalArgumentException("Помилка: ви не ввели ім'я та прізвище співробітника.");
        }

        if (!nameSurname.matches("[a-zA-Zа-яА-ЯіїєІЇЄ'\\- ]+")) {
            throw new IllegalArgumentException("Помилка: ім'я може містити тільки букви.");
        }
    }

    public void setNameSurname(String nameSurname) {
        validateNameSurname(nameSurname);
        this.nameSurname = nameSurname;
    }

    public int getAge() {
        return age;
    }

    public static void validateAge(int age) {
        if(age < 18){throw new IllegalArgumentException("Співробітник має бути повнолітнім!");}
    }

    public void setAge(int age) {
        validateAge(age);
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public static void validateSalary(double salary) {
        if(salary < 0){throw new IllegalArgumentException("Помилка: заробітня плата не може бути від'ємною!");}
    }

    public void setSalary(double salary) {
        validateSalary(salary);
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public static void validatePosition(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Помилка: ви не ввели посаду співробітника.");
        }
    }

    public void setPosition(Position position) {
        validatePosition(position);
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id=" + id +
            ", nameSurname='" + nameSurname + '\'' +
            ", age=" + age +
            ", salary=" + salary +
            ", position='" + position + '\'' +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee emp)) return false;

        return id == emp.id
                && age == emp.age
                && Double.compare(salary, emp.salary) == 0
                && Objects.equals(nameSurname, emp.nameSurname)
                && position == emp.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameSurname, age, salary, position);
    }
    
}
