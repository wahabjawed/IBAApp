package com.deprecated;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;



import android.os.AsyncTask;
import android.util.Log;

public class JSONParser1 {

	InputStream is = null;
	JSONObject jObj = null;
	String json = "";
	String URL = "";
	String Method = "";
	List<NameValuePair> Params;

	// constructor
	public JSONParser1(String url, String method,
			List<NameValuePair> params) {
		URL = url;
		Method = method;
		Params = params;
		
	}

	// function get json from url
	// by making HTTP POST or GET mehtod
	public JSONObject makeHttpRequest() throws InterruptedException,
			ExecutionException {
	
		// Making HTTP request

		PerformTask fmgs = new PerformTask();
		return fmgs.execute().get();

		// return jObj
	}

	class PerformTask extends AsyncTask<Void, Void, JSONObject> {

		@Override
		protected JSONObject doInBackground(Void... params) {
			// TODO Auto-generated method stub

			try {

				// check for request method
				if (Method == "POST") {
					// request method is POST
					// defaultHttpClient
					DefaultHttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost(URL);
					httpPost.setEntity(new UrlEncodedFormEntity(Params));

					HttpResponse httpResponse = httpClient.execute(httpPost);
					HttpEntity httpEntity = httpResponse.getEntity();
					is = httpEntity.getContent();

				} else if (Method == "GET") {
					// request method is GET
					DefaultHttpClient httpClient = new DefaultHttpClient();
					String paramString = URLEncodedUtils
							.format(Params, "utf-8");
					URL += "?" + paramString;
					HttpGet httpGet = new HttpGet(URL);

					HttpResponse httpResponse = httpClient.execute(httpGet);
					HttpEntity httpEntity = httpResponse.getEntity();
					is = httpEntity.getContent();
				}

			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n");
				}
				is.close();

				json = sb.toString();
			} catch (Exception e) {
				Log.e("Buffer Error", "Error converting result " + e.toString());
			}

			// try parse the string to a JSON object
			try {
				jObj = new JSONObject(json);
			} catch (JSONException e) {
				Log.e("JSON Parser", "Error parsing data " + e.toString());
			}

			// return JSON String
			return jObj;

		}

	}

}
