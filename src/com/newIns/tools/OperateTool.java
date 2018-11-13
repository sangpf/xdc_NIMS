package com.newIns.tools;

import java.util.HashMap;

public class OperateTool {
	
	private static HashMap<String, Object> operateMap;
	
	private OperateTool(){}

	public static synchronized HashMap<String, Object> getOperateMap() {
		
		if(operateMap == null){
			operateMap = new HashMap<String, Object>();
		}
		
		return operateMap;
	};
	
}
