package sumdu.edu.ua;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EmployeeTest {

    @Test
    void succesfullEmployeeCreationTest(){
        Employee testEmployeeEmpty = new Employee();
        Employee sameReference = testEmployeeEmpty;
        Employee testEmployeeDefined = new Employee("Test Emp", 20, 999, "Ranger");

        assertTrue(testEmployeeEmpty.getId() > 0);
        assertTrue(testEmployeeDefined.getId() > 0);

        assertSame(sameReference, testEmployeeEmpty);

        assertNull(testEmployeeEmpty.getNameSurname());
        assertNotNull(testEmployeeDefined.getNameSurname());

        assertEquals("Test Emp", testEmployeeDefined.getNameSurname());
        assertEquals(20, testEmployeeDefined.getAge());
        assertEquals(999, testEmployeeDefined.getSalary());
        assertEquals("Ranger", testEmployeeDefined.getPosition());

        testEmployeeEmpty.setNameSurname("Frodo");
        assertEquals("Frodo", testEmployeeEmpty.getNameSurname());

        testEmployeeEmpty.setAge(30);
        assertEquals(30, testEmployeeEmpty.getAge());

        testEmployeeEmpty.setSalary(2000);
        assertEquals(2000, testEmployeeEmpty.getSalary());

        testEmployeeEmpty.setPosition("Test");
        assertEquals("Test", testEmployeeEmpty.getPosition());
    }

    @Test
    void setIllegalNameSurnameTest(){
        Employee e = new Employee();

        assertThrows(IllegalArgumentException.class, () -> e.setNameSurname(""));
        assertThrows(IllegalArgumentException.class, () -> e.setNameSurname(" "));
        assertThrows(IllegalArgumentException.class, () -> e.setNameSurname("fkadj3fjd"));
        assertThrows(IllegalArgumentException.class, () -> e.setNameSurname("4245"));
        assertThrows(IllegalArgumentException.class, () -> e.setNameSurname("   _   "));
        assertThrows(IllegalArgumentException.class, () -> e.setNameSurname("test_testi"));
    }

    @Test
    void setValidNameSurnameTest() {
        Employee e = new Employee();

        assertDoesNotThrow(() -> e.setNameSurname("Test Testi"));
        assertDoesNotThrow(() -> e.setNameSurname("TestTesti"));
        assertDoesNotThrow(() -> e.setNameSurname("Test"));
        assertDoesNotThrow(() -> e.setNameSurname("A"));
        assertDoesNotThrow(() -> e.setNameSurname("Тест"));
        assertDoesNotThrow(() -> e.setNameSurname("ЇжакTest"));
    }

    @Test
    void setIllegalAgeTest(){
        Employee e = new Employee();

        assertThrows(IllegalArgumentException.class, () -> e.setAge(-1));
        assertThrows(IllegalArgumentException.class, () -> e.setAge(0));
        assertThrows(IllegalArgumentException.class, () -> e.setAge(10));
        assertThrows(IllegalArgumentException.class, () -> e.setAge(17));
    }

    @Test
    void setValidAgeTest() {
        Employee e = new Employee();

        assertDoesNotThrow(() -> e.setAge(18));
        assertDoesNotThrow(() -> e.setAge(25));
        assertDoesNotThrow(() -> e.setAge(89));
    }

    @Test
    void setIllegalSalaryTest(){
        Employee e = new Employee();

        assertThrows(IllegalArgumentException.class, () -> e.setSalary(-1));
        assertThrows(IllegalArgumentException.class, () -> e.setSalary(-800));
        assertThrows(IllegalArgumentException.class, () -> e.setSalary(-0.001));
        assertDoesNotThrow(()->{e.setAge(18);});
    }

    @Test
    void setValidSalaryTest() {
        Employee e = new Employee();

        assertDoesNotThrow(() -> e.setSalary(0));
        assertDoesNotThrow(() -> e.setSalary(1));
        assertDoesNotThrow(() -> e.setSalary(0.0001));
        assertDoesNotThrow(() -> e.setSalary(400));
        assertDoesNotThrow(() -> e.setSalary(999.3));
    }

    @Test
    void setIllegalPositionTest(){
        Employee e = new Employee();

        assertThrows(IllegalArgumentException.class, () -> e.setPosition(""));
        assertThrows(IllegalArgumentException.class, () -> e.setPosition(" "));
    }

    @Test
    void setValidPositionTest() {
        Employee e = new Employee();

        assertDoesNotThrow(() -> e.setPosition("Test Testi"));
        assertDoesNotThrow(() -> e.setPosition("TestTesti"));
        assertDoesNotThrow(() -> e.setPosition("Test"));
        assertDoesNotThrow(() -> e.setPosition("A"));
        assertDoesNotThrow(() -> e.setPosition("Тест"));
        assertDoesNotThrow(() -> e.setPosition("ЇжакTest"));
        assertDoesNotThrow(() -> e.setPosition("fkadj3fjd"));
        assertDoesNotThrow(() -> e.setPosition("test_testi"));
        assertDoesNotThrow(() -> e.setPosition("4245"));
    }


}
