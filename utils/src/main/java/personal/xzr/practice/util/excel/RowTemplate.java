package personal.xzr.practice.util.excel;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

/**
 * 行模板
 * 
 * @author XiangZhuRui
 * @date 2016.11.22
 */
public class RowTemplate {
	// 姓名
	private String	name;
	// 性别
	private String	gender;
	// 生日
	private Date	birthday;
	// 年龄
	private String	age;
	// 证件号码
	private String	idNo;
	// 电子邮箱
	private String	email;
	// 手机号码
	private String	mobilePhone;
	// 备注
	private String	remark;

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
		if (StringUtils.isNotBlank(mobilePhone)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 是否为空行
	 * 
	 * @return
	 */
	public boolean isEmptyRow() {
		if (StringUtils.isNotBlank(mobilePhone) && StringUtils.isNotBlank(name) || StringUtils.isNotBlank(gender)
		        || null == birthday || StringUtils.isNotBlank(age) || StringUtils.isNotBlank(idNo)
		        || StringUtils.isNotBlank(email)) {
			return false;
		} else {
			return true;
		}
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

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
