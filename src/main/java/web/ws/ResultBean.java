package web.ws;

public class ResultBean {
	private String name;
	private String gender;
	private String comment;

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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public ResultBean() {
		super();
	}

	public ResultBean(String name, String gender, String comment) {
		super();
		this.name = name;
		this.gender = gender;
		this.comment = comment;
	}

	public String toString() {
		return comment;
	}
}
