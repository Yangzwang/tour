package com.ccnu.tour.service.Impl;



import com.sun.deploy.net.HttpUtils;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParamBean;
import org.apache.http.params.HttpParams;
import org.apache.http.params.SyncBasicHttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: yang
 * @CreateTime: 2021-03-02 21:52
 * @Description: 短信相关
 */
public class ImServiceImpl {

    private static CloseableHttpClient init(){
        int timeout = 10;
        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(timeout * 1000)
                .setConnectionRequestTimeout(timeout * 1000)
                .setSocketTimeout(timeout * 1000).build();
        return HttpClientBuilder.create().setDefaultRequestConfig(config).build();
    }



    public static void main(String[] args) {

        CloseableHttpClient httpclient = init();
        String host = "http://yzx.market.alicloudapi.com/yzx/sendSms";
        String appcode = "204375965fc144928827e017c9fb9574";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("mobile", "17621615772");
        querys.put("param", "code:1234");
        querys.put("tpl_id", "TP1710262");
        Map<String, String> bodys = new HashMap<String, String>();

      /*  HttpPost request = new HttpPost(host);
        if (querys != null && !querys.isEmpty()) {
            for (Map.Entry<String, String> entry : querys.entrySet()) {
                request.getParams().setParameter(entry.getKey(), entry.getValue());
            }
        }

        if(headers != null && !headers.isEmpty()) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                request.addHeader(entry.getKey(), entry.getValue());
            }
        }
        request.setHeader("Content-Type", "application/json; charset=UTF-8");*/
      /*  try {
            long startTimeMills = System.currentTimeMillis();

            HttpResponse response = httpclient.execute(request);
            long endTimeMills = System.currentTimeMillis();
            int status = response.getStatusLine().getStatusCode();

            HttpEntity entity = response.getEntity();
            if (status == HttpStatus.SC_OK || status == HttpStatus.SC_CREATED) {

            } else {

            }
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
*/

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(host);
        httpPost.addHeader("Authorization", "APPCODE " + appcode);

        List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
        urlParameters.add(new BasicNameValuePair("mobile", "17621615772"));
        urlParameters.add(new BasicNameValuePair("param", "code:1234"));
        urlParameters.add(new BasicNameValuePair("tpl_id", "TP1710262"));

        HttpEntity postParams;
        try {
            postParams = new UrlEncodedFormEntity(urlParameters);

        httpPost.setEntity(postParams);

        CloseableHttpResponse httpResponse = httpClient.execute(httpPost);

        System.out.println("POST Response Status:: "
                + httpResponse.getStatusLine().getStatusCode());

        BufferedReader reader = new BufferedReader(new InputStreamReader(
                httpResponse.getEntity().getContent()));

        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = reader.readLine()) != null) {
            response.append(inputLine);
        }
        reader.close();

        // print result
        System.out.println(response.toString());
        httpClient.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
