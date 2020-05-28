package com.admin.listen.analysis;

import com.admin.listen.analysis.entity.WordData;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



public class WordAnalysis extends AnalysisBase<List<WordData>> {

	@Override
	public List<WordData> analysis(String html) {
		if(html==null)return null;
		List<WordData> list=new ArrayList<>();
		Document doc=Jsoup.parse(html);
		Elements elements=doc.select("div[class=select_box]").select("div[class=select_initial]").select("ul");
		for(Element element:elements) {
			WordData wordData=new WordData();
			String english=element.select("li[class=initial_words]").text();
			String phoneticSymbol=element.select("li[class=initial_phonetic]").select("font").text();
			String chinese=element.select("li[class=initial_solve]").text();
			String pronunciation=element.select("li").select("i[class=fa fa-volume-up playico animate-hover]").attr("data-src");
			String detailedUrl=element.select("li[class=see_details]").select("a").attr("href");

			wordData.setChinese(chinese.trim());
			wordData.setDetailedUrl("https://www.suxuewang.com"+detailedUrl);
			wordData.setEnglish(english.trim());
			wordData.setPhoneticSymbol(phoneticSymbol.trim());
			wordData.setPronunciation("https://www.suxuewang.com"+pronunciation);
			list.add(wordData);
		}
		return list;
	}

}
