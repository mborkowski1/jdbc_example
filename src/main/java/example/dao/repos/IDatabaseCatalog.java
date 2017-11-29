package example.dao.repos;

public interface IDatabaseCatalog
{

    IAddressRepository addresses();
    IStudentRepository students();

}
