package example.dao.repos;

import example.domain.Address;

import java.util.List;

public interface IAddressRepository extends IRepository<Address>
{

    List<Address> selectByCity(String city);
    List<Address> selectByStudent(int idStudent);

}