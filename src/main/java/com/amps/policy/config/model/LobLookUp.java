package com.amps.policy.config.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lob_lkp", schema = "policy")
public class LobLookUp {

	@Id
	@Column(name = "lob_key")
	private Integer lobKey;

	@Column(name = "lob_title")
	private String lobTitle;

    public LobLookUp() {
    }

    public Integer getLobKey() {
		return lobKey;
	}

	public void setLobKey(Integer lobKey) {
		this.lobKey = lobKey;
	}

	public String getLobTitle() {
		return lobTitle;
	}

	public void setLobTitle(String lobTitle) {
		this.lobTitle = lobTitle;
	}

}