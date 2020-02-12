package czt.crawler.test;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/***
 * httpPost 请求学习
 * 在uti中通过post 带参数
 */
public class HttpPostParamTest {

    public static void main(String[] args) throws UnsupportedEncodingException {
        //1.创建客户端httpclients对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //2.创建post请求
        HttpPost post = new HttpPost("http://yun.itheima.com/search");

        //声明List集合，封装表单中的参数
        List<NameValuePair> params = new ArrayList<NameValuePair>();
        //设置请求的地址的参数
        params.add(new BasicNameValuePair("so","java"));
        params.add(new BasicNameValuePair("type","course"));
        params.add(new BasicNameValuePair("realhash","6666cd76f96956469e7be39d750cc7d9_65e8794ac8cec0d20e1db0e8cc94b1ac"));

        //创建表单的entity对象，第一个参数就是封装好的表单数据，第二个参数是编码
        UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(params,"utf8");
        //设置表单的Entity对象到post 请求中
        post.setEntity(formEntity);

        //3.发送请求，得到response响应
        CloseableHttpResponse response = null;
        try {
            //try块里面即使一块小的作用域，里面生命的变量是局部变量
             response = httpClient.execute(post);
            //4.解析响应
            if (response.getStatusLine().getStatusCode() == 200){
                String content = EntityUtils.toString(response.getEntity(), "utf8");
                System.out.println(content);
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
