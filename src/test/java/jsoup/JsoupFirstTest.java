package jsoup;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.File;
import java.net.URL;

/***
 * 虽然使用Jsoup可以替代HttpClient直接发起请求解析数据，
 * 但是往往不会这样用，因为实际的开发过程中，需要使用到多线程，连接池，代理等等方式，
 * 而jsoup对这些的支持并不是很好，所以我们一般把jsoup仅仅作为Html解析工具使用
 */
public class JsoupFirstTest {

    @Test
    public void testUrl() throws Exception {
        //解析url地址，第一个参数是访问的url,第二个参数是访问时候的超时时间
        Document doc = Jsoup.parse(new URL("http://www.itcast.cn"), 1000);
        //使用标签选择器，获取 title标签中的内容
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);

    }
    //对字符串的解析
    @Test
    public void testString() throws Exception{
        //使用工具类读取文件，获取字符串
        String content = FileUtils.readFileToString(new File("H:\\test.html"), "utf8");

        //解析字符串
        Document doc = Jsoup.parse(content);
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }
    @Test
    //对文件的解析
    public void testFile() throws Exception{
        Document doc = Jsoup.parse(new File("H:\\test.html"), "utf8");
        String title = doc.getElementsByTag("title").first().text();
        System.out.println(title);
    }

}
