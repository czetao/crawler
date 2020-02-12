package czt.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/***
 * 爬虫第一个案例
 */
public class CrawlerFirst {
    public static void main(String[] args) throws Exception {
        //1.打开浏览器，创建httpclent对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.输入网址，发起get请求，创建HTTPget对象
        HttpGet httpGet = new HttpGet("http://www.itcast.cn");
        //3.按回车，发起请求，返回响应，使用HTTP client对象发起请求
        CloseableHttpResponse response = httpClient.execute(httpGet);
        //4.解析响应，获取数据
        //判断状态码是否200
        if (response.getStatusLine().getStatusCode() == 200){
            //得到响应体的内容
            HttpEntity entity = response.getEntity();
            //使用工具类将内容转为字符串
            String content = EntityUtils.toString(entity, "utf8");
            System.out.println(content);
        }
    }
}
