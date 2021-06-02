package com.crawler.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import com.crawler.parser.NaeilParser;
import com.crawler.parser.RuliwebParser;

import clojure.lang.IFn.D;

public class NaeilTest {
	
	
	@Test
	public void crawlArticle() {
		NaeilParser parser= new NaeilParser();
		String url="http://www.naeil.com/news_view/?id_art=382627";
		try {
			System.out.println(parser.crawlArticle(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	@Test
	public void extractArticleUrls() {
		NaeilParser parser= new NaeilParser();
		String url="http://www.naeil.com/news_list/?cate=01002000";
		try {
			System.out.println(parser.extractArticleUrls(url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	}
