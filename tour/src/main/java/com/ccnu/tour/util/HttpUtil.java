package com.ccnu.tour.util;

import com.ccnu.tour.config.CommonJsonException;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yang
 * @CreateTime: 2021-03-03 20:26
 * @Description: 网络通信
 */
public class HttpUtil {
    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);
    private static final int SUCCESS = 200;

    private static CloseableHttpClient init() {
        int timeout = 10;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();
        return HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }

    public static void main(String[] args) {
        String host = "http://yzx.market.alicloudapi.com/yzx/sendSms";
        String appcode = "204375965fc144928827e017c9fb9574";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);

        Map<String, String> querys = new HashMap<>();
        querys.put("mobile", "17621615772");
        querys.put("param", "code:1234");
        querys.put("tpl_id", "TP1710262");
        try {
            System.out.println(post(host, headers, querys));
        }catch (Exception e){

        }

        System.out.println(AESUtil.doEncrypt("204375965fc144928827e017c9fb9574"));

    }

    public static String post(String url, Map<String, String> headers, Map<String, String> parameterMap) {
        CloseableHttpClient httpClient = init();
        HttpPost httpPost = new HttpPost(url);
        headers.forEach(httpPost::addHeader);
        List<NameValuePair> urlParameters = new ArrayList<>();
        parameterMap.forEach((key, value) -> urlParameters.add(new BasicNameValuePair(key, value)));
        HttpEntity postParams;
        try {
            postParams = new UrlEncodedFormEntity(urlParameters);

            httpPost.setEntity(postParams);

            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if (SUCCESS == httpResponse.getStatusLine().getStatusCode()) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(
                        httpResponse.getEntity().getContent()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
                reader.close();
                httpClient.close();
                return response.toString();
            } else {
                log.error("http fail"+ EntityUtils.toString(httpResponse.getEntity(), "utf-8"));
                throw new CommonJsonException(ErrorEnum.E_600);
            }
        } catch (Exception e) {
            throw new CommonJsonException(ErrorEnum.E_600);
        }

    }
}
