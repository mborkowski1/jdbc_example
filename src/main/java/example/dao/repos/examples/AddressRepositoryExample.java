package example.dao.repos.examples;

import example.dao.repos.IDatabaseCatalog;
import example.domain.Address;

import java.sql.Connection;

public class AddressRepositoryExample
{

    public static void run(Connection connection, IDatabaseCatalog catalog)
    {
        // catalog.addresses().createTable();

        Address address = new Address();
        address.setStreet("ABC");
        address.setCity("DEF");
        address.setHouseNumer("123");
        address.setPostCode("11-111");
        address.setIdStudent(1);

        catalog.addresses().add(address);
    }

}
