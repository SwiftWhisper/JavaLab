package sumdu.edu.ua.validators;

import sumdu.edu.ua.Position;

public class PartTimeEmployeeValidator extends EmployeeValidator {
    public static void validateHoursInDay(int hoursInDay) {
        if (hoursInDay < 4 || hoursInDay > 8) {
            throw new IllegalArgumentException("Помилка: кількість робочих годин на день повинна бути від 4 до 8!");
        }
    }

    @Override
    public void validateAge(int age) {
        if(age < 18){throw new IllegalArgumentException("Співробітник має бути повнолітнім!");}
    }
}
