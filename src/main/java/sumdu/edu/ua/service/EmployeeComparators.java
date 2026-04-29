package sumdu.edu.ua.service;

import java.util.Comparator;

import sumdu.edu.ua.model.Employee;

public class EmployeeComparators {
    private EmployeeComparators() {}
    public static final Comparator<Employee> BY_SALARY = new Comparator<Employee>() {
        @Override
        public int compare(Employee e1, Employee e2) {
            int result = Double.compare(e1.getSalary(), e2.getSalary());
            if (result != 0) return result;

            return Integer.compare(e1.getId(), e2.getId());
        }
    };

    public static final Comparator<Employee> BY_NAMESURNAME = new Comparator<Employee>() {
        @Override
        public int compare(Employee e1, Employee e2) {
            int result = e1.getNameSurname().compareToIgnoreCase(e2.getNameSurname());
            if (result != 0) return result;

            return Integer.compare(e1.getId(), e2.getId());

        }
    };

    public static final Comparator<Employee> BY_AGE = new Comparator<Employee>() {
        @Override
        public int compare(Employee e1, Employee e2) {
            int result  = Integer.compare(e1.getAge(), e2.getAge());
            if (result != 0) return result;

            return Integer.compare(e1.getId(), e2.getId());

        }
    };
}
