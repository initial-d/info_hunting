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
 * <p>������ResourceAdd
 * <p>���ã������Դ��¼��test.xml��
 * <p>@author  born to try
 */
public class resourceAdd {
	private static Document doc = null;
	private static Element root = null;
	private static  Element resourceitem = null;
	  /**    
	   *<br>��������addResourceitem     
	   *<br>��  �ã� ������test.xml�Ľ����������resourceitemԪ�ؽڵ�
	   *<br>��  ������
	   *<br>�������ͣ��� 
	   */
	public static  String addResourceitem() throws DocumentException {
		// �õ�test.xml�Ĵ�ŵ�ַ
		String path = "F:\\s";
		// test.xml�ķ���·��
		String filepath = path + "\\" + "test.xml";
		
		//����һ�� ������
		SAXReader xmlReader = new SAXReader();
		doc = xmlReader.read(filepath);
		root = doc.getRootElement();
		resourceitem = root.addElement("resourceitem");
		return "success";
	}
	// Ϊresourceitem���idԪ�ؽڵ�
	public static void addID(String idText) {
		Element titleNode = resourceitem.addElement("id");
		titleNode.addText(idText);
	}
	// Ϊresourceitem���titleԪ�ؽڵ�
	public static void addTitle(String titleText) {
		Element titleNode = resourceitem.addElement("title");
		titleNode.addText(titleText);
	}
	// Ϊresourceitem���keywordsԪ�ؽڵ�
	public static void addKeyWords(String keywords) {
		Element keyWordsNode = resourceitem.addElement("keywords");
		keyWordsNode.addText(keywords);
	}
	// Ϊresourceitem���kindԪ�ؽڵ�
	public static void addKind(String kind) {
		Element kindNode = resourceitem.addElement("kind");
		kindNode.addText(kind);
	}
	// Ϊresourceitem���describeԪ�ؽڵ�
	public static void addDescribe(String describe) {
		Element describeNode = resourceitem.addElement("describe");
		describeNode.addText(describe);
	}
	// Ϊresourceitem���dateԪ�ؽڵ�
	public static void addDate(String date) {
		Element dateNode = resourceitem.addElement("date");
		dateNode.addText(date);
	}
	// Ϊresourceitem���urlԪ�ؽڵ�
	public static void addUrl(String url) {
		Element urlNode = resourceitem.addElement("url");
		urlNode.addText(url);
	}
	// Ϊresourceitem���authorԪ�ؽڵ�
	public static void addAuthor(String author) {
		Element authorNode = resourceitem.addElement("author");
		authorNode.addText(author);
	}
	// Ϊresourceitem���publisherԪ�ؽڵ�
	public static void addpublisher(String publish) {
		Element publishNode = resourceitem.addElement("publisher");
		publishNode.addText(publish);
	}
	public static void resouceWrite(String newfilename) throws IOException{
		//��ʽ�����,����IE���һ��
		OutputFormat format = OutputFormat.createPrettyPrint();
		// ָ��XML�ַ������� 
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