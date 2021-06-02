package com.crawler;

import com.crawler.parser.DomitoryParser;
import com.crawler.parser.NaeilParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import com.crawler.parser.ParserFactory;
import com.crawler.parser.RuliwebParser;
import com.crawler.parser.parser;
import com.crawler.service.dao.ProjectDAO;
import com.crawler.vo.CrawlSiteVO;
import com.crawler.vo.ArticleVO;

public class Crawler2 {
	private ProjectDAO dao;
	private ApplicationContext context;

	public void run() {
		CrawlSiteVO vo = new CrawlSiteVO();
		//vo.setSiteType("C");
		parser parser = null;
		List<CrawlSiteVO> extractUrlFromDb;
		List<String> articleUrls = null;
		try {
			extractUrlFromDb = dao.selectForCrawl(vo);
			for (CrawlSiteVO x : extractUrlFromDb) {
				String startVisitUrl = x.getUrl();
				String siteId = x.getSiteId(); //222245
				String siteSubCate=x.getSiteSubCate(); //드미토리
				String siteCategory=x.getSiteCategory(); //기타커뮤니티
				String siteType  =x.getSiteType(); //C
				String siteName=x.getSiteName();//드미토리 방탄소년단 
				String siteIdOld=x.getSiteIdOld(); //222245
				parser = findParser(startVisitUrl);
				if (parser == null) {
					continue;
				}
				System.out.println();
				articleUrls = parser.extractArticleUrls(startVisitUrl);
				for (String articleUrl : articleUrls) {
					/*if (existUrl(articleUrl)) {
						System.out.println(articleUrl);
						continue;
					}*/
					//System.out.println("없는것");
					ArticleVO vo7 =parser.crawlArticle(articleUrl);
					if (vo7==null) {
						continue;
					}
					vo7.setSiteType(siteType);
					vo7.setSiteId(siteId);
					vo7.setSiteName(siteName);
					vo7.setSiteIdOld(Integer.parseInt(siteIdOld));
					vo7.setSiteCategory(siteCategory);
					vo7.setSiteSubType(siteSubCate);
					String contentDate=vo7.getCreateDate();
					String seq=findNameTS(contentDate).get(0);
					String table=findNameTS(contentDate).get(1);
					vo7.setTableName(table);
					vo7.setSeqName(seq);
					
						try {
							String lastCheckUrl = vo7.getUrl();
							if (existUrl(lastCheckUrl,table)) {
								continue;
							}
							if (vo7.getBody().isEmpty() ||vo7.getBody()==null) {
								continue;
							}
							int insertRowNum = dao.insertPractical(vo7); 
							System.out.println(insertRowNum);
						} catch (Exception e) {
							e.printStackTrace();
						}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void start() {
		try {
			init();
			run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void start2() {
		try {
			init();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private void init() {
		context = new GenericXmlApplicationContext("classpath:spring/application-context.xml");
		dao = context.getBean(ProjectDAO.class);
	}

	@SuppressWarnings("null")
	public boolean existUrl(String url,String tableName) { // visitUrl
		ArticleVO vo2 = new ArticleVO();
		vo2.setTableName(tableName);
		vo2.setUrl(url);
		boolean existUrl = dao.selectforPK(vo2);
		if (existUrl) {
			System.out.println("있는url");
			return true;
		}
		return false;
	}

	public parser findParser(String url) { // https://www.dmitory.com/bts // ru
		if (url.contains("dmitory")) {
			return new DomitoryParser();
		} else if (url.contains("ruliweb")) {
			return new RuliwebParser();

		}else if (url.contains("naeil")){
			return new NaeilParser();
		}
		System.out.println("디미토리나루리웹이아님");
		return null;
	}
	
	/*public String findTableName(ArticleVO vo,String siteType) {
		String table="";
		if(siteType.contains("C")) {
			table+="TB_ARTICLE_SEARCH_COMM";
		}
		String date=vo.getCreateDate();
		String subdate=date.substring(2,6);
		return table+"_"+subdate;
		
	}*/
	
	public HashMap<Integer, String> findNameTS(String date) {
		HashMap<Integer,String> map = new HashMap<>();
		String seqn="SEQ_COMM";
		String table="TB_ARTICLE_SEARCH_COMM";
		String subdate=date.substring(2,6);
		System.out.println(seqn+"_"+subdate);
		map.put(0, seqn+"_"+subdate);
		map.put(1,table+"_"+subdate);
		return map;
		
	}
	
	
	
	
	
	
}
