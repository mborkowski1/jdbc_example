package example.dao.repos.impl;

import example.dao.mappers.AddressMapper;
import example.dao.mappers.StudentMapper;
import example.dao.repos.IAddressRepository;
import example.dao.repos.IDatabaseCatalog;
import example.dao.repos.IStudentRepository;
import example.dao.uow.IUnitOfWork;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseCatalog implements IDatabaseCatalog
{

    Connection connection;
    IUnitOfWork uow;

    public DatabaseCatalog(Connection connection, IUnitOfWork uow)
    {
        this.connection = connection;
        this.uow = uow;
    }

    @Override
    public IAddressRepository addresses()
    {
        try
        {
            return new AddressRepository(connection, new AddressMapper(), uow);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public IStudentRepository students()
    {
        try
        {
            return new StudentRepository(connection, new StudentMapper(), uow);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
