package com.newIns.tools.wx;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;



public class AccessTokenTool {
	
	public static String getToken(String url,String cookie) throws Exception, IOException{
		String result = "";//返回信息
		
		//创建一个get请求
		HttpGet request = new HttpGet(url);
		
		//创建一个client客户端
		HttpClient httpClient = new DefaultHttpClient();
		
		//添加cookie到头文件
		request.addHeader("Cookie", cookie);
		
		//接受客户端发回的响应
		HttpResponse httpResponse = httpClient.execute(request);
		
		//获取返回状态
		int statusCode = httpResponse.getStatusLine().getStatusCode();
		if(statusCode==HttpStatus.SC_OK){
			//得到客户端响应的实体内容
			HttpEntity responseHttpEntity = httpResponse.getEntity();
			//得到输入流
			InputStream in = responseHttpEntity.getContent();
			//得到输入流的内容
			
		}
		
		return result;
	}
	
}
