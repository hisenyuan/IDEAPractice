package com.hisen.utils;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * @author hisenyuan
 * @time 2018/05/12 11:30
 * @description httpclient请求类
 */
public class HttpClientUtil {

    private static Logger LOGGER = Logger.getLogger(HttpClientUtil.class);

    /**
     * post with http basic auth
     *
     * @param data map param
     * @param auth http basic auth name
     */
    public static String sendPostWithAuth(String url, Map<String, String> data, String auth) {
        String responseContent = null;
        try {
            // auth
            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(auth, "");
            provider.setCredentials(AuthScope.ANY, credentials);
            HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider)
                    .build();
            // form data
            HttpPost httppost = new HttpPost(url);
            httppost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            List<NameValuePair> pairs = new ArrayList<>();
            data.entrySet().stream().forEach(entry ->
                    pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue()))
            );
            UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(pairs);
            httppost.setEntity(urlEncodedFormEntity);

            // post process
            CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httppost);
            LOGGER.info("http respone code:" + response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            LOGGER.info("http respone data:" + responseContent);
            // close resources
            response.close();
            ((CloseableHttpClient) client).close();
        } catch (Exception e) {
            LOGGER.error("send post error", e);
        }
        return responseContent;
    }

    /**
     * get with http basic auth
     */
    public static String sendGetWithAuth(String url, Map<String, String> data, String auth) {
        String responseContent = null;
        try {
            // auth
            CredentialsProvider provider = new BasicCredentialsProvider();
            UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(auth, "");
            provider.setCredentials(AuthScope.ANY, credentials);
            HttpClient client = HttpClientBuilder.create().setDefaultCredentialsProvider(provider)
                    .build();
            // form data
            url = url + "?" + getGetParamByMap(data);
            HttpGet httpGet = new HttpGet(url);
            httpGet.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            // get process
            CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httpGet);
            LOGGER.info("http respone code:" + response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();
            responseContent = EntityUtils.toString(entity, "UTF-8");
            LOGGER.info("http respone data:" + responseContent);
            // close resources
            response.close();
            ((CloseableHttpClient) client).close();
        } catch (Exception e) {
            LOGGER.error("send post error", e);
        }
        return responseContent;
    }

    public static String sendPost(String url, Map<String, String> data,
                                  Map<String, String> header) {
        String responseContent = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            HttpPost httppost = new HttpPost(url);
            // add header
            if (!header.isEmpty()) {
                header.entrySet().stream().forEach(
                        entry -> httppost.addHeader(entry.getKey(), entry.getValue()));
            }
            List<NameValuePair> pairs = new ArrayList<>();
            data.entrySet().stream().forEach(
                    entry -> pairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue())));
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(pairs);
            httppost.setEntity(entity);

            // post process
            CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httppost);
            LOGGER.info("http respone code:" + response.getStatusLine().getStatusCode());
            HttpEntity resEntity = response.getEntity();
            responseContent = EntityUtils.toString(resEntity, "UTF-8");
            LOGGER.info("http respone data:" + responseContent);
            // close resources
            response.close();
            ((CloseableHttpClient) client).close();
        } catch (Exception e) {
            LOGGER.error("send post error", e);
        }
        return responseContent;
    }

    public static String sendGet(String url, Map<String, String> data, Map<String, String> header) {
        String responseContent = null;
        try {
            HttpClient client = HttpClientBuilder.create().build();
            url = url + "?" + getGetParamByMap(data);
            HttpGet httpGet = new HttpGet(url);
            // add header
            if (!header.isEmpty()) {
                header.entrySet().stream().forEach(
                        entry -> httpGet.addHeader(entry.getKey(), entry.getValue()));
            }
            // get process
            CloseableHttpResponse response = (CloseableHttpResponse) client.execute(httpGet);
            LOGGER.info("http respone code:" + response.getStatusLine().getStatusCode());
            HttpEntity resEntity = response.getEntity();
            responseContent = EntityUtils.toString(resEntity, "UTF-8");
            LOGGER.info("http respone data:" + responseContent);
            // close resources
            response.close();
            ((CloseableHttpClient) client).close();
        } catch (Exception e) {
            LOGGER.error("send get error", e);
        }
        return responseContent;
    }

    public static String getGetParamByMap(Map<String, String> getParamMap) {
        if (null == getParamMap || getParamMap.isEmpty()) {
            return "";
        }
        String getParamStr = getParamMap.entrySet()
                .stream()
                .filter((entry) -> !StringUtils.isEmpty(entry.getValue()))
                .sorted(Map.Entry.comparingByKey())
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));
        return getParamStr;
    }
}


