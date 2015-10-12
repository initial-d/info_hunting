package Filter;

import java.io.IOException;
import java.util.BitSet;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.search.Filter;
/**
 * <p>类名：tupianFilter
 * <p>作用：图片过滤器
 * <p>@author :born to try
 */
public class tupianFilter extends Filter{ 
	 public BitSet bits(IndexReader reader) throws IOException {  
		  BitSet bitSet = new BitSet(reader.maxDoc());
		  bitSet.set(0, bitSet.size()-1,false);//设置位队列的每一位都问TRUE
		  Term term = new Term("kind","jpg");
		  TermDocs termDocs = reader.termDocs(term);//从索引中取出含term的文档
		  while(termDocs.next()){
		  bitSet.set(termDocs.doc(), true);
		  }	 
		  Term term1=new Term("kind","gif");
		  TermDocs termDocs1 = reader.termDocs(term1);//从索引中取出含term的文档
		  while(termDocs1.next()){
		  bitSet.set(termDocs1.doc(), true);
		  }	 
		  return bitSet;
		 }
}

