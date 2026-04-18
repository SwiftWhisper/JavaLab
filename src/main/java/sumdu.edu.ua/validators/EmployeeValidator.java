package sumdu.edu.ua.validators;

import sumdu.edu.ua.Position;

public abstract class EmployeeValidator {

    public void validateNameSurname (String nameSurname) {
        if (nameSurname == null || nameSurname.isBlank()) {
            throw new IllegalArgumentException("Помилка: ви не ввели ім'я та прізвище співробітника.");
        }

        if (!nameSurname.matches("[a-zA-Zа-яА-ЯіїєІЇЄ'\\- ]+")) {
            throw new IllegalArgumentException("Помилка: ім'я може містити тільки букви.");
        }
    }

    public void validateSalary(double salary) {
        if(salary < 0){throw new IllegalArgumentException("Помилка: заробітня плата не може бути від'ємною!");}
    }

    public void validatePosition(Position position) {
        if (position == null) {
            throw new IllegalArgumentException("Помилка: ви не ввели посаду співробітника.");
        }
    }

    public void validateAge(int age) {}
}

