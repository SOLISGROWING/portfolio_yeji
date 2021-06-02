package com.crawler.test;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

public class TomatoTest {
	@Test
	public void test() {
	String url="http://www.newstomato.com/CateMain.aspx?Cate=1200";
	Document doc=null;
	try {
		doc=Jsoup.connect(url).timeout(40000).validateTLSCertificates(false).get();
		//System.out.println(Jsoup.connect(url).validateTLSCertificates(false).get());
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	//System.out.println(doc);
	List<String> tempUrl=new ArrayList<String>();
	Elements es = doc.select("b.ti");
	for (Element link : es) {
		
		String absurl = link.select("a").attr("abs:href");
		if(absurl.length()<4) {
			continue;
		}
		tempUrl.add(absurl);
	}
	System.out.println(tempUrl);

}
}
