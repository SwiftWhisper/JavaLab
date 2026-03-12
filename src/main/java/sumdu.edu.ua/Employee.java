package sumdu.edu.ua;

import java.util.Objects;

public class Employee {
    private int id;
    private static int nextId=1;
    private String nameSurname;
    private int age;
    private double salary;
    private String position;

    public Employee() {
        this.id = nextId++;
    }
    
    public Employee(String nameSurname, int age, double salary, String position) {
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

    public void setNameSurname(String nameSurname) {
        if (nameSurname == null || nameSurname.isBlank()) {
            throw new IllegalArgumentException("Помилка: ви не ввели ім'я та прізвище співробітника.");
        }

        if (!nameSurname.matches("[a-zA-Zа-яА-ЯіїєІЇЄ'\\- ]+")) {
            throw new IllegalArgumentException("Помилка: ім'я може містити тільки букви.");
        }

        this.nameSurname = nameSurname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 18){throw new IllegalArgumentException("Співробітник має бути повнолітнім!");}
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if(salary < 0){throw new IllegalArgumentException("Помилка: заробітня плата не може бути від'ємною!");}

        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        if (position == null || position.isBlank()) {
            throw new IllegalArgumentException("Помилка: ви не ввели посаду співробітника.");
        }
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
                && Objects.equals(position, emp.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nameSurname, age, salary, position);
    }
    
}
