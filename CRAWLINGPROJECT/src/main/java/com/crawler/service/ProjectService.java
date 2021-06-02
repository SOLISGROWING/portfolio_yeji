package com.crawler.service;

import java.util.List;

import com.crawler.vo.CrawlSiteVO;
import com.crawler.vo.ArticleVO;

public interface ProjectService {
	List<ArticleVO> selectProjectList() throws Exception;
	public int insertTitle(List<ArticleVO> list);
	List<CrawlSiteVO> selectForCrawl() throws Exception;
	

	
}