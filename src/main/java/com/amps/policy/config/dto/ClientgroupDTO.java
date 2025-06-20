package com.amps.policy.config.dto;

public class ClientgroupDTO {

	private String clientCode;
	private String clientName;

	private String clientGroupName;

	private Integer clientGroupId;

	private String clientGroupCode;

	private String hp;

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientCode() {
		return clientCode;
	}

	public void setClientCode(String clientCode) {
		this.clientCode = clientCode;
	}

	public String getClientGroupName() {
		return clientGroupName;
	}

	public void setClientGroupName(String clientGroupName) {
		this.clientGroupName = clientGroupName;
	}

	public Integer getClientGroupId() {
		return clientGroupId;
	}

	public void setClientGroupId(Integer clientGroupId) {
		this.clientGroupId = clientGroupId;
	}

	public String getClientGroupCode() {
		return clientGroupCode;
	}

	public void setClientGroupCode(String clientGroupCode) {
		this.clientGroupCode = clientGroupCode;
	}

}
