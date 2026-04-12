package sumdu.edu.ua;

public class FullTimeEmployee extends Employee{
    private int yearsInCompany;

    public FullTimeEmployee() {
        super();
    }
        
    public FullTimeEmployee(String nameSurname, int age, double salary, Position position, int yearsInCompany) {
        super(nameSurname, age, salary, position);
        setYearsInCompany(yearsInCompany);
    }

    public FullTimeEmployee(Employee other, int setYearsInCompany) {
        super(other);
        setYearsInCompany(yearsInCompany);
    }

    public FullTimeEmployee(FullTimeEmployee other) {
        super(other);
        this.yearsInCompany = other.yearsInCompany;
    }

    public int getYearsInCompany(){
        return yearsInCompany;
    }

    public static void validateYearsInCompany(int yearsInCompany){
        if(yearsInCompany < 0){throw new IllegalArgumentException("Помилка: кількість років не може бути від'ємною!");}
    }

    public void setYearsInCompany(int years){
        validateYearsInCompany(years);
        this.yearsInCompany = yearsInCompany;
    }

    @Override
    public String toString() {
        return "FullTimeEmployee{" +
            "id=" + id +
            ", nameSurname='" + nameSurname + '\'' +
            ", age=" + age +
            ", salary=" + salary +
            ", position=" + position +
            ", yearsInCompany=" + yearsInCompany +
            '}';
    }

}
