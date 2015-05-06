package com.codenote.utils.httpclient;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HttpClient的帮助类，主要方法方法有get,post等
 *   调用完get,post后，需要调用close()方法关闭流
 *   非线程安全
 * 后续待加入ssl安全登录相关内容
 * @author lizeyu
 *
 */
public class HttpClientHelper
{
	private CloseableHttpClient httpclient = null;
	private HttpGet httpget = null;
	private HttpPost httppost = null;
	private CloseableHttpResponse response = null;
	
	/**
	 * 发送get请求，如果returnCode不是200，则返回null，并在控制台打印code
	 * @param url		url地址
	 * @return
	 */
	public HttpEntity get(String url)
	{
		try
		{
			httpclient = HttpClients.createDefault();
			// 创建httpget
			httpget = new HttpGet(url);
			// 执行get请求
			response = httpclient.execute(httpget);
			if (response.getStatusLine().getStatusCode() != 200)
			{
				System.out.println("status is not ok: "
						+ response.getStatusLine().getStatusCode());
				return null;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return response.getEntity();
	}
	
	/**
	 * 发送post请求，如果returnCode不是200，则返回null，并在控制台打印code
	 * 	注意方法调用结束后没有关闭流，因为后面可能会需要根据返回的entity进行打印、输出到文件等操作
	 * @param url		 url地址
	 * @param paramsMap  请求中夹带的参数，这里只考虑参数类型都是string型的
	 * @return 返回从response中获得的HttpEntity
	 */
	public HttpEntity post(String url, Map<String, String> paramsMap)
	{
		// 创建默认的httpClient实例.
		httpclient = HttpClients.createDefault();
		// 创建httppost
		httppost = new HttpPost(
				"http://localhost:8080/testHttpClient/MyJsp.jsp");
		// 创建参数队列
		List<NameValuePair> formparams = new ArrayList<NameValuePair>();
		
		for(Entry<String, String> entry : paramsMap.entrySet())
		{
			formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
		}
		
		UrlEncodedFormEntity uefEntity;
		try
		{
			uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
			httppost.setEntity(uefEntity);
			response = httpclient.execute(httppost);
			if (response.getStatusLine().getStatusCode() != 200)
			{
				System.out.println("status is not ok: "
						+ response.getStatusLine().getStatusCode());
				return null;
			}
			String setCookie = response.getFirstHeader("Set-Cookie").getValue();
			System.out.println(setCookie);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return response.getEntity();
	}

	public void printEntity(HttpEntity entity)
	{
		// 打印响应内容
		try
		{
			System.out.println("Response content: "
					+ EntityUtils.toString(entity));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}

	public void close()
	{
		// 关闭连接,释放资源
		try
		{
			if(httpclient != null)
			{
				httpclient.close();
			}
			if(response != null)
			{
				response.close();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}
