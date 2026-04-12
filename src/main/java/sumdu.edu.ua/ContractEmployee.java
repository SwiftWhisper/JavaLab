package sumdu.edu.ua;

public class ContractEmployee extends Employee{
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

    public static void validateDurationOfContract(int durationOfContract) {
        if(durationOfContract <= 0){throw new IllegalArgumentException("Помилка: довжина контракту не може бути меншою 1 року!");}
    }

    public void setDurationOfContract(int durationOfContract){
        validateDurationOfContract(durationOfContract);
        this.durationOfContract = durationOfContract;
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
