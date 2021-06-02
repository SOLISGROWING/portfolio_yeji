package com.crawler.vo;

import java.sql.Clob;
import java.util.Date;

public class ArticleVO {
/*	private String stringDate;
*/	private String writerId;
	private String title;
	private String body;
	private Integer articleId;
	private Integer siteIdOld;
	private String crawlDate;
	private String rt;
	private String replyId;
	private String replyWriterId;
	private String re;
	private String address;
	private String lat;
	private String lng;
	private String createDate;
	private String siteType;
	private String viaUrl;
	private String addressStatus;
	private String mention;
	private Integer articleIdOld;
	private String url;
	private String reputationType;
	private String siteSubType;
	private String contentId;
	private String address2;
	private String siteId;
	private Integer siteCode;
	private String writerName;
	private String collectedBy;
	private Integer rtCount;
	private Integer followerCount;
	private String siteName;
	private String picture;
	private String screenName;
	private String siteCategory;
	private Integer hitCount;
	private Integer likeCount;
	private Integer commentCount;
	private String tableName;
	private String seqName;
	
	public String getWriterId() {
		return writerId;
	}
	public void setWriterId(String writerId) {
		this.writerId = writerId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public Integer getArticleId() {
		return articleId;
	}
	public void setArticleId(Integer articleId) {
		this.articleId = articleId;
	}
	public Integer getSiteIdOld() {
		return siteIdOld;
	}
	public void setSiteIdOld(Integer siteIdOld) {
		this.siteIdOld = siteIdOld;
	}
	public String getCrawlDate() {
		return crawlDate;
	}
	public void setCrawlDate(String crawlDate) {
		this.crawlDate = crawlDate;
	}
	public String getRt() {
		return rt;
	}
	public void setRt(String rt) {
		this.rt = rt;
	}
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	public String getReplyWriterId() {
		return replyWriterId;
	}
	public void setReplyWriterId(String replyWriterId) {
		this.replyWriterId = replyWriterId;
	}
	public String getRe() {
		return re;
	}
	public void setRe(String re) {
		this.re = re;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getSiteType() {
		return siteType;
	}
	public void setSiteType(String siteType) {
		this.siteType = siteType;
	}
	public String getViaUrl() {
		return viaUrl;
	}
	public void setViaUrl(String viaUrl) {
		this.viaUrl = viaUrl;
	}
	public String getAddressStatus() {
		return addressStatus;
	}
	public void setAddressStatus(String addressStatus) {
		this.addressStatus = addressStatus;
	}
	public String getMention() {
		return mention;
	}
	public void setMention(String mention) {
		this.mention = mention;
	}
	public Integer getArticleIdOld() {
		return articleIdOld;
	}
	public void setArticleIdOld(Integer articleIdOld) {
		this.articleIdOld = articleIdOld;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getReputationType() {
		return reputationType;
	}
	public void setReputationType(String reputationType) {
		this.reputationType = reputationType;
	}
	public String getSiteSubType() {
		return siteSubType;
	}
	public void setSiteSubType(String siteSubType) {
		this.siteSubType = siteSubType;
	}
	public String getContentId() {
		return contentId;
	}
	public void setContentId(String contentId) {
		this.contentId = contentId;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getSiteId() {
		return siteId;
	}
	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}
	public Integer getSiteCode() {
		return siteCode;
	}
	public void setSiteCode(Integer siteCode) {
		this.siteCode = siteCode;
	}
	public String getWriterName() {
		return writerName;
	}
	public void setWriterName(String writerName) {
		this.writerName = writerName;
	}
	public String getCollectedBy() {
		return collectedBy;
	}
	public void setCollectedBy(String collectedBy) {
		this.collectedBy = collectedBy;
	}
	public Integer getRtCount() {
		return rtCount;
	}
	public void setRtCount(Integer rtCount) {
		this.rtCount = rtCount;
	}
	public Integer getFollowerCount() {
		return followerCount;
	}
	public void setFollowerCount(Integer followerCount) {
		this.followerCount = followerCount;
	}
	public String getSiteName() {
		return siteName;
	}
	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String getSiteCategory() {
		return siteCategory;
	}
	public void setSiteCategory(String siteCategory) {
		this.siteCategory = siteCategory;
	}
	public Integer getHitCount() {
		return hitCount;
	}
	public void setHitCount(Integer hitCount) {
		this.hitCount = hitCount;
	}
	public Integer getLikeCount() {
		return likeCount;
	}
	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}
	public Integer getCommentCount() {
		return commentCount;
	}
	public void setCommentCount(Integer commentCount) {
		this.commentCount = commentCount;
	}
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getSeqName() {
		return seqName;
	}
	public void setSeqName(String seqName) {
		this.seqName = seqName;
	}
	@Override
	public String toString() {
		return "ArticleVO [writerId=" + writerId + ", title=" + title + ", body=" + body + ", articleId=" + articleId
				+ ", siteIdOld=" + siteIdOld + ", crawlDate=" + crawlDate + ", rt=" + rt + ", replyId=" + replyId
				+ ", replyWriterId=" + replyWriterId + ", re=" + re + ", address=" + address + ", lat=" + lat + ", lng="
				+ lng + ", createDate=" + createDate + ", siteType=" + siteType + ", viaUrl=" + viaUrl
				+ ", addressStatus=" + addressStatus + ", mention=" + mention + ", articleIdOld=" + articleIdOld
				+ ", url=" + url + ", reputationType=" + reputationType + ", siteSubType=" + siteSubType
				+ ", contentId=" + contentId + ", address2=" + address2 + ", siteId=" + siteId + ", siteCode="
				+ siteCode + ", writerName=" + writerName + ", collectedBy=" + collectedBy + ", rtCount=" + rtCount
				+ ", followerCount=" + followerCount + ", siteName=" + siteName + ", picture=" + picture
				+ ", screenName=" + screenName + ", siteCategory=" + siteCategory + ", hitCount=" + hitCount
				+ ", likeCount=" + likeCount + ", commentCount=" + commentCount + ", tableName=" + tableName
				+ ", seqName=" + seqName + "]";
	}
	
	
	
	
	
	
	


	
}
