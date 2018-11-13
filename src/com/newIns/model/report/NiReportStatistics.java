package com.newIns.model.report;
/**
 * @author lj
 * @Description : 报告统计的model类
 * @time : 2016年8月10日 上午10:20:15
 */
public class NiReportStatistics {
	//报告id
	private int reportId;
	//问卷名称
	private String qnName;
	//渠道
	private int channel;
	//状态
	private int reportStatus;
	//类型
	private int qnType;
	//玩校阅读次数
	private int wanxViewNum;
	//微信阅读次数
	private int weixViewNum;
	//APP阅读次数
	private int appViewNum;
	//阅读基准数
	private int baseViewNum;
	//阅读修正数
	private int correctNum;
	//分享次数[暂时不做]
	private int shareNum;
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
	public int getChannel() {
		return channel;
	}
	public void setChannel(int channel) {
		this.channel = channel;
	}
	public int getReportStatus() {
		return reportStatus;
	}
	public void setReportStatus(int reportStatus) {
		this.reportStatus = reportStatus;
	}
	public int getQnType() {
		return qnType;
	}
	public void setQnType(int qnType) {
		this.qnType = qnType;
	}

	public int getCorrectNum() {
		return correctNum;
	}
	public void setCorrectNum(int correctNum) {
		this.correctNum = correctNum;
	}
	public int getBaseViewNum() {
		return baseViewNum;
	}
	public void setBaseViewNum(int baseViewNum) {
		this.baseViewNum = baseViewNum;
	}
	public int getWanxViewNum() {
		return wanxViewNum;
	}
	public void setWanxViewNum(int wanxViewNum) {
		this.wanxViewNum = wanxViewNum;
	}
	public int getWeixViewNum() {
		return weixViewNum;
	}
	public void setWeixViewNum(int weixViewNum) {
		this.weixViewNum = weixViewNum;
	}
	public int getAppViewNum() {
		return appViewNum;
	}
	public void setAppViewNum(int appViewNum) {
		this.appViewNum = appViewNum;
	}
	public int getShareNum() {
		return shareNum;
	}
	public void setShareNum(int shareNum) {
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
		return "NiReportStatistics [reportId=" + reportId + ", qnName="
				+ qnName + ", channel=" + channel + ", reportStatus="
				+ reportStatus + ", qnType=" + qnType + ", wanxViewNum="
				+ wanxViewNum + ", weixViewNum=" + weixViewNum
				+ ", appViewNum=" + appViewNum + ", baseViewNum=" + baseViewNum
				+ ", correctNum=" + correctNum + ", shareNum=" + shareNum
				+ ", readNum=" + readNum + "]";
	}
	
	
	
	
}
