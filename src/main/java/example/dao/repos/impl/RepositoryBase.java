package example.dao.repos.impl;

import example.dao.mappers.IMapper;
import example.dao.repos.IRepository;
import example.dao.uow.Entity;
import example.dao.uow.IUnitOfWork;
import example.dao.uow.IUnitOfWorkRepository;
import example.domain.IHaveId;

import java.sql.*;

public abstract class RepositoryBase<TEntity extends IHaveId> implements IRepository<TEntity>, IUnitOfWorkRepository
{

    protected Connection connection;
    protected IMapper<TEntity> mapper;
    protected IUnitOfWork uow;

    protected boolean tableExists;

    protected PreparedStatement insert;
    protected PreparedStatement update;
    protected PreparedStatement delete;
    protected PreparedStatement selectById;

    protected RepositoryBase(Connection connection, IMapper<TEntity> mapper, IUnitOfWork uow) throws SQLException
    {
        this.connection = connection;
        this.mapper = mapper;
        this.uow = uow;
        ResultSet resultSet = connection.getMetaData().getTables(null,null,null,null);
        checkIfTableExists(resultSet);
        if (!tableExists)
        {
            createTable();
            uow.saveChanges();
        }
        initStatements(connection);
    }

    private void checkIfTableExists(ResultSet resultSet) throws SQLException
    {
        while (resultSet.next())
        {
            if (resultSet.getString("TABLE_NAME").equalsIgnoreCase(getTableName()))
                tableExists = true;
        }
    }

    private void initStatements(Connection connection) throws SQLException
    {
        insert = connection.prepareStatement(getInsertQuerySql());
        update = connection.prepareStatement(getUpdateQuerySql());
        delete = connection.prepareStatement("DELETE FROM " + getTableName() + " WHERE id=?");
        selectById = connection.prepareStatement("SELECT * FROM " + getTableName() + " WHERE id=?");
    }

    @Override
    public void createTable()
    {
        String sql = createTableStatementSql();

        try
        {
            Statement createTable = connection.createStatement();
            if (!tableExists)
                createTable.executeUpdate(sql);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public void add(TEntity entity)
    {
        Entity ent = new Entity(this);
        ent.setEntity(entity);
        uow.markAsNew(ent);
    }

    @Override
    public void update(TEntity entity)
    {
        Entity ent = new Entity(this);
        ent.setEntity(entity);
        uow.markAsChanged(ent);
    }

    @Override
    public void delete(TEntity entity)
    {
        Entity ent = new Entity(this);
        ent.setEntity(entity);
        uow.markAsDeleted(ent);
    }

    @Override
    public TEntity get(int id)
    {
        try
        {
            selectById.setInt(1, id);
            ResultSet resultSet = selectById.executeQuery();
            while (resultSet.next())
            {
                return mapper.map(resultSet);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void persistAdd(Entity entity)
    {
        try
        {
            setInsert((TEntity) entity.getEntity());
            insert.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void persistUpdate(Entity entity)
    {
        try
        {
            setUpdate((TEntity) entity.getEntity());
            update.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void persistDelete(Entity entity)
    {
        try
        {
            delete.setInt(1, ((TEntity) entity.getEntity()).getId());
            delete.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    protected abstract String createTableStatementSql();
    protected abstract String getInsertQuerySql();
    protected abstract String getUpdateQuerySql();
    protected abstract String getTableName();

    protected abstract void setInsert(TEntity entity) throws SQLException;
    protected abstract void setUpdate(TEntity entity) throws SQLException;

}
