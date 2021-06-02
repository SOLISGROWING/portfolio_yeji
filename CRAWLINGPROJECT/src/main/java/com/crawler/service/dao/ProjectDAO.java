package com.crawler.service.dao;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.crawler.vo.CrawlSiteVO;
import com.crawler.vo.OnePageVO;
import com.crawler.vo.ArticleVO;

public interface ProjectDAO {

	List<ArticleVO> selectProjectList();
 	List<ArticleVO>  selectAnonymous(ArticleVO vo) throws Exception;
	public int insertAll(ArticleVO vo);
	List<CrawlSiteVO> selectForCrawl(CrawlSiteVO vo) throws Exception;
	boolean selectforPK(ArticleVO vo2);
	public boolean isExist(ArticleVO vo);
	ArticleVO selectUrlForCrawl();
	String selectUrlForCrawl(CrawlSiteVO vo);
	String selectOnePageUrl(OnePageVO vo);
	int selecttwotable(@Param("tableName") String tableName);
	public int insertTest(@Param("tableName") String tableName ,ArticleVO vo);
	public int insertPractical(ArticleVO vo);
}
