package com.newIns.model.survery;


public class NiSurveyDeliveryWanxVO extends SurveyDelivery{
	
    private String aqnName; //问卷名称
    private String editor; //出题人
    private Integer qstatus; //问卷状态
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
	public Integer getQstatus() {
		return qstatus;
	}
	public void setQstatus(Integer qstatus) {
		this.qstatus = qstatus;
	}
    
    
}
