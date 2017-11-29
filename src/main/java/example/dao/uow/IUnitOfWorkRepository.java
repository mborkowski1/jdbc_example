package example.dao.uow;

public interface IUnitOfWorkRepository
{

    void persistAdd(Entity entity);
    void persistUpdate(Entity entity);
    void persistDelete(Entity entity);

}
