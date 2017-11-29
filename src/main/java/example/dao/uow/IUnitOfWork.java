package example.dao.uow;

public interface IUnitOfWork
{

    void saveChanges();
    void rollback();
    void markAsNew(Entity entity);
    void markAsChanged(Entity entity);
    void markAsDeleted(Entity entity);

}
