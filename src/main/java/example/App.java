package example;

import example.dao.repos.IDatabaseCatalog;
import example.dao.repos.examples.AddressRepositoryExample;
import example.dao.repos.examples.StudentRepositoryExample;
import example.dao.repos.impl.DatabaseCatalog;
import example.dao.uow.IUnitOfWork;
import example.dao.uow.UnitOfWork;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class App
{

    public static void main(String[] args) throws SQLException
    {
        String url = "jdbc:hsqldb:hsql://localhost/workdb";

        Connection connection = DriverManager.getConnection(url);
        IUnitOfWork uow = new UnitOfWork(connection);
        IDatabaseCatalog catalog = new DatabaseCatalog(connection, uow);

        StudentRepositoryExample.run(connection, catalog);
        AddressRepositoryExample.run(connection, catalog);

        uow.saveChanges();
        connection.close();
    }

}
