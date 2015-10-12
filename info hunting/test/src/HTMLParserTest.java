import java.io.*;

import org.htmlparser.filters.*;
import org.htmlparser.*;
import org.htmlparser.nodes.*;
import org.htmlparser.tags.*;
import org.htmlparser.util.*;
import org.htmlparser.visitors.*;
import java.io.File;
import java.util.ArrayList;

public class HTMLParserTest {
	private static ArrayList<String> filelist = new ArrayList<String>();
	static String filePath;
	static String name;

	static void getFiles(String filePath) {
		File root = new File(filePath);
		File[] files = root.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				/*
				 * 递归调用
				 */
				getFiles(file.getAbsolutePath());
				filelist.add(file.getAbsolutePath());
				File directory = new File(file.getAbsolutePath());
				File[] htmlFiles = directory.listFiles(new FileNameSelector(
						"html"));
				// System.out.println("\n目录"+directory.getName()+"下的.html文件");
				for (File file1 : htmlFiles) {
					name = file.getAbsolutePath() + "/" + file1.getName();
					System.out.println(file.getAbsolutePath() + "/"
							+ file1.getName());
					String path = file.getAbsolutePath() + "/"
							+ file1.getName();
					StringBuffer sbStr = new StringBuffer();
					BufferedReader reader = null;
					try {
						reader = new BufferedReader(new FileReader(new File(
								path)));
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String temp = "";
					try {
						while ((temp = reader.readLine()) != null) {
							sbStr.append(temp);
							sbStr.append("");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						reader.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					String result = sbStr.toString();
					// readAll(result);
					// readByHtml(result);
					try {
						readTextAndTitle(result);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				// System.out.println(file.getAbsolutePath());
			} else {
				// System.out.println(file.getAbsolutePath());
			}
		}
	}

	public static void main(String args[]) throws Exception {
		filePath = "F:/workspace/MyHeritrix/jobs/sdufe-20130414121132012/mirror";
		getFiles(filePath);

	}

	// 按页面方式处理.解析标准的html页面
	public static void readByHtml(String content) throws Exception {
		Parser myParser;
		myParser = Parser.createParser(content, "GB2312");

		HtmlPage visitor = new HtmlPage(myParser);

		myParser.visitAllNodesWith(visitor);

		String textInPage = visitor.getTitle();
		System.out.println(textInPage);
		NodeList nodelist;
		nodelist = visitor.getBody();
		System.out.print(nodelist.asString().trim());

	}

	// 读取文本内容和标题
	public static void readTextAndTitle(String result) throws Exception {
		resourceAdd add = new resourceAdd();
		Parser parser;
		NodeList nodelist;
		parser = Parser.createParser(result, "GB2312");
		NodeFilter textFilter = new NodeClassFilter(TextNode.class);
		NodeFilter titleFilter = new NodeClassFilter(TitleTag.class);
		OrFilter lastFilter = new OrFilter();
		lastFilter.setPredicates(new NodeFilter[] { textFilter, titleFilter });
		nodelist = parser.parse(lastFilter);
		Node[] nodes = nodelist.toNodeArray();
		String line = "暂无内容";
		Node node;
		String[] tmp = new String[9];
		int q = 0;
		int num = 0;
		for (int i = 0; i < nodes.length && i < 9; i++) {
			node = nodes[i];
			if (node instanceof TextNode) {
				TextNode textnode = (TextNode) node;
				line = textnode.getText();
			} else if (node instanceof TitleTag) {
				TitleTag titlenode = (TitleTag) node;
				line = titlenode.getTitle();
			}
			if (isTrimEmpty(line))
				continue;

			System.out.println(line);
			if (line.isEmpty())
				continue;
			else
				num++;
			if (line.length() > 10)
				line = line.substring(0, 9);
			tmp[q++] = line;
		}
		if (num < 8)
			return;
		else {

			tmp[5] = "2013-04-18";
			tmp[2] = "html";
			tmp[7] = "duyimin";
			tmp[8] = "暂无";
			tmp[0] = "1000";
			String a = "http://192.168.0.9:8080/resources/"
					+ name.substring(53);
			char[] b = new char[100];
			b = a.toCharArray();
			for (int i = 0; i < a.length(); i++) {
				if (b[i] == '\\')
					b[i] = '/';
			}
			tmp[6] = String.valueOf(b);
			add.fuck(tmp[0], tmp[1], tmp[2], tmp[3], tmp[4], tmp[5], tmp[6],
					tmp[7], tmp[8]);

		}

	}

	// 分别读纯文本和链接

	/*
	 * public static void readTextAndLink(String result) throws Exception {
	 * Parser parser; NodeList nodelist; parser =
	 * Parser.createParser(result,"GB2312"); NodeFilter textFilter = new
	 * NodeClassFilter(TextNode.class); NodeFilter linkFilter = new
	 * NodeClassFilter(LinkTag.class); OrFilter lastFilter = new OrFilter();
	 * lastFilter.setPredicates(new NodeFilter[] { textFilter, linkFilter });
	 * nodelist = parser.parse(lastFilter); Node[] nodes =
	 * nodelist.toNodeArray(); String line =""; for(int i=0;i<nodes.length;i++)
	 * { Node node = nodes[i]; if(node instanceof TextNode) { TextNode textnode
	 * = (TextNode) node; line = textnode.getText(); } else if(node instanceof
	 * LinkTag) { LinkTag link = (LinkTag)node; line = link.getLink(); } if
	 * (isTrimEmpty(line)) continue; System.out.println(line); } }
	 */

	/*
	 * public static void readAll(String result) throws Exception { Parser
	 * parser; Node[] nodes ; parser = Parser.createParser(result,"GB2312");
	 * 
	 * 
	 * nodes = parser.extractAllNodesThatAre(TextNode.class);
	 * 
	 * //读取所有的内容节点 for (int i = 0; i < nodes.length; i++) { TextNode textnode =
	 * (TextNode) nodes[i]; String line = textnode.toPlainTextString().trim();
	 * if (line.equals("")) continue; System.out.println(line); } }
	 */
	/**
	 * 去掉左右空格后字符串是否为空
	 */
	public static boolean isTrimEmpty(String astr) {
		if ((null == astr) || (astr.length() == 0)) {
			return true;
		}
		if (isBlank(astr.trim())) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串是否为空:null或者长度为0.
	 */
	public static boolean isBlank(String astr) {
		if ((null == astr) || (astr.length() == 0)) {
			return true;
		} else {
			return false;
		}
	}

}
