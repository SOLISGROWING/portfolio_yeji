package com.crawler.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.crawler.Crawler;
public class CrawlerTest {
	
	@Test
	public void test() {
		Crawler crawler = new Crawler();
		crawler.start();
	}
}
