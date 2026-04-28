package sumdu.edu.ua.service;

import java.util.List;
import sumdu.edu.ua.model.Company;
import sumdu.edu.ua.repository.CompanyRepository;

public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository employeeRepository) {
        this.companyRepository = employeeRepository;
        
    }

    public Company createAndSaveCompany(String companyName, List<Company> companies) {
        Company newCompany = new Company(companyName);
        companies.add(newCompany);
        companyRepository.save(newCompany);
        return newCompany;
    }
}
