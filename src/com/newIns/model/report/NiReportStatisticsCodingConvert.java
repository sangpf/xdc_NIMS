package com.newIns.model.report;
/**
 * @author lj
 * @Description : 报告统计的model类[将编码转换成对应汉字的转换model]
 * @time : 2016年8月10日 上午10:20:15
 */
public class NiReportStatisticsCodingConvert {
	//报告id
	private int reportId;
	//问卷名称
	private String qnName;
	//渠道
	private String channel;
	//状态
	private String reportStatus;
	//类型
	private String qnType;
	
	//阅读修正数
	private int correctNum;
	//分享次数[暂时不做]
	private String shareNum;
	//阅读次数[供导出表格使用]
	private int readNum;
	public int getReportId() {
		return reportId;
	}
	public void setReportId(int reportId) {
		this.reportId = reportId;
	}
	public String getQnName() {
		return qnName;
	}
	public void setQnName(String qnName) {
		this.qnName = qnName;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(String reportStatus) {
		this.reportStatus = reportStatus;
	}
	public String getQnType() {
		return qnType;
	}
	public void setQnType(String qnType) {
		this.qnType = qnType;
	}
	public int getCorrectNum() {
		return correctNum;
	}
	public void setCorrectNum(int correctNum) {
		this.correctNum = correctNum;
	}
	public String getShareNum() {
		return shareNum;
	}
	public void setShareNum(String shareNum) {
		this.shareNum = shareNum;
	}
	public int getReadNum() {
		return readNum;
	}
	public void setReadNum(int readNum) {
		this.readNum = readNum;
	}
	@Override
	public String toString() {
		return "NiReportStatisticsCodingConvert [reportId=" + reportId
				+ ", qnName=" + qnName + ", channel=" + channel
				+ ", reportStatus=" + reportStatus + ", qnType=" + qnType
				+ ", correctNum=" + correctNum + ", shareNum=" + shareNum
				+ ", readNum=" + readNum + "]";
	}
	
	
	
}
