package duqu;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.poi.hslf.extractor.PowerPointExtractor;
import org.apache.poi.hssf.extractor.ExcelExtractor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * <p>类名：Extractor
 * <p>作用：读取文档内容
 * <p>@author :born to try
 */
public class Extractor {
	//根据word文件的url收取出word文件的内容
	public String getWord(String path) {
		String str;
		String text = null;
		try {
			str = URLEncoderHZ.encode(path, "utf-8");
			System.out.println(str);
			URL url = new URL(str);
			InputStream inp = url.openStream();
			WordExtractor extractor = new WordExtractor(
					new POIFSFileSystem(inp));
			text = extractor.getText();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(text.trim().length()<200){
			return text.trim();	
		}
		return text.trim();
		
	}


}
