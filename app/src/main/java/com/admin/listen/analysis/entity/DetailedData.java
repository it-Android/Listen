package com.admin.listen.analysis.entity;

import java.util.List;

/**
 * 单词详细信息
 * @author JQ
 *
 */
public class DetailedData {

	private String english;//英语
	private String chinese;//中文
	private String phoneticSymbol;//音标
	private String pronunciation;//发音连接
	private String skill;//技巧
	private List<ExampleData> list;//例子

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

	public String getPronunciation() {
		return pronunciation;
	}

	public void setPronunciation(String pronunciation) {
		this.pronunciation = pronunciation;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public List<ExampleData> getList() {
		return list;
	}

	public void setList(List<ExampleData> list) {
		this.list = list;
	}

	public static class ExampleData{
		private String en_example;
		private String ch_example;//
		public String getEn_example() {
			return en_example;
		}
		public void setEn_example(String en_example) {
			this.en_example = en_example;
		}
		public String getCh_example() {
			return ch_example;
		}
		public void setCh_example(String ch_example) {
			this.ch_example = ch_example;
		}
		
		
	}
	
	
	
}
