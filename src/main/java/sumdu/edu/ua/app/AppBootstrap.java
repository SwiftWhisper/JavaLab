package sumdu.edu.ua.app;

import com.fasterxml.jackson.databind.ObjectMapper;

import sumdu.edu.ua.repository.CompanyRepository;
import sumdu.edu.ua.repository.EmployeeRepository;
import sumdu.edu.ua.repository.InMemoryCompanyRepository;
import sumdu.edu.ua.repository.InMemoryEmployeeRepository;
import sumdu.edu.ua.service.CompanyService;
import sumdu.edu.ua.service.EmployeeFactory;
import sumdu.edu.ua.service.EmployeeService;
import sumdu.edu.ua.service.Json.CompanyJsonService;
import sumdu.edu.ua.service.Json.CompanyJsonWriter;
import sumdu.edu.ua.service.Json.JsonEmployeeMapper;
import sumdu.edu.ua.service.Json.JsonLoader;
import sumdu.edu.ua.service.Txt.TxtEmployeeMapper;
import sumdu.edu.ua.service.Txt.TxtImportService;

public class AppBootstrap {

    public AppContext initInMemory() {
        EmployeeRepository employeeRepo = new InMemoryEmployeeRepository();
        CompanyRepository companyRepo = new InMemoryCompanyRepository();
        return baseInit(employeeRepo, companyRepo);
    }

    public AppContext baseInit(EmployeeRepository employeeRepo, CompanyRepository companyRepo) {
        EmployeeFactory employeeFactory = new EmployeeFactory();

        TxtEmployeeMapper txtEmployeeMapper = new TxtEmployeeMapper();
        JsonEmployeeMapper jsonEmployeeMapper = new JsonEmployeeMapper();
        ObjectMapper objectMapper = new ObjectMapper();

        JsonLoader jsonLoader = new JsonLoader(objectMapper);
        CompanyJsonWriter JsonWriter = new CompanyJsonWriter(objectMapper); 

        CompanyService companyService = new CompanyService(companyRepo);
        EmployeeService employeeService = new EmployeeService(employeeFactory, employeeRepo);

        TxtImportService txtImportService = new TxtImportService(employeeService, companyService, txtEmployeeMapper);
        CompanyJsonService companyJsonService = new CompanyJsonService(employeeService, companyService, jsonEmployeeMapper, jsonLoader);

        return new AppContext(employeeService, companyService, txtImportService, companyJsonService, JsonWriter);
    }

}
