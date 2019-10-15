package com.wx.spring.utils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * http方法
 * @Description:
 * @Title: HttpClientUtil
 * @author wangxin
 * @date 2019年10月15日
 */
public class HttpClientUtil {
    private static int TIMEOUT = 8000;
    private static String CHARSET = "utf-8";
    final static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);

    public static final String getData(String url) {
        return getData(url, null);
    }

    /**
     * get 请求
     *
     * @param url     请求地址
     * @param headers
     * @return
     */
    @SuppressWarnings("finally")
    public static final String getData(String url, Map<String, String> headers) {
        if (url.getBytes().length > 2083) { // 鉴于IE浏览器支持2083字符数，所以不超出这个数字
            String content = String.format("请求字符长度：%s，请求链接：%s", url.getBytes().length, url);
            logger.warn(content);
        }
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String result = null;
        Date beginDate = new Date();
        try {
            HttpGet httpget = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT)
                    .build();
            httpget.setConfig(requestConfig);
            if (headers != null) {
                Set<Map.Entry<String, String>> sets = headers.entrySet();
                for (Map.Entry<String, String> map : sets) {
                    httpget.setHeader(map.getKey(), map.getValue());
                }
            }
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, CHARSET);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            logger.info("发起get请求 url:{} result:{} 响应时间:{}毫秒", url, result,
                    (new Date().getTime() - beginDate.getTime()));
            return result;
        }
    }

    public static final String postDataBody(String url, String bodyContent) {
        return postDataBody(url, bodyContent, null);
    }

    @SuppressWarnings("finally")
	public static final String postDataBody(String url, String bodyContent, Map<String, String> headers) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String result = null;
        Date beginDate = new Date();
        try {
            HttpPost post = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT)
                    .build();
            post.setConfig(requestConfig);

            if (bodyContent != null) {
                post.setEntity(new StringEntity(bodyContent, CHARSET));
                post.setHeader("Content-type", "application/json");
            }
            if (headers != null) {
                Set<Map.Entry<String, String>> sets = headers.entrySet();
                for (Map.Entry<String, String> set : sets) {
                    post.setHeader(set.getKey(), set.getValue());
                }
            }
            CloseableHttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, CHARSET);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            logger.info("发起post请求 url : {}  params : {}  result : {} 响应时间 : {}毫秒", url, bodyContent, result, (new Date().getTime() - beginDate.getTime()));
            return result;
        }
    }

    public static final String postDataBody(String url, Map<String, String> map) {
        return postDataBody(url, map, null);
    }

    @SuppressWarnings("finally")
    public static final String postDataBody(String url,
                                            Map<String, String> params, Map<String, String> headers) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String result = null;
        Date beginDate = new Date();
        try {
            HttpPost post = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT)
                    .build();
            post.setConfig(requestConfig);

            if (params != null) {
                post.setEntity(new StringEntity(JSON.toJSONString(params),
                        CHARSET));                
                post.setHeader("Content-type", "application/json");
            }
            if (headers != null) {
                Set<Map.Entry<String, String>> sets = headers.entrySet();
                for (Map.Entry<String, String> set : sets) {
                    post.setHeader(set.getKey(), set.getValue());
                }
            }
            CloseableHttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, CHARSET);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            logger.info("发起post请求 url:{},params:{},result:{} 响应时间:{}毫秒", url,
                    params, result,
                    (new Date().getTime() - beginDate.getTime()));
            return result;
        }
    }

    public static final String postData(String url, Map<String, String> params) {
        return postData(url, params, null);
    }

    public static final String postData(String url, Map<String, String> params,
                                        Map<String, String> headers) {
        return postData(url, params, headers, TIMEOUT);
    }

    @SuppressWarnings("finally")
    public static final String postData(String url, Map<String, String> params,
                                        Map<String, String> headers, int timeout) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String result = null;
        Date beginDate = new Date();
        try {
            HttpPost post = new HttpPost(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(timeout).setConnectTimeout(timeout)
                    .build();
            post.setConfig(requestConfig);
            if (params != null) {
                List<NameValuePair> nvps = new ArrayList<NameValuePair>();
                Set<Map.Entry<String, String>> sets = params.entrySet();
                for (Map.Entry<String, String> map : sets) {
                    nvps.add(new BasicNameValuePair(map.getKey(), map
                            .getValue()));
                }
                post.setEntity(new UrlEncodedFormEntity(nvps, CHARSET));
            }
            if (headers != null) {
                Set<Map.Entry<String, String>> sets = headers.entrySet();
                for (Map.Entry<String, String> map : sets) {
                    post.setHeader(map.getKey(), map.getValue());
                }
            }
            CloseableHttpResponse response = httpclient.execute(post);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, CHARSET);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            logger.info("发起post请求 url:{},params:{},result:{} 响应时间:{}毫秒", url,
                    params, result,
                    (new Date().getTime() - beginDate.getTime()));
            return result;
        }
    }

    @SuppressWarnings("finally")
    public static final String putData(String url, Map<String, String> params,
                                       Map<String, String> headers) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        String result = null;
        Date beginDate = new Date();
        try {
            if (params != null) {
                Set<Map.Entry<String, String>> sets = params.entrySet();
                StringBuffer sb = new StringBuffer();
                for (Map.Entry<String, String> map : sets) {
                    sb.append(map.getKey() + "=" + URLEncoder.encode(map.getValue(), "utf8") + "&");
                }
                url += "?" + sb.toString();
            }
            HttpPut put = new HttpPut(url);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT)
                    .build();
            put.setConfig(requestConfig);
            if (headers != null) {
                Set<Map.Entry<String, String>> sets = headers.entrySet();
                for (Map.Entry<String, String> map : sets) {
                    put.setHeader(map.getKey(), map.getValue());
                }
            }
            CloseableHttpResponse response = httpclient.execute(put);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    result = EntityUtils.toString(entity, CHARSET);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
            logger.info("发起put请求 url:{}, result:{} 响应时间:{}毫秒", url, result,
                    (new Date().getTime() - beginDate.getTime()));
            return result;
        }
    }

    /**
     * 组装URL<br>
     *
     * @param url       原始的请求url,不要带问号后的参数部分
     * @param paramsMap 键值对的字符串,键值都会进行URL编码,所以key支持有空格的特殊key,value也支持中文. 没有参数可以传null
     * @return
     * @throws Exception
     */
    public static String getFullUrl(String url, Map<String, Object> paramsMap) throws Exception {
        //参数格式转化
        String paramsStr = null;
        paramsStr = getJsonDataParams(paramsMap);

        //URL、参数拼接
        StringBuilder fullUrl = new StringBuilder(url);
        if (!StrUtils.isEmpty(paramsStr)) {
            fullUrl.append("?");
            fullUrl.append(paramsStr);
        }

        return fullUrl.toString();
    }

    /**
     * 转化成jsonData的参数风格(不带问号)
     *
     * @param paramsMap
     * @return
     * @throws Exception
     */
    public static String getJsonDataParams(Map<String, Object> paramsMap) throws Exception {

        StringBuilder sb = new StringBuilder();
        if (StrUtils.isEmpty(paramsMap)) {
            sb.append("json_data={}");
        } else {
            sb.append("json_data=").append(URLEncoder.encode(JSONObject.toJSONString(paramsMap), "UTF-8"));
        }
        return sb.toString();
    }

    /**
     * 发起https请求并获取结果
     *
     * @param fullUrl       请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr     提交的数据
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
     * @throws Exception 
     * @throws SystemException
     */
    public static JSONObject httpRequest(String fullUrl, String requestMethod, String outputStr, String secret) throws Exception  {
        OutputStream outputStream = null;
        BufferedReader bufferedReader = null;
        InputStreamReader inputStreamReader = null;
        InputStream inputStream = null;
        JSONObject jsonObject = null;
        StringBuffer buffer = new StringBuffer();

        try {

            URL url = new URL(fullUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);
            if (!StrUtils.isEmpty(secret)) {
                httpUrlConn.setRequestProperty("secret", secret);
            }

            httpUrlConn.connect();

            // 当有数据需要提交时
            if (null != outputStr) {
                outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
            }

            // 将返回的输入流转换成字符串
            if (HttpURLConnection.HTTP_OK == httpUrlConn.getResponseCode() ||
                    HttpURLConnection.HTTP_CREATED == httpUrlConn.getResponseCode() ||
                    HttpURLConnection.HTTP_ACCEPTED == httpUrlConn.getResponseCode()) {
                inputStream = httpUrlConn.getInputStream();
            } else {
                inputStream = httpUrlConn.getErrorStream();
            }

            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            httpUrlConn.disconnect();

            if (StrUtils.isEmpty(buffer.toString())) {
                return null;
            }

            jsonObject = JSONObject.parseObject(buffer.toString());
        } catch (ConnectException ce) {
            logger.error("Server connection timed out. Request link : " + fullUrl, ce);
            throw new Exception("Server connection timed out.");
        } catch (Exception e) {
            logger.error("https request error:{}. Request link : " + fullUrl, e);
            throw new Exception("https request error:{}");
        } finally {
            if (null != outputStream) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (null != bufferedReader) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (null != inputStreamReader) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            if (null != inputStream) {
                try {
                    inputStream.close();
                    inputStream = null;
                } catch (IOException e) {
                    logger.error(e.getMessage(), e);
                }
            }
        }
        return jsonObject;
    }

    /**
     * 转化占位符类型的URL
     * @param url
     * @param paramsMap
     * @return
     * @throws Exception 
     * @throws SystemException
     */
    public static String transUrlParams(String url, Map<String, Object> paramsMap) throws Exception {

        if (StrUtils.isEmpty(url))
        {
            throw new Exception("API地址不能为空");
        } else
        {
            Matcher m= Pattern.compile("\\{(.*?)\\}").matcher(url);
            while(m.find()){

                if (StrUtils.isEmpty(paramsMap))
                {
                    throw new Exception(url + ":接口地址没有找到占位符"+ m.group() +"需要的参数，不允许访问接口！");
                }

                Object obj = paramsMap.get(m.group().substring(1, m.group().length()-1).trim());
                if (StrUtils.isEmpty(obj))
                {
                    throw new Exception(url + ":接口地址没有找到占位符"+ m.group() +"需要的参数，不允许访问接口！");
                } else
                {
                    url = url.replace(m.group(), obj.toString());
                    paramsMap.remove(m.group().substring(1, m.group().length()-1).trim());
                }
            }
            return url;
        }
    }

    /**
     * 获取输入流
     *
     * @param url
     * @param headers
     * @return
     */
    public static byte[] getInputStream(String url, Map<String, String> headers, Map<String, String> params) {
        StringBuffer stb = new StringBuffer();
        if (params != null) {
            for (String key : params.keySet()) {
                try {
                    if (params.get(key) != null) {
                        stb.append(String.format("%s=%s&", key, URLEncoder.encode(params.get(key), CHARSET)));
                    }
                } catch (Exception e) {
                    // don't care
                }
            }
        }
        if (!url.contains("?")) {
            url += "?" + stb.toString();
        } else {
            url += "&" + stb.toString();
        }

        byte [] bytes = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        InputStream input = null;
        try {
            HttpGet httpget = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(TIMEOUT).setConnectTimeout(TIMEOUT).build();
            httpget.setConfig(requestConfig);
            if(headers != null){
                Set<Map.Entry<String, String>> sets=headers.entrySet();
                for(Map.Entry<String, String> map:sets){
                    httpget.setHeader(map.getKey(), map.getValue());
                }
            }
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    input = entity.getContent();
                    bytes = inputStream2byteArray(input);
                }
            } finally {
                response.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpclient.close();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    private static byte[] inputStream2byteArray(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        byte[] buff = new byte[100];
        int rc;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            stream.write(buff, 0, rc);
        }
        byte[] in2b = stream.toByteArray();

        return in2b;
    }

}
