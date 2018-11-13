package com.newIns.model.vote;


public class NiVoteDeliveryWanxVo extends VoteDelivery{
	private String vqnName; //问卷名称
	private String editor; //出题人
	private Integer vStatus;  //投票问卷状态
	public String getVqnName() {
		return vqnName;
	}
	public void setVqnName(String vqnName) {
		this.vqnName = vqnName;
	}
	public String getEditor() {
		return editor;
	}
	public void setEditor(String editor) {
		this.editor = editor;
	}
	public Integer getvStatus() {
		return vStatus;
	}
	public void setvStatus(Integer vStatus) {
		this.vStatus = vStatus;
	}
}
