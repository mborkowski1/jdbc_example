package example.dao.repos.examples;

import example.dao.repos.IDatabaseCatalog;
import example.domain.Gender;
import example.domain.Student;

import java.sql.Connection;

public class StudentRepositoryExample
{

    public static void run(Connection connection, IDatabaseCatalog catalog)
    {
        // catalog.students().createTable();

        Student student = new Student();
        student.setFirstName("Jan");
        student.setLastName("Kowalski");
        student.setAge(20);
        student.setPesel("12345678900");
        student.setPhone("123456789");
        student.setGender(Gender.MALE);

        catalog.students().add(student);
    }

}
