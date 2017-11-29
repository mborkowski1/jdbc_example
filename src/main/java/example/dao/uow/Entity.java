package example.dao.uow;

import example.domain.IHaveId;

public class Entity
{

    private EntityState entityState;
    private Object entity;
    private IUnitOfWorkRepository iUnitOfWorkRepository;

    public Entity(IUnitOfWorkRepository iUnitOfWorkRepository)
    {
        this.iUnitOfWorkRepository = iUnitOfWorkRepository;
        this.entityState = EntityState.Unchanged;
    }

    public void setEntityState(EntityState entityState) {
        this.entityState = entityState;
    }

    public Object getEntity() {
        return entity;
    }

    public <TEntity extends IHaveId> void setEntity(TEntity entity)
    {
        this.entity = entity;
    }

    public void persist()
    {
        switch (this.entityState)
        {
            case New:
                iUnitOfWorkRepository.persistAdd(this);
                break;
            case Changed:
                iUnitOfWorkRepository.persistUpdate(this);
                break;
            case Deleted:
                iUnitOfWorkRepository.persistDelete(this);
                break;
            case Unchanged:
                break;
            default:
                break;
        }
    }

}
