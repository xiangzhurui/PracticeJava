package com.xiangzhurui.sample.jackson.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer implements Serializable {

    private String customerId;
    private String name;
    private String gender;
    private String birthday;
    private String age;
    private String idNo;
    private String idType;
    private String mobilePhone;

    @JacksonXmlElementWrapper(useWrapping = true, localName = "Addresses")
    @JacksonXmlProperty(localName = "Address")
    private List<Address> addresses;

}
