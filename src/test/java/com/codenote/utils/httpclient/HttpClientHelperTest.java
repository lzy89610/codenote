package com.codenote.utils.httpclient;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.junit.Test;

public class HttpClientHelperTest
{
	@Test
	public void testGet()
	{
		String url = "http://www.baidu.com";

		HttpClientHelper clientHelper = new HttpClientHelper();
		HttpEntity entity = clientHelper.get(url);
		clientHelper.printEntity(entity);
		clientHelper.close();
	}
	
	@Test
	public void testPost()
	{
		String url = "http://localhost:8080/testHttpClient/MyJsp.jsp";
		Map<String, String> paramsMap = new HashMap<String, String>();
		paramsMap.put("userName", "lizeyu");
		
		HttpClientHelper clientHelper = new HttpClientHelper();
		HttpEntity entity = clientHelper.post(url, paramsMap);
		clientHelper.printEntity(entity);
		clientHelper.close();
	}
	
	

}
