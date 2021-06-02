package com.crawler.parser;
import com.crawler.CommunityType;
import com.crawler.parser.DomitoryParser;
import com.crawler.parser.parser;
import com.crawler.parser.RuliwebParser;
public class ParserFactory {
	public static parser createParser(String url) { //https://www.dmitory.com/bts
		return createParser(findMassmediaType(url));
	}

	private static CommunityType findMassmediaType(String url) {
		return CommunityType.communityTypeByUrl(url);
	}

	private static parser createParser(CommunityType type) {
		switch (type) {
		case DOMITORY:
			return new DomitoryParser();
		case RULIWEB:
			return new RuliwebParser();
	
		default:
			return null;
		}
	}
}