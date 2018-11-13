package weibo4j;

import weibo4j.http.AccessToken;
import weibo4j.http.Response;
import weibo4j.model.PostParameter;
import weibo4j.model.WeiboException;
import weibo4j.org.json.JSONException;
import weibo4j.org.json.JSONObject;
import weibo4j.util.WeiboConfig;

public class Oauth extends Weibo {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7003420545330439247L;
	// ----------------------------针对站内应用处理SignedRequest获取accesstoken----------------------------------------
	public String user_id;

	public String getToken() {
		return access_token;
	}


	/*
	 * 处理解析后的json解析
	 */
	public String ts(String json) {
		try {
			JSONObject jsonObject = new JSONObject(json);
			access_token = jsonObject.getString("oauth_token");
			user_id = jsonObject.getString("user_id");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return access_token;

	}

	/*----------------------------Oauth接口--------------------------------------*/

	public AccessToken getAccessTokenByCode(String code) throws WeiboException {

		Response post = client.post(
				WeiboConfig.getValue("accessTokenURL"),
				new PostParameter[] {
						new PostParameter("client_id", WeiboConfig
								.getValue("client_ID")),
						new PostParameter("client_secret", WeiboConfig
								.getValue("client_SERCRET")),
						new PostParameter("grant_type", "authorization_code"),
						new PostParameter("code", code),
						new PostParameter("redirect_uri", WeiboConfig
								.getValue("redirect_URI")) }, 
				false, null);
		
		return new AccessToken(post);
	}

	//拼接请求路径 请求授权地址
	public String authorize(String response_type) throws WeiboException {
		return WeiboConfig.getValue("authorizeURL").trim() + "?client_id="
				+ WeiboConfig.getValue("client_ID").trim() + "&redirect_uri="
				+ WeiboConfig.getValue("redirect_URI").trim()
				+ "&response_type=" + response_type;
	}

	public String authorize(String response_type, String state)
			throws WeiboException {
		return WeiboConfig.getValue("authorizeURL").trim() + "?client_id="
				+ WeiboConfig.getValue("client_ID").trim() + "&redirect_uri="
				+ WeiboConfig.getValue("redirect_URI").trim()
				+ "&response_type=" + response_type + "&state=" + state;
	}

	public String authorize(String response_type, String state, String scope)
			throws WeiboException {
		return WeiboConfig.getValue("authorizeURL").trim() + "?client_id="
				+ WeiboConfig.getValue("client_ID").trim() + "&redirect_uri="
				+ WeiboConfig.getValue("redirect_URI").trim()
				+ "&response_type=" + response_type + "&state=" + state
				+ "&scope=" + scope;
	}
}
