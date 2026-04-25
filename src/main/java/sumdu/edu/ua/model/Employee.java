package sumdu.edu.ua.model;

import com.fasterxml.jackson.databind.node.ObjectNode;

import sumdu.edu.ua.validators.EmployeeValidator;
import java.util.Objects;

public abstract class Employee {
    protected int id;
    protected static int nextId=1;
    protected String nameSurname;
    protected int age;
    protected double salary;
    protected Position position;

    private static int empCount = 0;

    protected abstract EmployeeValidator getValidator();
    public abstract EmployeeType getType();

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

    public void setNameSurname(String nameSurname) {
        getValidator().validateNameSurname(nameSurname);
        this.nameSurname = nameSurname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        getValidator().validateAge(age);
        this.age = age;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        getValidator().validateSalary(salary);
        this.salary = salary;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        getValidator().validatePosition(position);
        this.position = position;
    }

    public void toJson(ObjectNode node) {
        node.put("type", getType().name());
        node.put("nameSurname", getNameSurname());
        node.put("age", getAge());
        node.put("salary", getSalary());
        node.put("position", getPosition().name());
    }

    protected String baseToString() {
        return "id=" + id +
                ", nameSurname='" + nameSurname + '\'' +
                ", age=" + age +
                ", salary=" + salary +
                ", position=" + position;
    }

    @Override
    public String toString() {
        return "Employee{" + baseToString() + '}';
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
