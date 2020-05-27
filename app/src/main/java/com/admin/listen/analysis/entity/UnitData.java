package com.admin.listen.analysis.entity;

import java.util.List;

/**
 * 单元数据
 * @author JQ
 *
 */
public class UnitData {

	private String bookName;
	private int number;
	private List<OneUnit> listUnit;
	
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public List<OneUnit> getListUnit() {
		return listUnit;
	}
	public void setListUnit(List<OneUnit> listUnit) {
		this.listUnit = listUnit;
	}
	public String toString() {
		StringBuffer buffer=new StringBuffer();
		buffer.append("书名：").append(bookName).append("\n");
		buffer.append("单词总数：").append(number).append("\n");
		if(listUnit!=null) {
			buffer.append("章节：\n").append(listUnit.toString());
		}
		return buffer.toString();
	}
	public static class OneUnit {
		private String pageName;
		private int pageNum;
		private String pageUrl;
		
		public String getPageName() {
			return pageName;
		}
		public void setPageName(String pageName) {
			this.pageName = pageName;
		}
		public int getPageNum() {
			return pageNum;
		}
		public void setPageNum(int pageNum) {
			this.pageNum = pageNum;
		}
		public String getPageUrl() {
			return pageUrl;
		}
		public void setPageUrl(String pageUrl) {
			this.pageUrl = pageUrl;
		}
		public String toString() {
			StringBuffer buffer=new StringBuffer();
			buffer.append("单元名称：").append(pageName).append("，");
			buffer.append("单词数量：").append(pageNum).append("，");
			buffer.append("单词地址：").append(pageUrl);
			return buffer.toString();
		}
		
		
		
	}
	
}
