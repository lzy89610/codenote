package com.codenote.lucene;

/**
 * 实体： 文章
 * 
 * @author tyg
 * 
 */
public class Article
{
	private Integer id;
	private String title;
	private String content;

	public Article()
	{
		super();
	}

	public Article(Integer id, String title, String content)
	{
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	@Override
	public String toString()
	{
		return id + " " + title + " " + content;
	}

}
