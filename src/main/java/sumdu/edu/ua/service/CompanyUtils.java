package sumdu.edu.ua.service;

import java.util.List;
import java.util.UUID;

import sumdu.edu.ua.model.Company;

public class CompanyUtils {

    public static Company findByUuid(List<Company> companies, UUID uuid) {
        for (Company c : companies) {
            if (c.getUuid().equals(uuid)) {
                return c;
            }
        }
        return null;
    }
}
