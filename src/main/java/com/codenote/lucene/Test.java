package com.codenote.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriter.MaxFieldLength;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class Test
{

	public static void main(String[] args) throws IOException
	{
		Article article = new Article();
		article.setContent("hello world ni hao lucene");
		article.setId(1);
		article.setTitle("hello lucene");
		
		Directory directory = FSDirectory.open(new File("./indexDirwhy1/"));
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30); 
		
		Document doc = new Document();
		doc.add(new Field("id", article.getId().toString(), Store.YES, Index.NOT_ANALYZED));
		doc.add(new Field("title", article.getTitle(), Store.YES, Index.ANALYZED));
		doc.add(new Field("content", article.getContent(), Store.YES, Index.ANALYZED));
		
		IndexWriter indexWriter = new IndexWriter(directory, analyzer, MaxFieldLength.LIMITED);
		indexWriter.addDocument(doc);
		indexWriter.close();

	}

}
