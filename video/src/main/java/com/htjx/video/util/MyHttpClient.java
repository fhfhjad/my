package com.htjx.video.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * HttpClient工具类
 * @ClassName: MyHttpClient
 * @author sunlulu
 * @date Oct 23, 2015 1:23:33 PM
 * @version v1.0
 */
@SuppressWarnings("rawtypes")
public class MyHttpClient {

	private static CloseableHttpClient client = HttpClients.createDefault();

	private static RequestConfig requestConfig = RequestConfig.custom()
			.setConnectionRequestTimeout(120000).setConnectTimeout(120000)
			.setSocketTimeout(120000).build();

	public static String getMethod(String url) {
		HttpGet get = null;
		CloseableHttpResponse response = null;
		InputStream in = null;
		try {
			get = new HttpGet(url);
			get.setConfig(requestConfig);
			String result = "";
			response = client.execute(get);
			
			if(response.getStatusLine().getStatusCode() == 500){
				return result;
			}
			
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				long len = entity.getContentLength();
				if (len != -1 && len < 2048) {
					result = EntityUtils.toString(entity);
				} else {
					entity = new BufferedHttpEntity(entity);
					in = entity.getContent();
					byte[] read = new byte[1024];
					byte[] all = new byte[0];
					int num;
					while ((num = in.read(read)) > 0) {
						byte[] temp = new byte[all.length + num];
						System.arraycopy(all, 0, temp, 0, all.length);
						System.arraycopy(read, 0, temp, all.length, num);
						all = temp;
					}
					result = new String(all, "UTF-8");
				}
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				response.close();
				get.releaseConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String getMethod(String url, Header header) {
		HttpGet httpGet = null;
		CloseableHttpResponse response = null;

		try {
			httpGet = new HttpGet(url);
			httpGet.setHeader(header);
			response = client.execute(httpGet);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String postMethod(String url, Map parameters)
			throws Exception {
		HttpPost post = null;
		CloseableHttpResponse response = null;
		try {
			post = new HttpPost(url);
			//post.setHeader(HttpHeaders.CONTENT_TYPE, "multipart/form-data");
			if (parameters != null) {
				List<NameValuePair> nvps = new ArrayList<NameValuePair>();
				Iterator it = parameters.entrySet().iterator();
				while (it.hasNext()) {
					Map.Entry entry = (Map.Entry) it.next();
					String key = (String) entry.getKey();
					String value = (String) entry.getValue();
					nvps.add(new BasicNameValuePair(key, value));
				}
				post.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
			}
			response = client.execute(post);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
				post.releaseConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String postMethod(String url, String bodyJson,
			String accessKeyId, String sign) throws Exception {
		HttpPost post = null;
		CloseableHttpResponse response = null;
		try {
			post = new HttpPost(url);
			if (accessKeyId != null && !"".equals(accessKeyId)) {
				post.setHeader("accessKeyId", accessKeyId);
			}
			if (sign != null && !"".equals(sign)) {
				post.setHeader("sign", sign);
			}
			StringEntity se = new StringEntity(bodyJson);
			se.setContentType("application/json");
			se.setContentEncoding("utf-8");
			post.setEntity(se);
			response = client.execute(post);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
				post.releaseConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static String postMethod(String url, HttpEntity entity, Header header) {
		HttpPost httpPost = null;
		CloseableHttpResponse response = null;

		try {
			httpPost = new HttpPost(url);
			httpPost.addHeader(header);
			httpPost.setEntity(entity);
			response = client.execute(httpPost);
			HttpEntity resultEntity = response.getEntity();
			String result = EntityUtils.toString(resultEntity);
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String putMethod(String url, HttpEntity entity, Header header) {
		HttpPut httpPut = null;
		CloseableHttpResponse response = null;

		try {
			httpPut = new HttpPut(url);
			httpPut.addHeader(header);
			httpPut.setEntity(entity);
			response = client.execute(httpPut);
			HttpEntity resultEntity = response.getEntity();
			String result = EntityUtils.toString(resultEntity);
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String putMethod(String url, Header header) {
		return putMethod(url, null, header);
	}

	public static String deleteMethod(String url, Header header) {

		HttpDelete httpDelete = null;
		CloseableHttpResponse response = null;
		try {
			httpDelete = new HttpDelete(url);
			httpDelete.addHeader(header);
			response = client.execute(httpDelete);
			HttpEntity resultEntity = response.getEntity();
			String result = EntityUtils.toString(resultEntity);
			return result;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String deleteMethodNoReturn(String url, Header header) {

		HttpDelete httpDelete = null;
		CloseableHttpResponse response = null;
		try {
			httpDelete = new HttpDelete(url);
			httpDelete.addHeader(header);
			response = client.execute(httpDelete);
			HttpEntity resultEntity = response.getEntity();
			if (resultEntity != null) {
				String result = EntityUtils.toString(resultEntity);
				return result;
			}

		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 无权限验证
	public static String postMethod(String url, String bodyJson)
			throws Exception {
		HttpPost post = null;
		CloseableHttpResponse response = null;
		try {
			post = new HttpPost(url);
			StringEntity se = new StringEntity(bodyJson, "utf-8");
			se.setContentType("application/json");
			// se.setContentEncoding("utf-8");
			post.setEntity(se);
			response = client.execute(post);
			HttpEntity entity = response.getEntity();
			String result = EntityUtils.toString(entity);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				response.close();
				post.releaseConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public static BasicNameValuePair getNameValuePair(String name, String value) {
		return new BasicNameValuePair(name, value);
	}

	// public static Header getAuthorizationHeader(CFClientToken token) throws
	// Exception
	// {
	// return new BasicHeader("Authorization",
	// "bearer "+token.getAcessToken().getAccess_token());
	// }

}
