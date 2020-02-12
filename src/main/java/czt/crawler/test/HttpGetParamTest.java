package czt.crawler.test;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;

/***
 * httpget请求2
 * 在url中带有参数的路径 http://yun.itheima.com/search?keys=Java
 */
public class HttpGetParamTest {

    public static void main(String[] args) throws URISyntaxException {
        //1.创建客户端httpclients对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.创建get请求，httpget
        //通过Uribuilder 创建uri
        URIBuilder uriBuilder = new URIBuilder("http://yun.itheima.com/search");
        uriBuilder.setParameter("keys","Java" );
        HttpGet get = new HttpGet(uriBuilder.build());
        System.out.println("发送的请求是"+get);
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
