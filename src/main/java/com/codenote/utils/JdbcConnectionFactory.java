package com.codenote.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnectionFactory
{

	/**
	 * 配置文件目录地址
	 */
	// private String propertyFileDir = "d:/db-conn-config.properties";
	// private String propertyFileDir = System.getProperty("user.dir") +
	// "/atetplatform/config/**/*-config.xml";
	private String propertyFileDir;

	/**
	 * 驱动
	 */
	private String driver;

	/**
	 * 连接字符串
	 */
	private String url;

	/**
	 * 数据库登录用户名称
	 */
	private String username;

	/**
	 * 数据库登录密码
	 */
	private String password;

	/**
	 * 需要连接的数据库名称
	 */
	private String dbName;

	/**
	 * 获取一个数据库连接工厂
	 */
	private static JdbcConnectionFactory connFactory = null;

	public static JdbcConnectionFactory getConnFactoryInstance(
			String propertyFileDir)
	{
		connFactory = new JdbcConnectionFactory(propertyFileDir);
		return connFactory;
	}

	private JdbcConnectionFactory()
	{

	}

	private JdbcConnectionFactory(String propertyFileDir, final String dbName)
	{
		try
		{
			this.dbName = dbName;
			this.propertyFileDir = propertyFileDir;
			initParams();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private JdbcConnectionFactory(String propertyFileDir)
	{
		try
		{
			this.propertyFileDir = propertyFileDir;
			initParams();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * 通过配置文件获取数据库连接参数
	 * 
	 * @throws IOException
	 */
	private void initParams() throws IOException
	{
		Properties properties = new Properties();

		InputStream in = new FileInputStream(new File(propertyFileDir));
		properties.load(in);

		getParams(properties);

	}

	/**
	 * 根据数据库名获取相应的参数
	 * 
	 * @param p
	 *            Properties
	 */
	private void getParams(Properties p)
	{
		// 注意：如果不用trim(),这样properties文件中的字符串后面不能有空格，否则会出错
		driver = p.getProperty(dbName + ".driver").trim();
		url = p.getProperty(dbName + ".url").trim();
		username = p.getProperty(dbName + ".username").trim();
		password = p.getProperty(dbName + ".password").trim();

	}

	/**
	 * 获取连接对象
	 * 
	 * @return
	 */
	public Connection getConnection()
	{
		Connection conn = null;

		try
		{
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			// 设置不自动提交
			conn.setAutoCommit(false);
		}
		catch (ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		return conn;
	}

	/**
	 * 关闭操作
	 * 
	 * @param conn
	 * @param pst
	 * @param rs
	 */
	public static void close(Connection conn, PreparedStatement pst,
			ResultSet rs)
	{
		try
		{
			if (rs != null)
			{
				rs.close();
			}
			if (pst != null)
			{
				pst.close();
			}
			if (conn != null)
			{
				conn.close();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getDriver()
	{
		return driver;
	}

	public void setDriver(String driver)
	{
		this.driver = driver;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public String getPropertyFileDir()
	{
		return propertyFileDir;
	}

	public void setPropertyFileDir(String propertyFileDir)
	{
		this.propertyFileDir = propertyFileDir;
	}

}