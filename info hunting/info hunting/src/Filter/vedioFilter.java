package Filter;

import java.io.IOException;
import java.util.BitSet;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.search.Filter;
/**
 * <p>类名：vedioFilter
 * <p>作用：视频过滤器
 * <p>@author :born to try
 */
public class vedioFilter extends Filter{ 
	 public BitSet bits(IndexReader reader) throws IOException {  
		  BitSet bitSet = new BitSet(reader.maxDoc());
		  bitSet.set(0, bitSet.size()-1,false);//设置位队列的每一位都问TRUE
		  Term term = new Term("kind","mp4");
		  TermDocs termDocs = reader.termDocs(term);//从索引中取出含term的文档
		  while(termDocs.next()){
		  bitSet.set(termDocs.doc(), true);
		  }	 
		  Term term1=new Term("kind","flv");
		  TermDocs termDocs1 = reader.termDocs(term1);//从索引中取出含term的文档
		  while(termDocs1.next()){
		  bitSet.set(termDocs1.doc(), true);
		  }	 
		  Term term2=new Term("kind","rmvb");
		  TermDocs termDocs2 = reader.termDocs(term2);//从索引中取出含term的文档
		  while(termDocs2.next()){
		  bitSet.set(termDocs2.doc(), true);
		  }	
		  Term term3=new Term("kind","avi");
		  TermDocs termDocs3 = reader.termDocs(term3);//从索引中取出含term的文档
		  while(termDocs3.next()){
		  bitSet.set(termDocs3.doc(), true);
		  }	 
		  Term term4=new Term("kind","asf");
		  TermDocs termDocs4 = reader.termDocs(term4);//从索引中取出含term的文档
		  while(termDocs4.next()){
		  bitSet.set(termDocs4.doc(), true);
		  }	 
		  Term term5=new Term("kind","swf");
		  TermDocs termDocs5 = reader.termDocs(term5);//从索引中取出含term的文档
		  while(termDocs5.next()){
		  bitSet.set(termDocs5.doc(), true);
		  }	 
		  Term term6=new Term("kind","rm");
		  TermDocs termDocs6 = reader.termDocs(term6);//从索引中取出含term的文档
		  while(termDocs6.next()){
		  bitSet.set(termDocs6.doc(), true);
		  }	 
		  return bitSet;
		 }
}

