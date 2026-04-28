DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'position_enum') THEN
        CREATE TYPE position_enum AS ENUM ('MANAGER', 'HR', 'JANITOR', 'CLERK');
    END IF;
END $$;

DO $$
BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'employee_type_enum') THEN
        CREATE TYPE employee_type_enum AS ENUM ('FULL_TIME', 'CONTRACT', 'PART_TIME', 'INTERN');
    END IF;
END $$;

Create TABLE IF NOT exists COMPANY (
	Company_id BIGSERIAL PRIMARY KEY,
	name Varchar(100) not null
);

CREATE TABLE IF NOT exists EMPLOYEE (
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

