package Filter;

import java.io.IOException;
import java.util.BitSet;

import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.index.TermDocs;
import org.apache.lucene.search.Filter;
/**
 * <p>������tupianFilter
 * <p>���ã�ͼƬ������
 * <p>@author :born to try
 */
public class tupianFilter extends Filter{ 
	 public BitSet bits(IndexReader reader) throws IOException {  
		  BitSet bitSet = new BitSet(reader.maxDoc());
		  bitSet.set(0, bitSet.size()-1,false);//����λ���е�ÿһλ����TRUE
		  Term term = new Term("kind","jpg");
		  TermDocs termDocs = reader.termDocs(term);//��������ȡ����term���ĵ�
		  while(termDocs.next()){
		  bitSet.set(termDocs.doc(), true);
		  }	 
		  Term term1=new Term("kind","gif");
		  TermDocs termDocs1 = reader.termDocs(term1);//��������ȡ����term���ĵ�
		  while(termDocs1.next()){
		  bitSet.set(termDocs1.doc(), true);
		  }	 
		  return bitSet;
		 }
}

