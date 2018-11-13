package com.newIns.model.assess;


public class NiAssessDeliveryWanxVo extends AssessDelivery{
	
	private String aqnName; //问卷名称
	private String editor; //出题人
	private Integer qStatus;  //测评问卷状态
	
	public String getAqnName() {
		return aqnName;
	}
	public void setAqnName(String aqnName) {
		this.aqnName = aqnName;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public Integer getqStatus() {
		return qStatus;
	}
	public void setqStatus(Integer qStatus) {
		this.qStatus = qStatus;
	}
	
}
