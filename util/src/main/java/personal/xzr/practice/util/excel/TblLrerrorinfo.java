package personal.xzr.practice.util.excel;

import java.util.Date;

/**
 * 错误信息实体
 * 
 * @author XiangZhuRui
 * @date 2016.11.22
 */
public class TblLrerrorinfo {
	// 入库批次号
	private String	guideNum;
	// 数据有误的行号
	private Integer	rowNumB;
	// 导入次数,默认值[1]
	private Integer	guideTimes;
	// 错误原因
	private String	errorreason;
	// 错误行，默认值[1]
	private Integer	errorrow;
	// 创建日期
	private Date	makedate;
	// 创建时间
	private String	maketime;
	// 修改日期
	private Date	modifydate;
	// 修改时间
	private String	modifytime;
	// 操作人
	private String	operator;

	public String getGuideNum() {
		return guideNum;
	}

	public void setGuideNum(String guideNum) {
		this.guideNum = guideNum;
	}

	public Integer getRowNumB() {
		return rowNumB;
	}

	public void setRowNumB(Integer rowNumB) {
		this.rowNumB = rowNumB;
	}

	public Integer getGuideTimes() {
		return guideTimes;
	}

	public void setGuideTimes(Integer guideTimes) {
		this.guideTimes = guideTimes;
	}

	public String getErrorreason() {
		return errorreason;
	}

	public void setErrorreason(String errorreason) {
		this.errorreason = errorreason;
	}

	public Integer getErrorrow() {
		return errorrow;
	}

	public void setErrorrow(Integer errorrow) {
		this.errorrow = errorrow;
	}

	public Date getMakedate() {
		return makedate;
	}

	public void setMakedate(Date makedate) {
		this.makedate = makedate;
	}

	public String getMaketime() {
		return maketime;
	}

	public void setMaketime(String maketime) {
		this.maketime = maketime;
	}

	public Date getModifydate() {
		return modifydate;
	}

	public void setModifydate(Date modifydate) {
		this.modifydate = modifydate;
	}

	public String getModifytime() {
		return modifytime;
	}

	public void setModifytime(String modifytime) {
		this.modifytime = modifytime;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public TblLrerrorinfo(String guideNum, Integer rowNumB, Integer guideTimes, String errorreason, Integer errorrow,
	        Date makedate, String maketime, Date modifydate, String modifytime, String operator) {
		super();
		this.guideNum = guideNum;
		this.rowNumB = rowNumB;
		this.guideTimes = guideTimes;
		this.errorreason = errorreason;
		this.errorrow = errorrow;
		this.makedate = makedate;
		this.maketime = maketime;
		this.modifydate = modifydate;
		this.modifytime = modifytime;
		this.operator = operator;
	}

	public TblLrerrorinfo() {
		super();
	}
}