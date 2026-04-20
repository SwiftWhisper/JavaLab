package sumdu.edu.ua;

public class EmployeeDto {

    private final String type;
    private final String nameSurname;
    private final String age;
    private final String salary;
    private final String position;

    private final String yearsInCompany;
    private final String durationOfContract;
    private final String hoursInDay;
    private final String internshipMonths;
    private final String university;

    public EmployeeDto(
            String type,
            String nameSurname,
            String age,
            String salary,
            String position,
            String yearsInCompany,
            String durationOfContract,
            String hoursInDay,
            String internshipMonths,
            String university
    ) {
        this.type = type;
        this.nameSurname = nameSurname;
        this.age = age;
        this.salary = salary;
        this.position = position;
        this.yearsInCompany = yearsInCompany;
        this.durationOfContract = durationOfContract;
        this.hoursInDay = hoursInDay;
        this.internshipMonths = internshipMonths;
        this.university = university;
    }

    public String getType() {
        return type;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public String getAge() {
        return age;
    }

    public String getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }

    public String getYearsInCompany() {
        return yearsInCompany;
    }

    public String getDurationOfContract() {
        return durationOfContract;
    }

    public String getHoursInDay() {
        return hoursInDay;
    }

    public String getInternshipMonths() {
        return internshipMonths;
    }

    public String getUniversity() {
        return university;
    }
}
