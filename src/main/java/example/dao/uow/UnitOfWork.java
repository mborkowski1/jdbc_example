package example.dao.uow;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UnitOfWork implements IUnitOfWork
{

    List<Entity> entities = new ArrayList<>();
    private Connection connection;

    public UnitOfWork(Connection connection) throws SQLException
    {
        this.connection = connection;
        this.connection.setAutoCommit(false);
    }

    @Override
    public void saveChanges()
    {
        for (Entity entity: entities)
        {
            entity.persist();
        }
        try
        {
            this.connection.commit();
            entities.clear();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            rollback();
        }
    }

    @Override
    public void rollback()
    {
        entities.clear();
    }

    @Override
    public void markAsNew(Entity entity)
    {
        mark(entity, EntityState.New);
    }

    @Override
    public void markAsChanged(Entity entity)
    {
        mark(entity, EntityState.Changed);
    }

    @Override
    public void markAsDeleted(Entity entity)
    {
        mark(entity, EntityState.Deleted);
    }

    private void mark(Entity entity, EntityState entityState)
    {
        entity.setEntityState(entityState);
        entities.add(entity);
    }

}
