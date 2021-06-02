package com.crawler.parser;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crawler.vo.ArticleVO;

//cate 별로 정리해보자 
public class NaeilParser extends parser{
	/*private Document parseDocument(String url) {
		Document doc= null;
		try {
			doc=Jsoup.connect(url).timeout(40000).validateTLSCertificates(false).get();
			//System.out.println(Jsoup.connect(url).validateTLSCertificates(false).get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
		
	}
	private String nowTime() {
		String today = null;
		Date date3 = new Date();
		SimpleDateFormat sdformat1 = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date3);
		today = sdformat1.format(cal1.getTime());
		return today;
	}*/
	private String parsedate(Document doc) {
		String date = doc.select("div.date").text();
		String baredate=date.replaceAll(":", "").replaceAll("-", "");
		String Date=baredate.substring(baredate.indexOf("면")+2).replaceAll(" ", "").replaceAll("게재", "");
		return Date;
	}
	private String parseTitle(Document doc) {
		String title = doc.select("div.articleArea>h3").text();
		System.out.println(title);
		return title;
	}
	private String parseWriter(Document doc) {
		String writer = doc.select("div.byLine").text();
		if(writer.isEmpty()) {
			return " ";
		}
		int cutPoint = writer.indexOf(" ");
		return writer.substring(0,cutPoint);
	}
	private String parsebody(Document doc) {
		String body = doc.select("div.article").text();
		//System.out.println(body);
		return body;
	}
	
	public List<String> extractArticleUrls(String url) throws IOException {
		Document doc=null;
		try {
			doc=Jsoup.connect(url).timeout(40000).validateTLSCertificates(false).get();
			//System.out.println(Jsoup.connect(url).validateTLSCertificates(false).get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> tempUrl=new ArrayList<String>();
		if(url!="http://www.naeil.com/news_list/?cate=01008000") {
			Elements es = doc.select("div.newsList08");
			for (Element link : es) {
				
				String absurl = link.select("a").attr("abs:href");
				if(absurl.isEmpty()) {
					continue;
				}
				tempUrl.add(absurl);
			}
			
		}
		else {
			Elements es = doc.select("p.contL_L_title");
			for (Element link : es) {
				
				String absurl = link.select("a").attr("abs:href");
				if(absurl.isEmpty()) {
					continue;
				}
				tempUrl.add(absurl);
			}
			
		}
		
		return tempUrl;
		
	}
	public ArticleVO crawlArticle(String url) throws IOException {
		ArticleVO vo = new ArticleVO();
		//Document doc=null;
		
		//doc=parse(url);
		vo.setUrl(url);
		vo.setTitle(parseTitle(parse(url)));
		vo.setArticleId(999);
		vo.setSiteIdOld(999);
		vo.setBody(parsebody(parse(url)));
		vo.setCollectedBy("M");
		//vo.setCrawlDate(nowTime());
		vo.setHitCount(0);
		vo.setCreateDate(parsedate(parse(url)));
		vo.setCommentCount(0);
		vo.setLikeCount(0);
		//vo.setWriterId(parseWriter(parseDocument(url)));
		//vo.setWriterName(parseWriter(parseDocument(url)));
		vo.setWriterId("내일신문");
		vo.setWriterName("내일신문");
		vo.setRt("F");
		vo.setReplyId("0");
		vo.setRe("F");
		vo.setLat("0");
		vo.setLng("0");
		vo.setSiteType("M");
		vo.setAddressStatus("N");
		vo.setMention("N");
		vo.setReputationType("E");
		vo.setSiteSubType("내일신문");
		vo.setContentId(" ");
		vo.setSiteId(" ");
		vo.setSiteCode(0000);
		vo.setRtCount(0);
		vo.setFollowerCount(0);
		vo.setSiteName("내일신문");
		vo.setSiteCategory("인터넷신문,언론");
		vo.setLikeCount(0);
		System.out.println(vo.toString());
		return vo;
	}
	@Override
	public Document parse(String url) throws IOException {
		// TODO Auto-generated method stub
		Document doc= null;
		try {
			doc=Jsoup.connect(url).timeout(40000).validateTLSCertificates(false).get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
}
