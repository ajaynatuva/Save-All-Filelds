package com.amps.policy.config.dto;

public class DeltaDTO {

	private String source;
	private String deltaDropboxPath;
	private String sourceDropboxPath;

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDeltaDropboxPath() {
		return deltaDropboxPath;
	}

	public void setDeltaDropboxPath(String deltaDropboxPath) {
		this.deltaDropboxPath = deltaDropboxPath;
	}

	public String getSourceDropboxPath() {
		return sourceDropboxPath;
	}

	public void setSourceDropboxPath(String sourceDropboxPath) {
		this.sourceDropboxPath = sourceDropboxPath;
	}

}
