package com.codenote.lucene;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;


public class TestSearch
{

	public static void main(String[] args) throws IOException, ParseException
	{
		String queryString = "hello";
		
		Directory directory = FSDirectory.open(new File("./indexDirwhy1/"));
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_30);
		
		QueryParser queryParser = new QueryParser(Version.LUCENE_30, "title", analyzer);
		Query query = queryParser.parse(queryString);
	
		IndexSearcher indexSearcher = new IndexSearcher(directory);
		TopDocs topDocs = indexSearcher.search(query, 100);
		
		int count = topDocs.totalHits; // 匹配到的结果数量
		ScoreDoc[] scoreDocs = topDocs.scoreDocs; // 前n条结果的信息
		
		List<Article> list = new ArrayList<Article>();
		
		for(ScoreDoc s : scoreDocs)
		{
			System.out.println("here" + s.doc);
			System.out.println(s.score);
			Document doc = indexSearcher.doc(s.doc);
			
			Article article = new Article();
			article.setId(Integer.parseInt(doc.get("id"))); // 需要转换一下类型，把String型转为Integer
			article.setTitle(doc.get("title"));
			article.setContent(doc.get("content")); // doc.getField("content").stringValue()

			System.out.println(article);
		}
		
		indexSearcher.close();
		

	}

}
