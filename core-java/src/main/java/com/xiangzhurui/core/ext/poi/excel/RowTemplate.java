package com.xiangzhurui.core.ext.poi.excel;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import lombok.Data;

/**
 * 行模板
 *
 * @author XiangZhuRui
 */
@Data
public class RowTemplate implements Serializable {

  // 姓名
  private String name;
  // 性别
  private String gender;
  // 生日
  private Date birthday;
  // 年龄
  private String age;
  // 证件号码
  private String idNo;
  // 电子邮箱
  private String email;
  // 手机号码
  private String mobilePhone;
  // 备注
  private String remark;

  public RowTemplate(String name, String gender, Date birthday, String age, String idNo, String email,
                     String mobilePhone, String remark) {
    super();
    this.name = name;
    this.gender = gender;
    this.birthday = birthday;
    this.age = age;
    this.idNo = idNo;
    this.email = email;
    this.mobilePhone = mobilePhone;
    this.remark = remark;
  }

  public RowTemplate() {
    super();
  }

  /**
   * 是否有手机号
   *
   * @return
   */
  public boolean hasMobilePhone() {
    return StringUtils.isNotBlank(mobilePhone);
  }

  /**
   * 是否为空行
   *
   * @return
   */
  public boolean isEmptyRow() {
    return (!StringUtils.isNotBlank(mobilePhone) || !StringUtils.isNotBlank(name)) && !StringUtils.isNotBlank(gender)
        && null != birthday && !StringUtils.isNotBlank(age) && !StringUtils.isNotBlank(idNo)
        && !StringUtils.isNotBlank(email);
  }


}
