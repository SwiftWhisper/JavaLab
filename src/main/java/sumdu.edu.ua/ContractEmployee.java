package sumdu.edu.ua;

import com.fasterxml.jackson.databind.node.ObjectNode;

import sumdu.edu.ua.validators.EmployeeValidator;
import sumdu.edu.ua.validators.ContractEmployeeValidator;

public class ContractEmployee extends Employee{
    private static final ContractEmployeeValidator VALIDATOR = new ContractEmployeeValidator();

    private int durationOfContract;

    public ContractEmployee() {
        super();
    }
        
    public ContractEmployee(String nameSurname, int age, double salary, Position position, int durationOfContract) {
        super(nameSurname, age, salary, position);
        setDurationOfContract(durationOfContract);
    }

    public ContractEmployee(Employee other, int durationOfContract) {
        super(other);
        setDurationOfContract(durationOfContract);
    }

    public ContractEmployee(ContractEmployee other) {
        super(other);
        this.durationOfContract = other.durationOfContract;
    }

    public int getDurationOfContract(){
        return durationOfContract;
    }


    public void setDurationOfContract(int durationOfContract){
        VALIDATOR.validateDurationOfContract(durationOfContract);
        this.durationOfContract = durationOfContract;
    }

    @Override 
    public void toJson(ObjectNode node) {
        super.toJson(node);
        node.put("durationOfContract", durationOfContract);
    }

    @Override
    public EmployeeType getType() {
        return EmployeeType.CONTRACT;
    }

    @Override
    protected EmployeeValidator getValidator() {
        return VALIDATOR;
    }

    @Override
    public String toString() {
        return "ContractEmployee{" +
            "id=" + id +
            ", nameSurname='" + nameSurname + '\'' +
            ", age=" + age +
            ", salary=" + salary +
            ", position=" + position +
            ", durationOfContract=" + durationOfContract +
            '}';
    }

}
