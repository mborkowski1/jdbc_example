package example.dao.mappers;

import example.domain.Address;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddressMapper implements IMapper<Address>
{

    @Override
    public Address map(ResultSet resultSet) throws SQLException
    {
        Address address = new Address();
        address.setId(resultSet.getInt("id"));
        address.setStreet(resultSet.getString("street"));
        address.setCity(resultSet.getString("city"));
        address.setHouseNumer(resultSet.getString("house_number"));
        address.setPostCode(resultSet.getString("post_code"));
        address.setIdStudent(resultSet.getInt("id_student"));
        return address;
    }

}
