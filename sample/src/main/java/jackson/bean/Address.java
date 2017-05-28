package jackson.bean;

public class Address {
    private String zipCode;
    private String province;
    private String distinct;
    private String city;

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistinct() {
        return distinct;
    }

    public void setDistinct(String distinct) {
        this.distinct = distinct;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Address(String zipCode, String province, String distinct, String city) {
        super();
        this.zipCode = zipCode;
        this.province = province;
        this.distinct = distinct;
        this.city = city;
    }

    public Address() {
        super();
    }

}
