package com.crawler;

import com.crawler.parser.DomitoryParser;
import com.crawler.parser.NaeilParser;

import java.util.ArrayList;
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

public class Crawler {
	private ProjectDAO dao;
	private ApplicationContext context;

	public void run() {
		CrawlSiteVO vo = new CrawlSiteVO();
		//vo.setSiteType("C");
		parser parser = null;
		List<CrawlSiteVO> extractUrlFromDb;
		List<String> articleUrls = null;
		List<String> tempUrlsForNaeil = new ArrayList<>();
		tempUrlsForNaeil.add("http://www.naeil.com/news_list/?cate=01002000");
		tempUrlsForNaeil.add("http://www.naeil.com/news_list/?cate=01008000");
		tempUrlsForNaeil.add("http://www.naeil.com/news_list/?cate=01003000");
		tempUrlsForNaeil.add("http://www.naeil.com/news_list/?cate=01004000");
		tempUrlsForNaeil.add("http://www.naeil.com/news_list/?cate=01005000");
		tempUrlsForNaeil.add("http://www.naeil.com/news_list/?cate=01006000");

		System.out.println(dao);
		try {
			/*extractUrlFromDb = dao.selectForCrawl(vo);
			for (CrawlSiteVO x : extractUrlFromDb) {*/
			for (String tempurl: tempUrlsForNaeil) {
				// method url 
//			}
				//System.out.println(x.getUrl());
				//String startVisitUrl = x.getUrl();
				String startVisitUrl=tempurl;
				parser = findParser(startVisitUrl);
				if (parser == null) {
					continue;
				}
//				String visitableUrl = parser.makeVisitableUrl(x.getUrl());
				articleUrls = parser.extractArticleUrls(startVisitUrl);
				List<ArticleVO> po = null;
				for (String articleUrl : articleUrls) {
					/*if (existUrl(articleUrl)) {
						System.out.println(articleUrl);
						continue;
					}*/
					System.out.println("없는것");
					//po = parser.crawlArticle(articleUrl);
					ArticleVO vo7 =parser.crawlArticle(articleUrl);
					vo7.setTableName(findTableName(vo7));
					vo7.setSeqName(findSeqName(vo7));
					if (vo7==null) {
						continue;
					}
						try {
							String lastCheckUrl = vo7.getUrl();
							if (existUrl(lastCheckUrl,findTableName(vo7))) {
								continue;
							}
							if (vo7.getBody().isEmpty()|| vo7.getBody()==null) {
								continue;
							}
							int insertRowNum = dao.insertPractical(vo7);
							System.out.println(insertRowNum);
						} catch (Exception e) {
							e.printStackTrace();
						}
					
					// return vo
					
					//ArticleVO vo7 = parser.crawlArticle(articleUrl);
					//dao.insertAll(vo7);
					
					/*if (po.isEmpty()) {
						continue;
					}*/
					/*for (ArticleVO vvo : po) { // 50 번 loop
						try {
							String lastCheckUrl = vvo.getUrl();
							if (existUrl(lastCheckUrl)) {
								continue;
							}
							if (vvo.getBody().isEmpty()) {
								continue;
							}
							int insertRowNum = dao.insertAll(vvo);
							System.out.println(insertRowNum);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}*/
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
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
	
	public String findTableName(ArticleVO vo) {
		String siteType=vo.getSiteType();
		String table = new String();
		if(siteType.contains("C")) {
			table+="TB_ARTICLE_SEARCH_COMM";
			
		}
		else if (siteType=="M") {
			table+="TB_ARTICLE_SEARCH_MEDIA";
			
		}
		String date=vo.getCreateDate();
		String subdate=date.substring(2,6);
		System.out.println(table+"_"+subdate);
		return table+"_"+subdate;
		
	}
	
	public String findSeqName(ArticleVO vo) {
		String siteType=vo.getSiteType();
		String seq=new String();
		if(siteType.contains("C")) {
			seq+="SEQ_COMM";
		}
		else if (siteType.contains("M")) {
			seq+="SEQ_MEDIA";
		}
		String date=vo.getCreateDate();
		String subdate=date.substring(2,6);
		System.out.println(seq+"_"+subdate);
		System.out.println("----");
		return seq+"_"+subdate;
		
	}
	
	
	
	
	
}
