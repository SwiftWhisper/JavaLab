package sumdu.edu.ua.validators;

import sumdu.edu.ua.Position;

public class ContractEmployeeValidator extends EmployeeValidator {
    public static void validateDurationOfContract(int durationOfContract) {
        if(durationOfContract <= 0){throw new IllegalArgumentException("Помилка: довжина контракту не може бути меншою 1 року!");}
    }

    @Override
    public void validateAge(int age) {
        if(age < 18){throw new IllegalArgumentException("Співробітник має бути повнолітнім!");}
    }
}

