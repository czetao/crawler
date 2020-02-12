package czt.crawler.test;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/***
 * httpclentpool连接池 学习
 */
public class HttpClientPool {
    public static void main(String[] args) throws IOException {
        //创建连接池管理器
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();

        //设置最大连接数
        cm.setMaxTotal(100);
        //设置每个主机的最大连接数,即是每个网站
        cm.setDefaultMaxPerRoute(10);
        //使用连接池管理器发起请求
        doget(cm);

    }

    private static void doget(PoolingHttpClientConnectionManager cm) throws IOException {
        //不是每次创建新的httpclient,而是从连接池中获取httpclient对象
        CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(cm).build();
        HttpGet httpget = new HttpGet("http://www.itcast.cn");
        CloseableHttpResponse response = httpClient.execute(httpget);
        if (response.getStatusLine().getStatusCode() == 200) {
            String content = EntityUtils.toString(response.getEntity(), "utf8");
            System.out.println(content);
        }
        //不能在这里关闭httpclient
    }
}
