package sumdu.edu.ua.app;

import java.util.Properties;

import com.fasterxml.jackson.databind.ObjectMapper;

import sumdu.edu.ua.repository.CompanyRepository;
import sumdu.edu.ua.repository.DatabaseCompanyRepository;
import sumdu.edu.ua.repository.DatabaseEmployeeRepository;
import sumdu.edu.ua.repository.EmployeeRepository;
import sumdu.edu.ua.repository.InMemoryCompanyRepository;
import sumdu.edu.ua.repository.InMemoryEmployeeRepository;
import sumdu.edu.ua.service.CompanyService;
import sumdu.edu.ua.service.ConfigLoader;
import sumdu.edu.ua.service.EmployeeFactory;
import sumdu.edu.ua.service.EmployeeService;
import sumdu.edu.ua.service.Database.DatabaseConnection;
import sumdu.edu.ua.service.Database.DatabaseInitializer;
import sumdu.edu.ua.service.Database.DatabaseManager;
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

    public AppContext initWithDatabase(String configPath) {
        try {
        Properties props = ConfigLoader.load(configPath);

        DatabaseConnection databaseConnection = new DatabaseConnection(
                props.getProperty("db.url"),
                props.getProperty("db.user"),
                props.getProperty("db.password")
        );
        DatabaseManager db = new DatabaseManager(databaseConnection);
        DatabaseInitializer dbInit = new DatabaseInitializer(databaseConnection);
        dbInit.runSchema("schema.sql");
        EmployeeRepository employeeRepo = new DatabaseEmployeeRepository(db);
        CompanyRepository companyRepo = new DatabaseCompanyRepository(db);
        return baseInit(employeeRepo, companyRepo);
        }
        catch (Exception e){
            throw new RuntimeException(
                "Не вдалося запустити програму з БД. Перевір config.properties, PostgreSQL і schema.sql.",
                e
            );
        }
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
