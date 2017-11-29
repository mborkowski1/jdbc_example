package example.dao.mappers;

import example.domain.Gender;
import example.domain.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements IMapper<Student>
{

    @Override
    public Student map(ResultSet resultSet) throws SQLException
    {
        Student student = new Student();
        student.setId(resultSet.getInt("id"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        student.setAge(resultSet.getInt("age"));
        student.setGender(Gender.valueOf(resultSet.getString("gender")));
        student.setPesel(resultSet.getString("pesel"));
        student.setPhone(resultSet.getString("phone"));
        return student;
    }

}
