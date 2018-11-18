package com.yinww.account.domain;

import java.io.Serializable;
import java.util.Date;

import org.apache.ibatis.type.Alias;

/**
 * Tenant 租户
 * @author yinww
 *
 */
@Alias("tenant")
public class Tenant implements Serializable {
	private static final long serialVersionUID = 7841144358194132207L;
	
	private String id;
	private String name;
	private String code;
	private String mainAccount;
	private String mainAccountPwd;
	private String contact;
	private String mobile;
	private String phone;
	private String email;
	private String address;
	private String logo;
	private int type; // 0个人, 1企业, 2个体工商户, 3政府, 4媒体, 5其他组织
	private int status; // 0已注册, 1已审核, 2已认证, 3已冻结, 4已注销
	private String note;
	private String accessKey;
	private String creator;
	private Date createTime;
	private String editor;
	private Date editTime;
	
	public Tenant() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMainAccount() {
		return mainAccount;
	}

	public void setMainAccount(String mainAccount) {
		this.mainAccount = mainAccount;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
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
	
	public String getAccessKey() {
		return accessKey;
	}

	public void setAccessKey(String accessKey) {
		this.accessKey = accessKey;
	}
	
	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getMainAccountPwd() {
		return mainAccountPwd;
	}

	public void setMainAccountPwd(String mainAccountPwd) {
		this.mainAccountPwd = mainAccountPwd;
	}

	@Override
	public String toString() {
		return "Tenant{id=" + id + ", name=" + name + ", code=" + code + "}";
	}

}
