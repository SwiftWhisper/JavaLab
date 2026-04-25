package sumdu.edu.ua.validators;

public class InternEmployeeValidator extends EmployeeValidator {
    public void validateUniversity(String university) {
        if (university == null || university.trim().isEmpty()) {
            throw new IllegalArgumentException("Помилка: назва університету не може бути порожньою!");
        }

        if (!university.matches("[a-zA-Zа-яА-ЯіїєІЇЄ'\\- ]+")) {
            throw new IllegalArgumentException("Помилка: назва університету може містити тільки букви.");
        }
    }

    public void validateInternshipMonths(int months) {
        if (months <= 0 || months > 12) {
            throw new IllegalArgumentException("Помилка: тривалість стажування повинна бути від 1 до 12 місяців!");
        }
    }

    @Override
    public void validateAge(int age) {
        if(age < 14){throw new IllegalArgumentException("Інтерн має досягнути віку 14 років!");}
    }
}

