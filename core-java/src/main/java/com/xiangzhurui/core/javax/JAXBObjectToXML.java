package com.xiangzhurui.core.javax;

import java.io.File;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;

public class JAXBObjectToXML {

  public static void main(String[] args) throws JAXBException {
    StringWriter sw = new StringWriter();
    /* 初始化java对象 */
    User user = new User();
    user.setAccount("accountTest");
    user.setPassword("passwordTest");

    /* 初始化 jaxb marshaler */
    JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

    /* 设置为格式化输出 */
    jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

    /* 将java对象 编组 为xml (输出到文件或标准输出) */
    jaxbMarshaller.marshal(user, new File("user.xml"));
    // jaxbMarshaller.marshal(user, System.out);
    // 转换为字符串
    jaxbMarshaller.marshal(user, sw);
    String str = sw.toString();
    System.out.println(str);
  }

  @XmlRootElement
  public static class User {

    private String account;
    private String password;

    public String getAccount() {
      return account;
    }

    public void setAccount(String account) {
      this.account = account;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }
}