package example.dao.repos;

import example.domain.IHaveId;

public interface IRepository<TEntity extends IHaveId>
{

    void createTable();
    void add(TEntity entity);
    void update(TEntity entity);
    void delete(TEntity entity);
    TEntity get(int id);

}
