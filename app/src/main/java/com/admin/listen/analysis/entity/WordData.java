package com.admin.listen.analysis.entity;

public class WordData {
	private String english;//英语
	private String chinese;//中文
	private String phoneticSymbol;//音标
	private String detailedUrl;//详细信息连接
	private String pronunciation;//发音连接
	
	public String getPronunciation() {
		return pronunciation;
	}
	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}

	public String getEnglish() {
		return english;
	}
	public void setEnglish(String english) {
		this.english = english;
	}
	public String getChinese() {
		return chinese;
	}
	public void setChinese(String chinese) {
		this.chinese = chinese;
	}
	public String getPhoneticSymbol() {
		return phoneticSymbol;
	}
	public void setPhoneticSymbol(String phoneticSymbol) {
		this.phoneticSymbol = phoneticSymbol;
	}
	public String getDetailedUrl() {
		return detailedUrl;
	}
	public void setDetailedUrl(String detailedUrl) {
		this.detailedUrl = detailedUrl;
	}

	public String toString() {
		StringBuffer buffer=new StringBuffer();
		buffer.append("单词：").append(english);
		buffer.append("，中文：").append(chinese);
		buffer.append("，音标：").append(phoneticSymbol);
		buffer.append("，详细信息连接：").append(detailedUrl);
		buffer.append("，发音连接：").append(pronunciation);
		
		return buffer.toString();
	}
	
	
}
