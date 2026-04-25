package sumdu.edu.ua.validators;

public class FullTimeEmployeeValidator extends EmployeeValidator {
    public void validateYearsInCompany(int yearsInCompany){
        if(yearsInCompany < 0){throw new IllegalArgumentException("Помилка: кількість років не може бути від'ємною!");}
    }

    @Override
    public void validateAge(int age) {
        if(age < 18){throw new IllegalArgumentException("Співробітник має бути повнолітнім!");}
    }
}
