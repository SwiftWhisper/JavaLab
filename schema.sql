CREATE TYPE position_enum AS ENUM ('MANAGER', 'HR', 'JANITOR', 'CLERK');
CREATE TYPE employee_type_enum AS ENUM ('FULL_TIME', 'CONTRACT', 'PART_TIME', 'INTERN');

Create table COMPANY (
	Company_id BIGSERIAL PRIMARY KEY,
	name Varchar(100) not null
);

CREATE TABLE EMPLOYEE (
    Employee_id BIGSERIAL PRIMARY KEY,
    Company_id BIGINT not null,
    Employee_type employee_type_enum NOT NULL,
    Name_surname varchar(100) NOT NULL,
    Age int not null,
    Salary int not null,
    Position position_enum not null,
    
    Years_in_company int,
    Duration_of_contract int,
    Hours_in_day int,
    University varchar(100),
    Internship_months int,
    
    CONSTRAINT fk_employee_company
    	FOREIGN KEY (Company_id)
    	REFERENCES company(Company_id)
    	ON DELETE CASCADE
);

