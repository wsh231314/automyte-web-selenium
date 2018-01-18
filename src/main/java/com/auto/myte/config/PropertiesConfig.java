package com.auto.myte.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:/config/logic.properties")
@ConfigurationProperties(prefix = "myte")
public class PropertiesConfig {

	private String filepath;

	private String imageUrl;

	private String runScript;

	private String runfolder;

	private String locZip;

	private String downloadZip;

	private String excel;
	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getRunScript() {
		return runScript;
	}

	public void setRunScript(String runScript) {
		this.runScript = runScript;
	}

	public String getRunfolder() {
		return runfolder;
	}

	public void setRunfolder(String runfolder) {
		this.runfolder = runfolder;
	}

	public String getLocZip() {
		return locZip;
	}

	public void setLocZip(String locZip) {
		this.locZip = locZip;
	}

	public String getDownloadZip() {
		return downloadZip;
	}

	public void setDownloadZip(String downloadZip) {
		this.downloadZip = downloadZip;
	}

	public String getExcel() {
		return excel;
	}

	public void setExcel(String excel) {
		this.excel = excel;
	}

}
