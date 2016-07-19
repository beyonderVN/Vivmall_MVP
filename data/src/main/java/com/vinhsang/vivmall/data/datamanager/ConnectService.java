package com.vinhsang.vivmall.data.datamanager;

import android.util.Log;

import com.vinhsang.vivmall.data.net.Extra;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;

public class ConnectService {
	private static final String TAG = "ConnectService";
	interface ConnectServiceInterface {

	}
	private static String url="http://103.3.249.99:8080/SrvAppVivmall/RestSrv/SrvConnect/";

	public static String getUrl() {
		return url;
	}

	public static void setUrl(String url) {
		ConnectService.url = url;
	}

	public enum RequestMethod {
		GET,
		POST
	}
	public static String ExecGetDataReturnJson
						(
							RequestMethod method,							
							String file,
							String code,
							String[]fields,
							String[] values) throws Exception {
		String result = "";
		ArrayList<NameValuePair> params =new ArrayList<NameValuePair>();
		ArrayList<NameValuePair> headers =new ArrayList<NameValuePair>();
		//		
		if(fields.length!=values.length){
			result = "-1";
			return result;
		}
		params.add(new BasicNameValuePair("file", file));
		params.add(new BasicNameValuePair("code", code));
		params.add(new BasicNameValuePair("fields", Extra.ConvertArrayToJson(fields)));
		params.add(new BasicNameValuePair("values", Extra.ConvertArrayToJson(values)));
		String action = "ExecGetDataReturnJson";
		result = Execute(action,method,params,headers);
		return result;		

	}
	public static String ExecGetDataReturnJson
			(
					RequestMethod method,
					String file,
					String code
					) throws Exception {
		String result = "";
		ArrayList<NameValuePair> params =new ArrayList<NameValuePair>();
		ArrayList<NameValuePair> headers =new ArrayList<NameValuePair>();
		//

		params.add(new BasicNameValuePair("file", file));
		params.add(new BasicNameValuePair("code", code));
		String action = "ExecGetDataReturnJsonBase";
		result = Execute(action,method,params,headers);
		return result;

	}
	public static String ExecShoopingCart
			(
					RequestMethod method,
					String customer,
					String cart) throws Exception {
		String result = "";
		ArrayList<NameValuePair> params =new ArrayList<NameValuePair>();
		ArrayList<NameValuePair> headers =new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("customer", customer));
		params.add(new BasicNameValuePair("cart", cart));
		String action = "ExecShoopingCart";
		result = Execute(action,method,params,headers);
		return result;
	}

	public static String ExecContact
			(
					RequestMethod method,
					String contact) throws Exception {
		String result = "";
		ArrayList<NameValuePair> params =new ArrayList<NameValuePair>();
		ArrayList<NameValuePair> headers =new ArrayList<NameValuePair>();

		params.add(new BasicNameValuePair("contact", contact));
		String action = "ExecContact";
		result = Execute(action,method,params,headers);
		return result;
	}
	public static String cal9palace
			(
					RequestMethod method,
					String housekua,
					String lang) throws Exception {
		String result = "";
		ArrayList<NameValuePair> params =new ArrayList<NameValuePair>();
		ArrayList<NameValuePair> headers =new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("housekua", housekua));
		params.add(new BasicNameValuePair("lang", lang));
		String action = "cal9palace";
		result = Execute(action,method,params,headers);
		return result;
	}
	public static String ExecBoFunctionReturnList
						(
							RequestMethod method,							
							String file,
							String code,
							String[]fields,
							String[] types,
							String[] values,
							String[]direcs) throws Exception {
		String result = "";
		ArrayList<NameValuePair> params =new ArrayList<NameValuePair>();
		ArrayList<NameValuePair> headers =new ArrayList<NameValuePair>();
		//		
		if(fields.length!=values.length){
			result = "-1";
			return result;
		}
		params.add(new BasicNameValuePair("file", file));
		params.add(new BasicNameValuePair("code", code));
		params.add(new BasicNameValuePair("fields", Extra.ConvertArrayToJson(fields)));
		params.add(new BasicNameValuePair("types",  Extra.ConvertArrayToJson(types)));
		params.add(new BasicNameValuePair("values", Extra.ConvertArrayToJson(values)));
		params.add(new BasicNameValuePair("direcs", Extra.ConvertArrayToJson(direcs)));
		String action = "ExecBoFunctionReturnList";
		result = Execute(action,method,params,headers);
		Log.d(TAG, "ExecBoFunctionReturnList: "+result);
		return result;
	}
	public static String LoginExec
			(
					RequestMethod method,
					String file,
					String code,
					String[]fields,
					String[] values) throws Exception {
		String result = "";
		ArrayList<NameValuePair> params =new ArrayList<NameValuePair>();
		ArrayList<NameValuePair> headers =new ArrayList<NameValuePair>();
		//
		if(fields.length!=values.length){
			result = "-1";
			return result;
		}
		params.add(new BasicNameValuePair("file", file));
		params.add(new BasicNameValuePair("code", code));
		params.add(new BasicNameValuePair("pfield", Extra.ConvertArrayToJson(fields)));
		params.add(new BasicNameValuePair("pvalue", Extra.ConvertArrayToJson(values)));

		String action = "LoginExec";
		result = Execute(action,method,params,headers);
		return result;
	}
	private static String Execute(String action,RequestMethod method,ArrayList<NameValuePair> params,ArrayList<NameValuePair> headers) throws Exception
	{
		String response ="";
		String mainUrl = url+action;
		switch(method) {
		case GET:
		{
			//add parameters
			String combinedParams = "";
			if(!params.isEmpty()){
				combinedParams += "?";
				for(NameValuePair p : params)
				{
					String paramString = p.getName() + "=" +
							URLEncoder.encode(p.getValue(), "UTF-8");
					if(combinedParams.length() > 1)
					{
						combinedParams  +=  "&" + paramString;
					}
					else
					{
						combinedParams += paramString;
					}
				}
			}
			//Log.d(TAG, "Execute: "+mainUrl + combinedParams);
			HttpGet request = new HttpGet(mainUrl + combinedParams);

			//add headers
			for(NameValuePair h : headers)
			{
				request.addHeader(h.getName(), h.getValue());
			}
			Log.d(TAG, "Execute: "+mainUrl + combinedParams);
			response= executeRequest(request, mainUrl);
			break;
		}
		case POST:
		{
			HttpPost request = new HttpPost(mainUrl);

			//add headers
			for(NameValuePair h : headers)
			{
				request.addHeader(h.getName(), h.getValue());
			}

			if(!params.isEmpty()){
				request.setEntity(
						new UrlEncodedFormEntity(params, HTTP.UTF_8));
			}

			response = executeRequest(request, mainUrl);
			break;
		}
		default:break;
		}
		return response;
	}//
	private static String executeRequest(HttpUriRequest request, String url)
	{
		HttpClient client = new DefaultHttpClient();

		HttpResponse httpResponse;
		int responseCode;
		String message;
		String response="";
		try {
			httpResponse = client.execute(request);
			responseCode = httpResponse.
					getStatusLine().getStatusCode();
			message = httpResponse.
					getStatusLine().getReasonPhrase();

			HttpEntity entity = httpResponse.getEntity();

			if (entity != null) {

				InputStream instream = entity.getContent();
				response = convertStreamToString(instream);

				// Closing the input stream will trigger 
				//com.vinhsang.app.Vivmall.connection release
				instream.close();
			}

		} catch (ClientProtocolException e)  {
			e.printStackTrace();
			client.getConnectionManager().shutdown();

		} catch (IOException e) {
			e.printStackTrace();
			client.getConnectionManager().shutdown();

		}catch (Exception e){

			e.printStackTrace();
		}
		Log.d(TAG, "executeRequest: "+response);
//		Log.d(TAG, "executeRequest: "+Thread.getAllStackTraces());
		return response;
	}
	private static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();

		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	
}
