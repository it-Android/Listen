package com.admin.listen.analysis;

import com.admin.listen.analysis.entity.UnitData;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class UnitAnalysis extends AnalysisBase<UnitData>{

	@Override
	public UnitData analysis(String html) {
		if(html==null)return null;
		UnitData unitData=new UnitData();
		Document doc=Jsoup.parse(html);
		Elements elements=doc.select("div[class=word_list_box]");
		String bookName=elements.select("h1").text();
		List<UnitData.OneUnit> listUnit=new ArrayList<>();
		int number=0;
		for(Element element:elements.select("div[class=unit_content_box]")) {
			Elements eles=element.select("ul[class=top_list]").select("li");
			if(eles.size()!=6)continue;
			UnitData.OneUnit oneUnit=new UnitData.OneUnit();
			String pageName=eles.get(0).text();
			String pageNum=eles.get(1).text();
			String pageUrl=eles.get(5).select("a").attr("href");
			int num=strToInt(pageNum);
			oneUnit.setPageName(pageName);
			oneUnit.setPageNum(num);
			oneUnit.setPageUrl("https://www.suxuewang.com"+pageUrl);
			number+=num;
			listUnit.add(oneUnit);
		}
		unitData.setBookName(bookName);
		unitData.setListUnit(listUnit);
		unitData.setNumber(number);
		//System.out.println(unitData.toString());
		return unitData;
	}

}
