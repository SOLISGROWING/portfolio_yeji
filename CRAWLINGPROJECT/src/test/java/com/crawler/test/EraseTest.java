package com.crawler.test;


import com.crawler.Crawler2;
import com.crawler.service.dao.ProjectDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/application-context.xml")
@TestExecutionListeners(inheritListeners = false, listeners = { DependencyInjectionTestExecutionListener.class,
		DirtiesContextTestExecutionListener.class })
public class EraseTest {
	@Autowired
	ProjectDAO dao;
	// 78 83
	// 15 23
	@Test
	public void test() {
		
		Crawler2 crawler = new Crawler2();
		crawler.start();
		crawler.run();
		
		
		
	}

}
