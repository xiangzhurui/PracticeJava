package com.xiangzhurui.sample.jackson;

import java.util.ArrayList;
import java.util.List;

import com.xiangzhurui.sample.jackson.bean.Address;
import com.xiangzhurui.sample.jackson.bean.Customer;

public class JacksonMapperUtilsUseCase {

    public static void main(String[] args) {
        Address a = new Address("zip111", "prsff", "distinct", "city");
        Address a1 = new Address("sfasf", "prsff", "distinct", "city");
        List<Address> as = new ArrayList<Address>();
        as.add(a);
        as.add(a1);
        Customer c = new Customer("customerId", "name", "gender", "birthday", "age", "idNo", "idType", "mobilePhoneTest", as);

        System.out.println(JacksonMapperUtils.toJSON(c).orElse(null));
        System.out.println(JacksonMapperUtils.toXML(c).orElse(null));
        System.out.println(JacksonMapperUtils.toJSON(as).orElse(null));
        System.out.println(JacksonMapperUtils.toXML(as).orElse(null));

    }




}
