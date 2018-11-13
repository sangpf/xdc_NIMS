package com.newIns.model.assess;

import java.io.Serializable;

/**
 * 
 * @author Sang
 *
 */
public class NiAssessQuestionnaireVO extends NiAssessQuestionnaire implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String aqnClassName;

	public String getAqnClassName() {
		return aqnClassName;
	}

	public void setAqnClassName(String aqnClassName) {
		this.aqnClassName = aqnClassName;
	}
	
}
