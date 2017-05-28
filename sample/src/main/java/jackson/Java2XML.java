package jackson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.fasterxml.jackson.xml.XmlMapper;
import com.google.gson.Gson;

import jackson.bean.Address;
import jackson.bean.Customer;

public class Java2XML {

    public static void main(String[] args) {
        Address a = new Address("zip111", "prsff", "distinct", "city");
        Address a1 = new Address("sfasf", "prsff", "distinct", "city");
        List<Address> as = new ArrayList<Address>();
        as.add(a);
        as.add(a1);
        Customer c = new Customer("customerId", "name", "gender", "birthday", "age", "idNo", "idType", "mobilePhoneTest", as);
        Gson g = new Gson();
        String s = g.toJson(c, Customer.class);
        System.out.println(s);

    }


    public static String toXML(Object object) {
        return toXML(object, "root");
    }


    public static String toXML(Object object, String rootName) {
        try {
            XmlMapper xml = JacksonMapper.getXmlMapper();
            // Object to XML
            String xmlStr = xml.writeValueAsString(object);
            String xmlHeader = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
            // Object Class Name
            String mapClassName = object.getClass().getSimpleName();
            String beginStr = "<" + mapClassName + " xmlns=\"\">";
            String endStr = "</" + mapClassName + ">";
            int beginNum = beginStr.length();
            int endNum = xmlStr.indexOf(endStr);
            String subStr = xmlStr.substring(beginNum, endNum);
            StringBuffer sb = new StringBuffer();
            sb.append(xmlHeader).append("<" + rootName + ">").append(subStr).append("</" + rootName + ">");
            return sb.toString();
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
