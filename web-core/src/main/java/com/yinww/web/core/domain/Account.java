package com.yinww.web.core.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Account implements Serializable, UserDetails {
	
	private static final long serialVersionUID = 5483313813369766000L;
	
	private Long id;
	private String loginName;
	private int type; // 账号类型，0子账号, 1主账号
	private String password;
	private String displayName;
	private String realName;
	private Long tenantId;
	private String tenantName;
	private String mobile;
	private String phone;
	private String email;
	private String headImg;
	private int gender; // 性别，1男, 0女
	private int status; // 状态，0已注册, 1已审核, 2已认证, 3已冻结, 4已注销
	private int grade; // 等级，0子账号, 1主账号
	private String creator;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private String editor;
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date editTime;
	private String language;
	
	public Account() {
	}

	public Account(Long id, Long tenantId, String loginName, String creator) {
		this.id = id;
		this.tenantId = tenantId;
		this.loginName = loginName;
		this.creator = creator;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
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

	public String getHeadImg() {
		return headImg;
	}

	public void setHeadImg(String headImg) {
		this.headImg = headImg;
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

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	@Override
	public String toString() {
		return new StringBuilder("Account{id=").append(id).append(", loginName=").append(loginName)
				.append(", mobile=").append(mobile)
				.append(", tenantName=").append(tenantName).append("}").toString();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getUsername() {
		return loginName;
	}

	@Override
	public boolean isAccountNonExpired() {
		// 状态，0已注册, 1已审核, 2已认证, 3已冻结, 4已注销
		return status < 3;
	}

	@Override
	public boolean isAccountNonLocked() {
		return status < 3;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return status < 3;
	}

	@Override
	public boolean isEnabled() {
		return status < 3;
	}
}
