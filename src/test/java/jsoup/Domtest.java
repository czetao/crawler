package jsoup;

import org.apache.commons.lang3.builder.ToStringExclude;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;

/***
 * 1.	根据id查询元素getElementById
 * 2.	根据标签获取元素getElementsByTag
 * 3.	根据class获取元素getElementsByClass
 * 4.	根据属性获取元素getElementsByAttribute
 */
public class Domtest {

    @Test
    public void testDom() throws Exception{
        Document doc = Jsoup.parse(new File("H:\\test.html"), "utf8");
        //1.	根据id查询元素getElementById
        Element element = doc.getElementById("city_bj");
        //2.	根据标签获取元素getElementsByTag
        Element div = doc.getElementsByTag("div").first();
        //3.	根据class获取元素getElementsByClass
        Element classes = doc.getElementsByClass("class_a").first();
        //4.	根据属性获取元素getElementsByAttribute
        Element abc = doc.getElementsByAttribute("href").first();


        System.out.println(abc.text());
    }

    @Test
    //元素中获取数据
    public void testData() throws Exception{
        Document doc = Jsoup.parse(new File("H:\\test.html"), "utf8");
        Element element = doc.getElementById("test");
        String str = "";
        //1.从元素中获取id
        str = element.id();

        //2.从元素中获取className
        str = element.className();

        //3.从元素中获取属性的值attr
        str = element.attr("id");
        str = element.attr("class");

        //4.从元素中获取所有属性attributes
        Attributes attributes = element.attributes();
        System.out.println(attributes.toString());

        //5.从元素中获取文本内容
        str = element.text();
        System.out.println(str);
    }

    /***
     * 选择器的使用
     * @throws Exception
     */
    @Test
    public void testSelector() throws Exception{

        //解析html文件，获取document对象
        Document document = Jsoup.parse(new File("H:\\test.html"), "utf8");
        //tagname: 通过标签查找元素，比如：span
        Elements span = document.select("span");
        for (Element element : span) {
            System.out.println(element.text());
        }

//#id: 通过ID查找元素，比如：#city_bjj
        String str = document.select("#city_bj").text();

//.class: 通过class名称查找元素，比如：.class_a
        str = document.select(".class_a").text();

//[attribute]: 利用属性查找元素，比如：[abc]
        str = document.select("[abc]").text();

//[attr=value]: 利用属性值来查找元素，比如：[class=s_name]
        str = document.select("[class=s_name]").text();

    }

    /***
     * 元素选择器，多个组合使用练习
     */
    @Test
    public void testSelector2() throws Exception{
        //解析html文件，获取document对象
        Document document = Jsoup.parse(new File("H:\\test.html"), "utf8");
        //元素 + ID
        Elements select = document.select("h3#city_bj");
        //元素 + class  比如 li.class
        //元素 + 属性名  span[abc]

        //查找某个元素下子元素，比如：.city_con li 查找"city_con"下的所有li
        Elements elements = document.select(".city_con li");
        for (Element element : elements) {
            System.out.println(element.text());
        }

        //parent > child: 查找某个父元素下的直接子元素，
//比如：.city_con > ul > li 查找city_con第一级（直接子元素）的ul，再找所有ul下的第一级li
        String text = document.select(".city_con > ul > li").text();

        //parent > * 查找某个父元素下所有直接子元素.city_con > *
        text = document.select(".city_con > *").text();


        // System.out.println(select.text());
    }

}
