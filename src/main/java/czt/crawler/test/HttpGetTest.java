package czt.crawler.test;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/***
 * httpget请求学习
 * 在uti
 */
public class HttpGetTest {

    public static void main(String[] args) {
        //1.创建客户端httpclients对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.创建get请求，httpget
        /**
         * 使用post请求时，只需要改这行代码
         * HttpPost post = new HttpPost(uri);
          */
        HttpGet get = new HttpGet("http://www.itcast.cn");

        //配置请求信息
        RequestConfig config = RequestConfig.custom().setConnectTimeout(1000)  //创建链接的最长时间，单位是毫秒
                .setConnectionRequestTimeout(500)  //设置获取链接的最长时间，单位是毫秒
                .setSocketTimeout(10*1000) //设置数据传输的最长时间，单位是毫秒
                .build();

        //给请求设置设置信息
        get.setConfig(config);
        //3.发送请求，得到response响应
        CloseableHttpResponse response = null;
        try {
            //try块里面即使一块小的作用域，里面生命的变量是局部变量
             response = httpClient.execute(get);
            //4.解析响应
            if (response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                System.out.println(content.length());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭资源
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
