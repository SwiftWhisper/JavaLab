package sumdu.edu.ua;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class CompanyUtils {

    public static Company findById(List<Company> companies, int id) {
        for (Company c : companies) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null;
    }
}
