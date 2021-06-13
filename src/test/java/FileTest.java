import com.csvreader.Utils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class FileTest {

    @Before
    public void loadEmployeeData() {
        Utils.loadEmployeeData();
    }
    @Before
    public void loadDepartmentData() {
        Utils.loadEmployeeData();
    }

    @Test
    public void loadCsvEmployeeTest() {
        String[] employee1 = "1;John;Smith;2".split(Utils.SEMICOLON);
        String[] employee2 = "2;Pete;Hallock;1".split(Utils.SEMICOLON);
        String[] employee3 = "3;Freddie;Ruckman;4".split(Utils.SEMICOLON);
        String[] employee4 = "4;Lee;Alen;4".split(Utils.SEMICOLON);
        String[] employee5 = "5;Paul;Miller;3".split(Utils.SEMICOLON);


        Object[] expected = new Object[5];
        expected[0] = Utils.parseCSVEmployee(employee1).toString();
        expected[1] = Utils.parseCSVEmployee(employee2).toString();
        expected[2] = Utils.parseCSVEmployee(employee3).toString();
        expected[3] = Utils.parseCSVEmployee(employee4).toString();
        expected[4] = Utils.parseCSVEmployee(employee5).toString();


        Object[] received = new Object[5];
        received[0] = Utils.loadEmployeeData().get(0).toString();
        received[1] = Utils.loadEmployeeData().get(1).toString();
        received[2] = Utils.loadEmployeeData().get(2).toString();
        received[3] = Utils.loadEmployeeData().get(3).toString();
        received[4] = Utils.loadEmployeeData().get(4).toString();

        Assert.assertEquals("It should return a list of the first five employees in the file.", expected, received);
    }

    @Test
    public void loadCsvDepartmentTest() {
        String[] department1 = "1;Marketing".split(Utils.SEMICOLON);
        String[] department2 = "2;Management".split(Utils.SEMICOLON);
        String[] department3 = "3;Finance".split(Utils.SEMICOLON);
        String[] department4 = "4;Production".split(Utils.SEMICOLON);


        Object[] expected = new Object[5];
        expected[0] = Utils.parseCSVDepartment(department1).toString();
        expected[1] = Utils.parseCSVDepartment(department2).toString();
        expected[2] = Utils.parseCSVDepartment(department3).toString();
        expected[3] = Utils.parseCSVDepartment(department4).toString();


        Object[] received = new Object[5];
        received[0] = Utils.loadDepartmentData().get(0).toString();
        received[1] = Utils.loadDepartmentData().get(1).toString();
        received[2] = Utils.loadDepartmentData().get(2).toString();
        received[3] = Utils.loadDepartmentData().get(3).toString();

        Assert.assertEquals("It should return a department list.", expected, received);
    }

    @Test
    public void testEmployeeCount() {
        Assert.assertEquals("Quantity should be 7.", 7, Utils.employeeCount());
    }

    @Test
    public void testDepartmentCount() {
        Assert.assertEquals("Quantity should be 4.", 4, Utils.departmentCount());
    }

}
