package com.yinww.web.core.domain;

import java.io.Serializable;
import java.util.Date;

public class Account implements Serializable {
	
	private static final long serialVersionUID = 5483313813369766000L;
	
	private String id;
	private String loginName;
	private int type; // 账号类型，0子账号, 1主账号
	private String password;
	private String displayName;
	private String realName;
	private String tenantId;
	private String tenantName;
	private String mobile;
	private String phone;
	private String email;
	private String head_img;
	private int gender; // 性别，1男, 0女
	private int status; // 状态，0被锁定, 1正常, 2已审核, 3已认证
	private int grade; // 等级，0子账号, 1主账号
	private String creator;
	private Date createTime;
	private String editor;
	private Date editTime;
	
	public Account() {
	}

	public Account(String id, String tenantId, String loginName, String creator) {
		this.id = id;
		this.tenantId = tenantId;
		this.loginName = loginName;
		this.creator = creator;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getHead_img() {
		return head_img;
	}

	public void setHead_img(String head_img) {
		this.head_img = head_img;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getEditor() {
		return editor;
	}

	public void setEditor(String editor) {
		this.editor = editor;
	}

	public Date getEditTime() {
		return editTime;
	}

	public void setEditTime(Date editTime) {
		this.editTime = editTime;
	}

	@Override
	public String toString() {
		return new StringBuilder("Account{id=").append(id).append(", loginName=").append(loginName)
				.append(", mobile=").append(mobile)
				.append(", tenantName=").append(tenantName).append("}").toString();
	}
}
