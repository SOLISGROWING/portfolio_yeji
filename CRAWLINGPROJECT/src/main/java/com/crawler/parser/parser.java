package com.crawler.parser;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.crawler.vo.ArticleVO;


public abstract class parser {
	/**
	 * 게시물 url을 추출한다.
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public abstract List<String> extractArticleUrls(String url)
			throws IOException;
	
	public abstract ArticleVO crawlArticle(String url) throws IOException;

	public abstract Document parse(String url) throws IOException;
	
	/**
	 * 게시물을 파싱하여 결과클래스에 담는다.
	 * @param url 
	 * @return
	 * @throws IOException
	 */
/*	public abstract SelectQuery crawlArticle(String url) throws IOException;
*/	
	/**
	 * 파서의 종료작업이 필요할때 상속받은 클래스에서 재정의한다
	 */
	public void close() {
		
	}
	
	/**
	 * jsoup document에서 쿼리식에 의해 검색된 엘리멘트의 자식태그들을 제거한다.
	 * @param doc
	 * @param cssQuery
	 */
	protected Document removeElement(Document doc, String cssQuery) {
		Element el = doc.select(cssQuery).first();
		if (el != null)
			el.remove();
		
		return doc;
	}
	
	
	/**
	 * jsoup document에서 쿼리식에 의해 검색된 엘리멘트의 자식태그들을 제거한다.
	 * @param doc
	 * @param cssQuery
	 */
	protected Document removeElements(Document doc, String cssQuery) {
		Elements el = doc.select(cssQuery);
		if(el.isEmpty() == false) {
			el.remove();
		}
		
		return doc;
	}
	
	/**
	 * 방문가능한 게시물목록url을 리턴한다.
	 * 필요할 경우 하위클래스에서 재정의한다.
	 * @param url
	 * @return
	 */
	public String makeVisitableUrl(String url) {
		return url;
	}
	
	protected String getCurrentDate() {

		String today = null;

		Date now = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		today = simpleDateFormat.format(now);

		return today;
	}
	
	/**
	 * 대상 문자열에서 패턴을 찾아 패턴뒤에 나오는 문자열을 제거한다.
	 * @param body
	 * @param pattern
	 * @return
	 */
	protected String leftString(String body, String pattern) {
		int substringEndIndex = body.indexOf(pattern);
		if (substringEndIndex > 0) {
			body = body.substring(0, substringEndIndex);
		}
		return body;
	}
	
	
}
