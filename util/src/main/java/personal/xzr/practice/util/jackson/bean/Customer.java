package personal.xzr.practice.util.jackson.bean;

import java.util.List;

public class Customer {
    private String        customerId;
    private String        name;
    private String        gender;
    private String        birthday;
    private String        age;
    private String        idNo;
    private String        idType;
    private String        mobilePhone;
    private List<Address> addresses;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public Customer(String customerId, String name, String gender, String birthday, String age, String idNo, String idType,
            String mobilePhone, List<Address> addresses) {
        super();
        this.customerId = customerId;
        this.name = name;
        this.gender = gender;
        this.birthday = birthday;
        this.age = age;
        this.idNo = idNo;
        this.idType = idType;
        this.mobilePhone = mobilePhone;
        this.addresses = addresses;
    }

    public Customer() {
        super();
    }
}
