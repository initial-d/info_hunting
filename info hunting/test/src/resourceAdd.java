import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
/**
 * <p>类名：ResourceAdd
 * <p>作用：添加资源记录到test.xml中
 * <p>@author  born to try
 */
public class resourceAdd {
	private static Document doc = null;
	private static Element root = null;
	private static  Element resourceitem = null;
	  /**    
	   *<br>函数名：addResourceitem     
	   *<br>作  用： 建立对test.xml的解析，并添加resourceitem元素节点
	   *<br>参  数：无
	   *<br>返回类型：无 
	   */
	public static  String addResourceitem() throws DocumentException {
		// 得到test.xml的存放地址
		String path = "F:\\s";
		// test.xml的访问路径
		String filepath = path + "\\" + "test.xml";
		
		//构建一个 解析器
		SAXReader xmlReader = new SAXReader();
		doc = xmlReader.read(filepath);
		root = doc.getRootElement();
		resourceitem = root.addElement("resourceitem");
		return "success";
	}
	// 为resourceitem添加id元素节点
	public static void addID(String idText) {
		Element titleNode = resourceitem.addElement("id");
		titleNode.addText(idText);
	}
	// 为resourceitem添加title元素节点
	public static void addTitle(String titleText) {
		Element titleNode = resourceitem.addElement("title");
		titleNode.addText(titleText);
	}
	// 为resourceitem添加keywords元素节点
	public static void addKeyWords(String keywords) {
		Element keyWordsNode = resourceitem.addElement("keywords");
		keyWordsNode.addText(keywords);
	}
	// 为resourceitem添加kind元素节点
	public static void addKind(String kind) {
		Element kindNode = resourceitem.addElement("kind");
		kindNode.addText(kind);
	}
	// 为resourceitem添加describe元素节点
	public static void addDescribe(String describe) {
		Element describeNode = resourceitem.addElement("describe");
		describeNode.addText(describe);
	}
	// 为resourceitem添加date元素节点
	public static void addDate(String date) {
		Element dateNode = resourceitem.addElement("date");
		dateNode.addText(date);
	}
	// 为resourceitem添加url元素节点
	public static void addUrl(String url) {
		Element urlNode = resourceitem.addElement("url");
		urlNode.addText(url);
	}
	// 为resourceitem添加author元素节点
	public static void addAuthor(String author) {
		Element authorNode = resourceitem.addElement("author");
		authorNode.addText(author);
	}
	// 为resourceitem添加publisher元素节点
	public static void addpublisher(String publish) {
		Element publishNode = resourceitem.addElement("publisher");
		publishNode.addText(publish);
	}
	public static void resouceWrite(String newfilename) throws IOException{
		//格式化输出,类型IE浏览一样
		OutputFormat format = OutputFormat.createPrettyPrint();
		// 指定XML字符集编码 
		format.setEncoding("GBK");
		XMLWriter writer = new XMLWriter(new FileOutputStream(new File(newfilename)));
		writer.write(doc);
		writer.close();
	}
	public  void fuck(String id,String title,String kind,String key,String dec, String  date,String url,String author,String pub) 
	{
		try {
			addResourceitem();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addID(id);
		addTitle(title);
		addKeyWords(key);
		addKind(kind);
		addDescribe(dec);
		addDate(date);
		addUrl(url);
		addAuthor(author);
		addpublisher(pub);
		try {
			resouceWrite("F:/s/test.xml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}