package sumdu.edu.ua.app;

import sumdu.edu.ua.service.EmployeeService;
import sumdu.edu.ua.service.CompanyService;
import sumdu.edu.ua.service.Json.CompanyJsonService;
import sumdu.edu.ua.service.Txt.TxtImportService;
import sumdu.edu.ua.service.Json.CompanyJsonWriter;

public class AppContext {
    public final EmployeeService employeeService;
    public final CompanyService companyService;
    public final TxtImportService txtImportService;
    public final CompanyJsonService companyJsonService;
    public final CompanyJsonWriter companyJsonWriter;

    public AppContext(EmployeeService employeeService,
                      CompanyService companyService,
                      TxtImportService txtImportService,
                      CompanyJsonService companyJsonService,
                      CompanyJsonWriter companyJsonWriter) {

        this.employeeService = employeeService;
        this.companyService = companyService;
        this.txtImportService = txtImportService;
        this.companyJsonService = companyJsonService;
        this.companyJsonWriter = companyJsonWriter;
    }
}
