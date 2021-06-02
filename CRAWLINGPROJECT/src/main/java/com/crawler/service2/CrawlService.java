package com.crawler.service2;

import java.util.List;

import com.crawler.vo.ArticleVO;

public interface CrawlService {
	List<ArticleVO> selectProjectList() throws Exception;
	public int insertTitle(List<ArticleVO> list);
	

	
}