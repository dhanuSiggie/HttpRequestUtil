//$Id$
package org.http;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


public class HttpRequestUtil {

	public static final Logger LOG =Logger.getLogger(HttpRequestUtil.class.getName());

	public static Response postRequest(String url,Map <String,String> headers,JSONObject body ) {
		CloseableHttpClient client = HttpClients.createDefault();
		Response res=null;
		try {
			HttpPost request=new HttpPost(url);
			LOG.info("HttpUtil Called -> Url" + " >> " +url);
			if(headers!=null) {
				request.setHeader("Content-type", "application/json");
				Set<String> keys = headers.keySet();
				for(String header: keys) {
					request.setHeader(header,headers.get(header));
					LOG.info("req header>> " + header);
				}
			}

			if(body!=null) {
				StringEntity params =new StringEntity(body.toString(),"UTF-8");
				request.setEntity(params);
				LOG.info("Body of the Request>>"+" >> "+body.toString());
			}
			
			CloseableHttpResponse response = client.execute(request);
			res=new Response();
			HttpEntity entity = response.getEntity();
			res.setHttpStatus(response.getStatusLine().getStatusCode());
			res.setEntity(EntityUtils.toString(entity));

			return res;

		} catch (Exception e) {
			LOG.info("HttpUtilUtil :: invokeUrl :: unexpected exception" + e.getMessage());
			return res;
		} finally {
			try {
				client.close();
			} catch (Exception e) {
				LOG.info("Error in closing stream"+e.getMessage());
			}
		}
	}public static Response postRequestForFileUploadV1(URIBuilder url,Map <String,String> headers,File fi ) {
		CloseableHttpClient client = HttpClients.createDefault();
		Response res=null;
		try {
			HttpPost request=new HttpPost(url.build());
			request.setHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
			LOG.info("HttpUtil Called -> Url" + " >> " +url);

			if(headers!=null) {
				Set<String> keys = headers.keySet();
				for(String header: keys) {
					request.setHeader(header,headers.get(header));
					LOG.info("req header>> " + header + " >> " +headers.get(header));
				}
			}
			if(fi!=null) {
			InputStream in=new FileInputStream(fi);
			request.setEntity(new InputStreamEntity(in));
			request.setHeader("Content-Type", "application/octet-stream");
			}
			CloseableHttpResponse response = client.execute(request);
			res=new Response();
			HttpEntity entity = response.getEntity();
			res.setHttpStatus(response.getStatusLine().getStatusCode());
			res.setEntity(EntityUtils.toString(entity));
			return res;
		} catch (Exception e) {
			LOG.info("HttpUtilUtil :: invokeUrl :: unexpected exception" + e.getMessage());
			return res;
		} finally {
			try {
				client.close();
			} catch (Exception e) {
				LOG.info("Error in closing stream"+e.getMessage());
			}
		}
	} 
	public static Response deleteRequest(String url,Map <String,String> headers,JSONObject body) {
		CloseableHttpClient client = HttpClients.createDefault();
		Response res=null;
		try {
	        HttpDelete request = new HttpDelete(url);
			LOG.info("HttpUtil Called -> Url" + " >> " +url);
			if(headers!=null) {
				request.setHeader("Content-type", "application/json");
				Set<String> keys = headers.keySet();
				for(String header: keys) {
					request.setHeader(header,headers.get(header));
					LOG.info("req header>> " + header);
				}
			}      
			CloseableHttpResponse response = client.execute(request);
			res=new Response();
			res.setHttpStatus(response.getStatusLine().getStatusCode());			
			return res;

		} catch (Exception e) {
			LOG.info("HttpUtilUtil :: invokeUrl :: unexpected exception" + e.getMessage());
			return res;
		} finally {
			try {
				client.close();
			} catch (Exception e) {
				LOG.info("Error in closing stream"+e.getMessage());
			}
		}
	}
	public static Response deleteRequest(String url,Map <String,String> headers){
		return deleteRequest(url, headers,null);
	}
	public static Response getRequest(String url,Map <String,String> headers ) {
		CloseableHttpClient client = HttpClients.createDefault();
		Response res=null;
		try {
			HttpGet request=new HttpGet(url);
			LOG.info("HttpUtil Called -> Url" + " >> " +url);
			if(headers!=null) {
				request.setHeader("Content-type", "application/json");
				Set<String> keys = headers.keySet();
				for(String header: keys) {
					request.setHeader(header,headers.get(header));
					LOG.info("req header>> " + header);
				}
			}
			CloseableHttpResponse response = client.execute(request);
			res=new Response();
			HttpEntity entity = response.getEntity();
			res.setHttpStatus(response.getStatusLine().getStatusCode());
			res.setEntity(EntityUtils.toString(entity));

			return res;

		} catch (Exception e) {
			LOG.info("HttpUtilUtil :: invokeUrl :: unexpected exception" + e.getMessage());
			return res;
		} finally {
			try {
				client.close();
			} catch (Exception e) {
				LOG.info("Error in closing stream"+e.getMessage());
			}
		}
	}
	public static Response getRequest(String url){
		return getRequest(url, null);
	}
	//Post With Query Params 
	public static Response postRequest (URIBuilder url,Map <String,String> headers,JSONObject body ) {
		CloseableHttpClient client = HttpClients.createDefault();
		Response res=null;
		try {
			HttpPost request=new HttpPost(url.build());
			request.setHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
			LOG.info("HttpUtil Called -> Url" + " >> " +url);

			if(headers!=null) {
				request.setHeader("Content-type", "application/json");
				Set<String> keys = headers.keySet();
				for(String header: keys) {
					request.setHeader(header,headers.get(header));
					LOG.info("req header>> " + header + " >> " +headers.get(header));
				}
			}

			if(body!=null) {
				StringEntity params =new StringEntity(body.toString(),"UTF-8");
				request.setEntity(params);
				LOG.info("Body of the Request>>"+" >> "+body.toString());
			}

			CloseableHttpResponse response = client.execute(request);
			res=new Response();
			HttpEntity entity = response.getEntity();
			LOG.info("HTTP Status Code Received >>" +response.getStatusLine().getStatusCode());
			res.setHttpStatus(response.getStatusLine().getStatusCode());
			res.setEntity(EntityUtils.toString(entity));
			return res;
		} catch (Exception e) {
			LOG.info("HttpUtilUtil :: invokeUrl :: unexpected exception" + e.getMessage());
			return res;
		} finally {
			try {
				client.close();
			} catch (Exception e) {
				LOG.info("Error in closing stream"+e.getMessage());
			}
		}
	}
	public static Response postRequest (URIBuilder url) {
		return postRequest(url,null,null);
	}
	public static Response postRequest (URIBuilder url,Map <String,String> headers) {
		return postRequest(url,headers,null);
	}
	public static Response putRequest(String url,Map <String,String> headers,JSONObject body ) {
		CloseableHttpClient client = HttpClients.createDefault();
		Response res=null;
		try {
			HttpPut request=new HttpPut(url);
			LOG.info("HttpUtil Called -> Url" + " >> " +url);
			if(headers!=null) {
				request.setHeader("Content-type", "application/json");
				Set<String> keys = headers.keySet();
				for(String header: keys) {
					request.setHeader(header,headers.get(header));
					LOG.info("req header>> " + header);
				}
			}

			if(body!=null) {
				StringEntity params =new StringEntity(body.toString(),"UTF-8");
				request.setEntity(params);
				LOG.info("Body of the Request>>"+" >> "+body.toString());
			}

			CloseableHttpResponse response = client.execute(request);
			res=new Response();
			HttpEntity entity = response.getEntity();
			res.setHttpStatus(response.getStatusLine().getStatusCode());
			res.setEntity(EntityUtils.toString(entity));

			return res;

		} catch (Exception e) {
			LOG.info("HttpUtilUtil :: invokeUrl :: unexpected exception" + e.getMessage());
			return res;
		} finally {
			try {
				client.close();
			} catch (Exception e) {
				LOG.info("Error in closing stream"+e.getMessage());
			}
		}
	}

	public static Response postRelayMailRequest(String relayMailURL,Map <String,Object> headers) throws IOException {
		Response res =new Response();
		BufferedReader rd = null;
		InputStream is = null;
		URL url = new URL(relayMailURL);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		//Setting Connection Properties
		conn.setRequestMethod("POST");
		conn.setDoOutput(true);
		conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
		StringBuilder result = new StringBuilder();
		for(String key:headers.keySet()) {
			result.append(URLEncoder.encode(key, "UTF-8"));
			result.append("=");
			result.append(URLEncoder.encode(headers.get(key).toString(), "UTF-8"));
			result.append("&");
		}	 
		String data = result.toString();
		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
		wr.write(data, 0, data.length());
		wr.flush();
		wr.close();

		//Get the Response 
		int responseCode = ((HttpURLConnection) conn).getResponseCode();
		is = conn.getInputStream();
		rd = new BufferedReader(new InputStreamReader(is));
		StringBuffer sb = new StringBuffer();
		String line;
		while ((line = rd.readLine()) != null) {
			sb.append(line);
		}
		LOG.info( "RestAPIUtil :: invokeUrl" + sb);
		res.setHttpStatus(responseCode);
		res.setEntity(sb.toString());
		return res;
	}
	//Delete With Query Params 
	public static Response deleteRequest (URIBuilder url,Map <String,String> headers ) {
		CloseableHttpClient client = HttpClients.createDefault();
		Response res=null;
		try {
			HttpDelete request=new HttpDelete(url.build());
			request.setHeader("Content-type", "application/x-www-form-urlencoded;charset=UTF-8");
			LOG.info("HttpUtil Called -> Url" + " >> " +url);

			if(headers!=null) {
				request.setHeader("Content-type", "application/json");
				Set<String> keys = headers.keySet();
				for(String header: keys) {
					request.setHeader(header,headers.get(header));
					LOG.info("req header>> " + header + " >> " +headers.get(header));
				}
			}
			CloseableHttpResponse response = client.execute(request);
			res=new Response();
			HttpEntity entity = response.getEntity();
			res.setHttpStatus(response.getStatusLine().getStatusCode());
			res.setEntity(EntityUtils.toString(entity));
			return res;
		} catch (Exception e) {
			LOG.info("HttpUtilUtil :: invokeUrl :: unexpected exception" + e.getMessage());
			return res;
		} finally {
			try {
				client.close();
			} catch (Exception e) {
				LOG.info("Error in closing stream"+e.getMessage());
			}
		}
	}
}
