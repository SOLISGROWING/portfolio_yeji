package com.crawler.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crawler.service.ProjectService;
import com.crawler.service.dao.ProjectDAO;
import com.crawler.vo.CrawlSiteVO;
import com.crawler.vo.ArticleVO;
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
	
	@Autowired
	private ProjectDAO projectMapper;
	
	@Override
	@Transactional
	public List<ArticleVO> selectProjectList() throws Exception {
		return projectMapper.selectProjectList();
	}
	
	
	
	@Override
	public int insertTitle(List<ArticleVO> list) {
		return 0;
	}

	@Override
	public List<CrawlSiteVO> selectForCrawl() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


	
	
}
