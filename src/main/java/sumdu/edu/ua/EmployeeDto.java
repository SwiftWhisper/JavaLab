package sumdu.edu.ua;

import sumdu.edu.ua.model.ContractEmployee;
import sumdu.edu.ua.model.Employee;
import sumdu.edu.ua.model.FullTimeEmployee;
import sumdu.edu.ua.model.InternEmployee;
import sumdu.edu.ua.model.PartTimeEmployee;

public class EmployeeDto {

    private Integer id;
    private String type;
    private String nameSurname;
    private String age;
    private String salary;
    private String position;

    private String yearsInCompany;
    private String durationOfContract;
    private String hoursInDay;
    private String internshipMonths;
    private String university;

    public EmployeeDto(){

    }
    
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

    public static EmployeeDto fromEmployee(Employee employee) {

        EmployeeDto dto = new EmployeeDto();

        dto.setId(employee.getId());

        dto.setNameSurname(employee.getNameSurname());
        dto.setAge(String.valueOf(employee.getAge()));
        dto.setSalary(String.valueOf(employee.getSalary()));
        dto.setPosition(employee.getPosition().name());
        dto.setType(employee.getType().name());

        if (employee instanceof FullTimeEmployee e) {
            dto.setYearsInCompany(String.valueOf(e.getYearsInCompany()));
        } else if (employee instanceof ContractEmployee e) {
            dto.setDurationOfContract(String.valueOf(e.getDurationOfContract()));
        } else if (employee instanceof PartTimeEmployee e) {
            dto.setHoursInDay(String.valueOf(e.getHoursInDay()));
        } else if (employee instanceof InternEmployee e) {
            dto.setUniversity(e.getUniversity());
            dto.setInternshipMonths(String.valueOf(e.getInternshipMonths()));
        }

        return dto;
    }

    public Integer getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

	public void setAge(String age) {
		this.age = age;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public void setYearsInCompany(String yearsInCompany) {
		this.yearsInCompany = yearsInCompany;
	}

	public void setDurationOfContract(String durationOfContract) {
		this.durationOfContract = durationOfContract;
	}

	public void setHoursInDay(String hoursInDay) {
		this.hoursInDay = hoursInDay;
	}

	public void setInternshipMonths(String internshipMonths) {
		this.internshipMonths = internshipMonths;
	}

	public void setUniversity(String university) {
		this.university = university;
	}
}
