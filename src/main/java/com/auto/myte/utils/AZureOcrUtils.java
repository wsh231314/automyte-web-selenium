package com.auto.myte.utils;

import java.io.InputStream;
import java.net.URI;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class AZureOcrUtils {

	public static JSONObject getOcrRespone(InputStream body) {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpEntity entity = null;
		JSONObject json = null;

		try {
			URIBuilder builder = new URIBuilder("https://api.cognitive.azure.cn/vision/v1.0/ocr");

			builder.setParameter("language", "unk");
			builder.setParameter("detectOrientation ", "true");

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/octet-stream");
			request.setHeader("Ocp-Apim-Subscription-Key", "fa51e86bf6e04a2098a57c95693cafbc");

			// Request body
			InputStreamEntity reqEntity = new InputStreamEntity(body);
			request.setEntity(reqEntity);
			

			HttpResponse response = httpclient.execute(request);
			entity = response.getEntity();

			if (entity != null) {
				json = JSON.parseObject(EntityUtils.toString(entity));  
				System.out.println();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return json;
	}

	public static HttpEntity getOcrResponeByUrl(String body) {

		CloseableHttpClient httpclient = HttpClients.createDefault();
		HttpEntity entity = null;

		try {
			URIBuilder builder = new URIBuilder("https://api.cognitive.azure.cn/vision/v1.0/ocr");

			builder.setParameter("language", "unk");
			builder.setParameter("detectOrientation ", "true");

			URI uri = builder.build();
			HttpPost request = new HttpPost(uri);
			request.setHeader("Content-Type", "application/json");
			request.setHeader("Ocp-Apim-Subscription-Key", "fa51e86bf6e04a2098a57c95693cafbc");

			// Request body
			StringEntity reqEntity = new StringEntity(body);
			request.setEntity(reqEntity);
			

			HttpResponse response = httpclient.execute(request);
			entity = response.getEntity();

			if (entity != null) {
				System.out.println(EntityUtils.toString(entity));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return entity;
	}
}
