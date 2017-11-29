package example.dao.repos;

import example.domain.Gender;
import example.domain.Student;

import java.util.List;

public interface IStudentRepository extends IRepository<Student>
{

    List<Student> selectByFirstName(String firstName);
    List<Student> selectByLastName(String lastName);
    List<Student> selectByAge(int fromAge, int toAge);
    List<Student> selectByGender(Gender gender);

}
