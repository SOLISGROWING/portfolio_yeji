package com.crawler;



public enum CommunityType {
	RULIWEB("RULIWEB","http://bbs.ruliweb.com"),
	DOMITORY("DOMITORY","https://www.dmitory.com");
	
	
	
	
	private String value;
	private String url;
	
	private CommunityType(String value, String url) {
		this.value = value;
		this.url = url;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
	public String getValue() {
		if (value == null) {
			return "";
		}
		return value;
	}
	
	public String getUrl() {
		if (url == null) {
			return "";
		}
		return url;
	}
	
	/**
	 * site url에 해당하는 매스미디어타입을 리턴한다.
	 * @param url
	 * @return
	 */
	public static CommunityType communityTypeByUrl(String url) {
		for (CommunityType val : values()) {
			if (url.contains(val.url)) {
				return val;
			}
		}
		throw new IllegalArgumentException("unknown massmedia url:" + url);
	}
}