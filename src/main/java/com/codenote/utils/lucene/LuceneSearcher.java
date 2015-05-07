package com.codenote.utils.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import com.codenote.lucene.Article;

public class LuceneSearcher
{
	private Directory directory;
	
	private Analyzer analyzer;
	
	private IndexSearcher indexSearcher;
	
	public LuceneSearcher(String indexFileDir)
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
	
	public List<Article> search(String filedName, String queryString)
	{
		QueryParser queryParser = new QueryParser(Version.LUCENE_30, filedName, analyzer);
		List<Article> list = new ArrayList<Article>();
		Query query;
		TopDocs topDocs;
		
		try
		{
			query = queryParser.parse(queryString);
			indexSearcher = new IndexSearcher(directory);
			topDocs = indexSearcher.search(query, 100);
			//int count = topDocs.totalHits; // 匹配到的结果数量
			ScoreDoc[] scoreDocs = topDocs.scoreDocs; // 前n条结果的信息
			
			for(ScoreDoc s : scoreDocs)
			{
				Document doc = indexSearcher.doc(s.doc);
				
				Article article = new Article();
				article.setId(Integer.parseInt(doc.get("id"))); // 需要转换一下类型，把String型转为Integer
				article.setTitle(doc.get("title"));
				article.setContent(doc.get("content")); // doc.getField("content").stringValue()
				//System.out.println(article);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			close();
		}
		
		return list;
	}
	
	public void close()
	{
		try
		{
			if (indexSearcher != null)
			{
				indexSearcher.close();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
}
