package Path;
import java.io.FileOutputStream;
import java.io.PrintStream;
import Path.RealPath;
import Distributed.*;
/**
 * <p>������WriteIPlist
 * <p>���ã���Զ�̵�¼��������IPд��iplist.dat�ļ�
 * <p>@author: born to try
 */

public class WriteIPlist {
	/**    
	*<br>��������go  
	*<br>��  �ã��Ӷ�̬�����л�ȡ�ѵ�½��������IP����д��.dat�ļ�
	*<br>��  ������
	*<br>�������ͣ�void
	*/
	   public static void go(){
		    FileOutputStream out;   
		    PrintStream print;
            try { 
 	              out = new FileOutputStream(RealPath.Path+"SQL\\iplist.data");   
	              print = new PrintStream( out );   
	              for(int j=0;j<IPlist.iplist.size();j++)
	                 {
	                   print.println (IPlist.iplist.get(j));   
	                  }
	              print.close();   
	            } catch (Exception e) {   
	        System.err.println ("��IPд���б�ʧ��");   
	       }   
	   }
}  