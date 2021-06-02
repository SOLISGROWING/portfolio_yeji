
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
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

import com.crawler.parser.parser;
import com.crawler.vo.ArticleVO;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/application-context.xml")
@TestExecutionListeners(inheritListeners = false, listeners = { DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class })
public class RuliwebParser extends parser {
	
	private String findCate(Document doc) {
		String cate = doc.select("input[name=board_name]").attr("value").toString();
		return "루리웹"+"-"+cate;
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
	
	@Override
	public List<String> extractArticleUrls(String url) throws IOException {
		Document doc=parse(url);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<String> tempUrl=new ArrayList<String>();
		Elements els = doc.select("table.board_list_table > tbody > tr.table_body");
		for (Element link : els) {
			//System.out.println(link);
			String category=link.select("td.subject>div.title>a.subject_link").text();
			//String category = link.select("td.divsn>a").text();
			//System.out.println(link.select("cate_label").text());
			if(category.contains("공지")) {
				continue;
			}
			else if(category.contains("BEST")) {
				continue;
			}
			else if(category.contains("모집")) {
				continue;
			}
			else if (category.isEmpty()==true) {
				continue;
			}

			String absurl = link.select("td.subject>div.title>a.subject_link").attr("href");
			if(absurl.isEmpty() || absurl.length()<4) {
				continue;
			}
			
			String urlcut= absurl.substring(absurl.indexOf("r"));
			String absUrl="https://bbs."+urlcut;
			tempUrl.add(absUrl);
		}
		
	
		return tempUrl;
	}
	
	@Override
	public Document parse(String url) throws IOException {
		// TODO Auto-generated method stub
		return Jsoup.connect(url).timeout(40000).validateTLSCertificates(false).get();
	}

	// 본문을 긁는것 
		@Override
		public   ArticleVO crawlArticle(String url) throws IOException {
;			ArticleVO vo = new ArticleVO();
			int flag=1;
			List<ArticleVO> list = new ArrayList<ArticleVO>();
			Document doc=parse(url);
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 추출 섹션
			String Regdate=null;
			String regdate = doc.select("span.regdate").text();
			
			String body = doc.select("div.view_content").text().replaceAll(" ","");
			String writer= doc.select("a.nick").text();
			String likeCount=doc.select("span.like_value").text();
			char[] arr=body.toCharArray();
			int count=0;
			for(int i=0; i<body.length(); i++) {
				char oneletter = arr[i];
				count++;
				if(Character.getType(oneletter)!=12 && Character.getType(oneletter)!=24) {
					break;
				}
				if(count==body.length()) {
					flag--;
					System.out.println("들어가기방지");
				}
				
			}
			/*String realbody=doc.select("meta[name=description]").attr("content").toString();
			System.out.println(realbody);
			System.out.println(realbody.length());
			// 널값처리 
			String writer= doc.select("a.nick").text();
			int count=0;
			char[] arr=realbody.toCharArray();
			for(int i=0; i<realbody.length(); i++) {
				char oneletter = arr[i];
				if(Character.getType(oneletter)==12) {
					System.out.println(count);
					count++;
				}
			}*/
			/*String Body=null;     
			if(body.length()<5) {
				
				char[] arr=body.toCharArray();
				for(int i=0; i<body.length(); i++) {
					char oneletter = arr[i];
					if(Character.getType(oneletter)!=12) {
						break;
						//Body+=arr[i];
						//System.out.println(Body);
					}
				}
			}
			else {
				Body=body;
			}
			*/
			
			/*if(body.equals("  ")) {
				System.out.println("hi");
				System.out.println("hello");
			}
			if(body.contains(" ")) {
				System.out.println("erasetest");
			}
			if(body.contains(" ")) {
				System.out.println("realerase");
			}*/
			//String Body= body.replaceAll("\u00a0", "");
			System.out.println(body.replaceAll(" ",""));
			System.out.println(body.replaceAll(" ",""));
			String articleBody=body.replaceAll(" ","");
			//String articleBody= body.replaceAll(" ","").replaceAll(" ","");
			
			//날짜처리
			//String recomd=doc.select("div>span.like").text();
			String title= doc.select("span.subject_inner_text").text();
			//String hit = doc.select("a.nick").text();
			String cutpoint=doc.select("div.user_info").text();
			
			int recommend= Integer.parseInt(cutpoint.substring(cutpoint.lastIndexOf("추천")+2,cutpoint.indexOf("|")).replaceAll(" ", ""));
			int hit= Integer.parseInt(cutpoint.substring(cutpoint.indexOf("조회")+2,cutpoint.indexOf("일시")).replaceAll(" ", ""));
			
			/*if(title.equals(realbody)) {
				flag--;
			}*/
			
			String commentNum = doc.select("span.num_txt>strong.reply_count:eq(0)").first().text();
			String original= null;
			original= regdate;
			String cut1= original.substring(0,2);
			String cut2 = original.substring(3,5);
			String cut3 = original.substring(6,8);
			String cut4 = original.substring(10,12);
			String cut5 = original.substring(13,15);
			String cut6 = original.substring(16,18);
			String createdate= "20"+cut1+cut2+cut3+cut4+cut5+cut6;
			
			
			
			
			//set 섹션
			vo.setArticleId(999);
			//vo.setSiteIdOld(999);
			vo.setWriterId(writer);
			vo.setCrawlDate(nowTime());
			vo.setTitle(title);
			vo.setBody(body);
			vo.setRt("F");
			vo.setReplyId("0");
			vo.setRe("F");
			vo.setLat("0");
			vo.setLng("0");
			vo.setCreateDate(createdate);
			//vo.setSiteType("C");
			vo.setAddressStatus("N");
			vo.setMention("N");
			vo.setUrl(url);
			vo.setReputationType("E");
			//vo.setSiteSubType("루리웹");
			vo.setContentId(" ");
			//vo.setSiteId(" ");
			vo.setSiteCode(0000);
			vo.setWriterName(writer);
			vo.setCollectedBy("C");
			vo.setRtCount(0);
			vo.setFollowerCount(0);
			//vo.setSiteName(findCate(doc));
			//vo.setSiteCategory("기타커뮤니티");
			vo.setHitCount(hit);
			vo.setCommentCount(Integer.parseInt(commentNum));
			vo.setLikeCount(Integer.parseInt(likeCount));
			if(flag==1) {
				return vo;
			}
			return null;
			
		}
		
	
	
	
	

}
