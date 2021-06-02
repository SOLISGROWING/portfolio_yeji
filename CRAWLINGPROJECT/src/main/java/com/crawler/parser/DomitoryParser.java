package com.crawler.parser;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crawler.parser.parser;
import com.crawler.vo.ArticleVO;
/*import com.tapacross.sns.crawler.massmedia.entity.SelectQuery;
import com.tapacross.sns.parser.JSoupFactory;
import com.tapacross.sns.util.DateFormatUtil;*/
public class DomitoryParser extends parser {
	private String parseTitle(Document doc) {
		String bareTitle = doc.select("h1.np_18px > a").text();
		return bareTitle;
	}
	
	private Integer parseCommentn(Document doc) {
		String commentNum = doc.select("div.side.fr>span:eq(2)>b").text();
		Integer commentCount = Integer.parseInt(commentNum);
		return commentCount;
	}
	
	/*private String findCate(Document doc) {
		String realCate=doc.select("head>title").text();
		String rrealCate="디미토리"+"-"+realCate.substring(0,realCate.indexOf("-"));
		return rrealCate;
	}*/
	
	private Integer parseHitCount(Document doc) {
		String hitCount = doc.select("div.side.fr>span:eq(0)>b").text();
		Integer HitCount = Integer.parseInt(hitCount);
		return HitCount;
	}
	private String parseBody(Document doc) {
		String body = doc.select("article.read_body").text().replaceAll(" ","");;
		return body;
	}
	private String parseDate(Document doc) {
		String date = doc.select("span.date").text();
		String original = null;
		original = date;
		String cut1 = original.substring(0, 4);
		String cut2 = original.substring(5, 7);
		String cut3 = original.substring(8, 10);
		String cut4 = original.substring(11, 13);
		String cut5 = original.substring(14, 16);
		String cut6 = "00";
		String totalDate = cut1 + cut2 + cut3 + cut4 + cut5 + cut6;
		return totalDate;
	}
	private String nowTime() {
		String today = null;
		Date date3 = new Date();
		SimpleDateFormat sdformat1 = new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar cal1 = Calendar.getInstance();
		cal1.setTime(date3);
		today = sdformat1.format(cal1.getTime());
		return today;
	}
	
	
	// 게시글 목록의 각각의 URL을 얻는것 
	@Override
	public List<String> extractArticleUrls(String url) throws IOException {
		Document doc=null;
		try {
			doc=Jsoup.connect(url).timeout(40000).validateTLSCertificates(false).get();
			//System.out.println(Jsoup.connect(url).validateTLSCertificates(false).get());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> tempUrl=new ArrayList<String>();
		Elements es = doc.select("tbody.hide_notice>tr");
		for (Element link : es) {
			//String cate = link.select("td.cate").text();
			/*if (cate.contains("정보") || cate.isEmpty() == true) {
				continue;
			}*/
			String absurl = link.select("td.title>a.hx").attr("abs:href");
			if(absurl.isEmpty()) {
				continue;
			}
			tempUrl.add(absurl);
		}
	
		return tempUrl;
	}
	
	
	
	
	
	
	
	
	// 본문을 긁는것 
	@Override
	public ArticleVO crawlArticle(String url) throws IOException {
		ArticleVO vo = new ArticleVO();
		List<ArticleVO> list = new ArrayList<ArticleVO>();
		Document doc=null;
		try {
			System.out.println(url);
			doc=Jsoup.connect(url).timeout(40000).validateTLSCertificates(false).get();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 추출 섹션
		
		
		
		
		
		//set 섹션
		vo.setUrl(url);
		vo.setTitle(parseTitle(doc));
		vo.setArticleId(999);
		//vo.setSiteIdOld(999);
		vo.setBody(parseBody(doc));
		vo.setCollectedBy("C");
		vo.setCrawlDate(nowTime());
		vo.setHitCount(parseHitCount(doc));
		vo.setCreateDate(parseDate(doc));
		vo.setCommentCount(parseCommentn(doc));
		vo.setLikeCount(0);
		vo.setWriterId("드미토리");
		vo.setWriterName("드미토리");
		vo.setRt("F");
		vo.setReplyId("0");
		vo.setRe("F");
		vo.setLat("0");
		vo.setLng("0");
		//vo.setSiteType("C");
		vo.setAddressStatus("N");
		vo.setMention("N");
		vo.setReputationType("E");
		//vo.setSiteSubType("드미토리");
		vo.setContentId(" ");
		//vo.setSiteId(" ");
		vo.setSiteCode(0000);
		vo.setRtCount(0);
		vo.setFollowerCount(0);
		//vo.setSiteName(findCate(doc));
		//vo.setSiteCategory("기타커뮤니티");
		vo.setLikeCount(0);
		//list.add(vo);
		return vo;
		
	}
	
	
	
	
	
	
	@Override
	public Document parse(String url) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	

}