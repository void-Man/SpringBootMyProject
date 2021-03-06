package com.cmj.example.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * @author mengjie_chen
 * @description
 * @date 2021/1/7
 */
public class HttpsUtils {
    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 7000;

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        requestConfig = configBuilder.build();
    }

    /**
     * 创建SSL安全连接
     *
     * @param
     * @return org.apache.http.conn.ssl.SSLConnectionSocketFactory
     * @author mengjie_chen
     * @date 2021/1/7
     */
    private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
        SSLConnectionSocketFactory sslsf = null;
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            sslsf = new SSLConnectionSocketFactory(sslContext, (s, sslSession) -> true);
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        }
        return sslsf;
    }

    /**
     * post请求
     *
     * @param url             请求地址
     * @param headerParamList 请求头参数
     * @param body            请求体
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2021/1/7
     */
    public static String post(String url, List<BasicHeader> headerParamList, String body) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        // 通过请求的url初始化一个HttpGet请求
        HttpPost httpPost = new HttpPost(url);
        for (BasicHeader basicHeader : headerParamList) {
            httpPost.addHeader(basicHeader);
        }
        // 解决中文乱码问题
        StringEntity stringEntity = new StringEntity(body, "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        httpPost.setEntity(stringEntity);

        CloseableHttpResponse response = httpClient.execute(httpPost);
        int code = response.getStatusLine().getStatusCode();
        String msg = response.getStatusLine().getReasonPhrase();
        if (code != 200) {
            throw new RuntimeException("请求结果异常：" + msg);
        }
        HttpEntity e = response.getEntity();
        return EntityUtils.toString(e, "UTF-8");
    }

    /**
     * 发送带参数的get请求
     *
     * @param url
     * @param paramList
     * @return java.lang.String
     * @author mengjie_chen
     * @date 2021/3/6
     */
    public static String get(String url, List<BasicNameValuePair> paramList) throws IOException {
        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
        String params = EntityUtils.toString(new UrlEncodedFormEntity(paramList, Consts.UTF_8));
        HttpGet httpGet = new HttpGet(url + "?" + params);
        CloseableHttpResponse response = httpClient.execute(httpGet);
        int code = response.getStatusLine().getStatusCode();
        String msg = response.getStatusLine().getReasonPhrase();
        if (code != 200) {
            throw new RuntimeException("请求结果异常：" + msg);
        }
        HttpEntity e = response.getEntity();
        return EntityUtils.toString(e, "UTF-8");
    }

    /**
     * 测试方法
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {
//        CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory()).setConnectionManager(connMgr).setDefaultRequestConfig(requestConfig).build();
//        // 通过请求的url初始化一个HttpGet请求
//        HttpPost httpPost = new HttpPost("https://www.touzid.com/index.php?/fund/ajax/metrics/");
//        // 设置头部参数
//        httpPost.addHeader("accept", "application/json, text/plain, */*");
//        httpPost.addHeader("accept-encoding", "gzip, deflate, br");
//        httpPost.addHeader("accept-language", "zh-CN,zh;q=0.9");
//        httpPost.addHeader("content-type", "application/json;charset=UTF-8");
//        httpPost.addHeader("cookie", "rwe__Session=9pm0tj1ra8qp84l4ub2f4hh6c6; rwe__user_login=RJ5aWTut2LTOeNVOizltjFFax4W7JrESNRN7WU9taOzRF36y9siqI6d0oPkuGfKCPjCd6nAyKymybKj2kXdcJuB5fCkpUBUWo3pbt%2BKm85iAZCpEBHjWu%2FHKWysl2hO4; Hm_lvt_c0219d21ca52d4d5bb3d64c64189ef01=1609906121,1609906127,1609934388,1609934396; Hm_lpvt_c0219d21ca52d4d5bb3d64c64189ef01=1609989266");
//        httpPost.addHeader("origin", "https://www.touzid.com");
//        httpPost.addHeader("referer", "https://www.touzid.com/fund/index.html");
//        httpPost.addHeader("sec-fetch-dest", "empty");
//        httpPost.addHeader("sec-fetch-mode", "cors");
//        httpPost.addHeader("sec-fetch-site", "same-origin");
//        httpPost.addHeader("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36");
//        httpPost.addHeader("x-requested-with", "XMLHttpRequest");
//        // 参数设置
//        String body = "{\"st\":\"2011\",\"et\":\"2021\",\"type\":\"0\",\"symbol\":\"\",\"indice_type\":\"0\",\"valuetype\":0,\"year\":\"10\",\"etf_no\":1,\"sort\":{\"prop\":\"asset\",\"order\":\"desc\"},\"offset\":1,\"pagesize\":100}";
//        // 解决中文乱码问题
//        StringEntity stringEntity = new StringEntity(body, "UTF-8");
//        stringEntity.setContentEncoding("UTF-8");
//        httpPost.setEntity(stringEntity);
//
//        CloseableHttpResponse response = httpClient.execute(httpPost);
//        HttpEntity e = response.getEntity();
//        System.out.println(EntityUtils.toString(e, "UTF-8"));
    }

}