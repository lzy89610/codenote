package com.codenote.utils.lucene;

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

import com.codenote.lucene.Article;

public class LuceneWriter
{
	private Directory directory;
	
	private Analyzer analyzer;
	
	private IndexWriter indexWriter;
	
	public LuceneWriter(String indexFileDir)
	{
		try
		{
			directory = FSDirectory.open(new File(indexFileDir));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		analyzer = new StandardAnalyzer(Version.LUCENE_30); 
	}
	
	public void write(Article article)
	{
		Document doc = new Document();
		doc.add(new Field("id", article.getId().toString(), Store.YES, Index.NOT_ANALYZED));
		doc.add(new Field("title", article.getTitle(), Store.YES, Index.ANALYZED));
		doc.add(new Field("content", article.getContent(), Store.YES, Index.ANALYZED));
		
		try
		{
			indexWriter = new IndexWriter(directory, analyzer, MaxFieldLength.LIMITED);
			indexWriter.addDocument(doc);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		
	}
	
	public void close()
	{
		try
		{
			if (indexWriter != null)
			{
				indexWriter.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	

}
