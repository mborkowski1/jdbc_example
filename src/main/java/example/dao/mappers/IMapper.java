package example.dao.mappers;

import example.domain.IHaveId;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IMapper<TEntity extends IHaveId>
{

    TEntity map(ResultSet resultSet) throws SQLException;

}
