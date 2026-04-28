package sumdu.edu.ua.service;

import java.util.List;

import sumdu.edu.ua.model.Company;

public class CompanyUtils {

    public static Company findById(List<Company> companies, int id) {
        for (Company c : companies) {
            if (c.getDisplayId() == id) {
                return c;
            }
        }
        return null;
    }
}
