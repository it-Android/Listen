package com.admin.listen.analysis;



import com.admin.listen.analysis.entity.GradeAllData;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GradeAnalysis extends AnalysisBase<GradeAllData> {

	@Override
	public GradeAllData analysis(String html) {
		if(html==null)return null;
		GradeAllData gradeAllData=new GradeAllData();
		Document doc=Jsoup.parse(html);
        Elements el=doc.select("head").select("title");
		String grade=el.text();
		Elements elements=doc.select("div[class=Word_select]").select("div[class=BigFrame]");
		List<GradeAllData.GradeData> list=new ArrayList<>();
		for(Element element:elements) {
			GradeAllData.GradeData gradeData=new GradeAllData.GradeData();
			String name=element.select("h2").text();
			gradeData.setName(name);
			Elements eles=element.select("li");
			List<GradeAllData.FrameData> frame=new ArrayList<>();
			for(Element ele:eles) {
				GradeAllData.FrameData frameData=new GradeAllData.FrameData();
				String frameName=ele.select("a[class=Unit]").text();
				String urlPath=ele.select("a").attr("href");
				frameData.setFrameName(frameName);
				frameData.setUrlPath("https://www.suxuewang.com"+urlPath);
				frame.add(frameData);
			}
			gradeData.setFrame(frame);
			list.add(gradeData);
		}
		//System.out.println(list.toString());
        int index = grade.indexOf("_");
        gradeAllData.setGrade(grade.substring(0,index==-1? grade.length():index));
		gradeAllData.setList(list);
		return gradeAllData;
	}

}
