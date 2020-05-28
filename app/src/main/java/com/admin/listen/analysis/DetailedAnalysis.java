package com.admin.listen.analysis;

import com.admin.listen.analysis.entity.DetailedData;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class DetailedAnalysis extends AnalysisBase<DetailedData> {

	@Override
	public DetailedData analysis(String html) {
		if(html==null)return null;
		DetailedData detailedData=new DetailedData();
		Document doc=Jsoup.parse(html);
		Elements elements=doc.select("div[class=info-article wordmemoryhead wordmemoryhead2]");
		String english=elements.select("div[class=ht]").select("h1").text().split("音标：")[0].replace("单词：", "");
		String phoneticSymbol=elements.select("div[class=ht]").select("font").text();
		String pronunciation=elements.select("div[class=hb]").select("i").attr("data-src");
		String chinese=elements.select("div[class=hb]").select("div[class=lmemo]").select("span[class=word_mean]").text();
		String skill=elements.select("div[class=hb]").select("div[class=lmemo]").select("b[class=memory_method]").text();
		List<DetailedData.ExampleData> list=new ArrayList<DetailedData.ExampleData>();
		for(Element element:elements.select("div[class=lmemo exam-lmemo]")) {
			DetailedData.ExampleData exampleData=new DetailedData.ExampleData();
			String en_example=element.select("p").get(0).text();
			String ch_example=element.select("p").get(1).text();
			exampleData.setCh_example(ch_example);
			exampleData.setEn_example(en_example);
			list.add(exampleData);
		}
		detailedData.setChinese(chinese.trim());
		detailedData.setEnglish(english.trim());
		detailedData.setList(list);
		detailedData.setPhoneticSymbol(phoneticSymbol);
		detailedData.setPronunciation("https://www.suxuewang.com"+pronunciation);
		detailedData.setSkill(skill);
		return detailedData;
	}

}
