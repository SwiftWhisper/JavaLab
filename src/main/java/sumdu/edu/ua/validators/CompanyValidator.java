package sumdu.edu.ua.validators;

public class CompanyValidator {
    public void validateName (String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Помилка: ви не ввели назву компанії.");
        }
    }

}

