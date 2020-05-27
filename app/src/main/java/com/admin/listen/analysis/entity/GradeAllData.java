package com.admin.listen.analysis.entity;

import java.util.List;

/**
  *  等级数据
 * @author JQ
 *
 */
public class GradeAllData {
	private String grade;
	private List<GradeData> list;

	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public List<GradeData> getList() {
		return list;
	}
	public void setList(List<GradeData> list) {
		this.list = list;
	}

	//单个等级的信息
	public static class GradeData{
		private String name;
		private List<FrameData> frame;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public List<FrameData> getFrame() {
			return frame;
		}
		public void setFrame(List<FrameData> frame) {
			this.frame = frame;
		}

		@Override
		public String toString() {
			StringBuffer buffer=new StringBuffer();
			buffer.append("版本：").append(name);
			if(frame!=null) {
				buffer.append("年级：").append(frame.toString());
			}
			return buffer.toString();
		}
	}
	//一个学期的书
	public static class FrameData{
		private String frameName;
		private String urlPath;
		public String getFrameName() {
			return frameName;
		}
		public void setFrameName(String frameName) {
			this.frameName = frameName;
		}
		public String getUrlPath() {
			return urlPath;
		}
		public void setUrlPath(String urlPath) {
			this.urlPath = urlPath;
		}
		
		@Override
		public String toString() {
			StringBuffer buffer=new StringBuffer();
			buffer.append("名称：").append(frameName).append(" ，连接：").append(urlPath);
			return buffer.toString();
		}
		
		
	}
	
}
