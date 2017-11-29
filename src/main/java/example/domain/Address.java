package example.domain;

public class Address implements IHaveId
{

    private int id;
    private String street;
    private String city;
    private String houseNumer;
    private String postCode;
    private int idStudent;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHouseNumer() {
        return houseNumer;
    }

    public void setHouseNumer(String houseNumer) {
        this.houseNumer = houseNumer;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
    }

}
