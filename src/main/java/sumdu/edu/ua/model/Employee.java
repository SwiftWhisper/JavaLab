package sumdu.edu.ua.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.fasterxml.jackson.databind.node.ObjectNode;

import sumdu.edu.ua.validators.EmployeeValidator;

public abstract class Employee {
    protected int localId;
    protected Long databaseId;
    private static int nextId=1;
    protected String nameSurname;
    protected int age;
    protected double salary;
    protected Position position;

    protected abstract EmployeeValidator getValidator();

    public abstract EmployeeType getType();
    public Employee() {
        this.localId = nextId++;
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

    public int getLocalId() {
        return localId;
    }

    public Long getDatabaseId() {
        return databaseId;
    }

    public void assignDatabaseId(Long databaseId) {
        if (databaseId == null) {
            throw new IllegalArgumentException("Database ID не може бути null.");
        }

        if (this.databaseId != null) {
            throw new IllegalStateException("Database ID вже встановлений.");
        }

        this.databaseId = databaseId;
    }
    
    public Long getDisplayId() {
        return (databaseId != null) ? databaseId : localId;
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

    public Map<String, Object> getDbFields() {
        return new HashMap<>();
    }

    protected String baseToString() {
        return "id=" + getDisplayId() +
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

        if (this.databaseId != null && emp.databaseId != null) {
        return Objects.equals(this.databaseId, emp.databaseId);
        }

        return this.localId == emp.localId;
    }

    @Override
    public int hashCode() {
        return (databaseId != null)
                ? Objects.hash(databaseId)
                : Objects.hash(localId);
    }
    
}
