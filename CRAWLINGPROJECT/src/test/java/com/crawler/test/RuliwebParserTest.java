package com.crawler.test;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import com.crawler.parser.RuliwebParser;
import com.crawler.service.dao.ProjectDAO;
import com.crawler.vo.ArticleVO;

public class RuliwebParserTest {
	private ProjectDAO dao;
	@Test
	public void test() {
		
	}
	@Test
	public void extractArticleUrls() {
		RuliwebParser parser=new RuliwebParser();
		String url="https://bbs.ruliweb.com/market/board/300141/read/30615402?";
		try {
			System.out.println(parser.crawlArticle(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
