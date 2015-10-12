package duqu;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

import org.apache.poi.hslf.model.TextRun;
import org.apache.poi.hslf.usermodel.RichTextRun;
import org.apache.poi.hslf.usermodel.SlideShow;
/**
 * <p>类名：PptReader
 * <p>作用：读取PPT
 * <p>@author :born to try
 */
public class PptReader {
 public  String doPPTtoImage(String file) {
  String text = "";
  String str;
  
  try {
	  str = URLEncoderHZ.encode(file, "utf-8");
	  URL url = new URL(str);
	  InputStream is = url.openStream();
  // FileInputStream is = new FileInputStream(file);
      SlideShow ppt = new SlideShow(is);
      is.close();
      org.apache.poi.hslf.model.Slide[] slide = ppt.getSlides();
      for (int i = 0; i < slide.length&&i<2; i++) {
    //   System.out.print("第" + (i+1) + "页。");
       if(slide[i].getNotesSheet()!=null&&slide[i].getNotesSheet().getTextRuns()!=null){
     //获取第一个备注
       }
       TextRun[] truns = slide[i].getTextRuns();
       for (int k = 0; k < truns.length; k++) {
       RichTextRun[] rtruns = truns[k].getRichTextRuns();
       for (int l = 0; l < rtruns.length; l++) {
       rtruns[l].setFontIndex(1);
       rtruns[l].setFontName("宋体"); 
       text+=rtruns[l].getText();
       
     }
       
    }
      
   }
  // System.out.println("ok");
  } catch (FileNotFoundException e) {
  } catch (IOException e) {
   e.printStackTrace();
  }
  return text;
 }

 
}