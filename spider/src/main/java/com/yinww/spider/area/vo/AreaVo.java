package com.yinww.spider.area.vo;

import java.util.List;

public class AreaVo {

	private String code;

	private String name;

	private List<AreaVo> children;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<AreaVo> getChildren() {
		return children;
	}

	public void setChildren(List<AreaVo> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "AreaVo{" + "code='" + code + '\'' + ", name='" + name + '\'' + ", children=" + children + '}';
	}
}