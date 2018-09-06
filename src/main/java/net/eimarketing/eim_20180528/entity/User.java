package net.eimarketing.eim_20180528.entity;

public class User {

	private String employee_id;// 员工编码
	private String employee_name;// 员工姓名
	private String employee_identity;// 员工身份
	private String employee_department;// 所在部门
	private String password;// 密码
	private String employee_area;// 所在地区
	
	private String openId;
	private String rater_id;//参赛人员对应的评委id
	

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getEmployee_identity() {
		return employee_identity;
	}

	public void setEmployee_identity(String employee_identity) {
		this.employee_identity = employee_identity;
	}

	public String getEmployee_department() {
		return employee_department;
	}

	public void setEmployee_department(String employee_department) {
		this.employee_department = employee_department;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmployee_area() {
		return employee_area;
	}

	public void setEmployee_area(String employee_area) {
		this.employee_area = employee_area;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getRater_id() {
		return rater_id;
	}

	public void setRater_id(String rater_id) {
		this.rater_id = rater_id;
	}
	
	
}
