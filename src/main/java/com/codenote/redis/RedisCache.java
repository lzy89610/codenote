package com.codenote.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisCache implements Cache
{
	private static Cache redisCache;
	
	private JedisPool jedisPool;
	
	//private Jedis jedis;
	
	/**
	 * redis服务器ip地址
	 */
	private String ip;
	
	/**
	 * redis服务器端口号，默认为6379
	 */
	private int port = 6379;
	     
	/**
	 * redis最大空闲连接数，默认为10
	 */
	private int maxIdle = 10;
	     
	/**
	 * redis最大连接数
	 */
	private int maxTotal = 100;
	    
	/**
	 * redis最大等待时间 ，默认为3000
	 */
	private int maxWaitMillis = 3000;
	
	private RedisCache()
	{
		
		ip = "127.0.0.1";
		
		JedisPoolConfig config = new JedisPoolConfig();  
		config.setMaxIdle(maxIdle);
		config.setMaxTotal(maxTotal);
		config.setMaxWaitMillis(maxWaitMillis);
		JedisPool jedisPool = new JedisPool(config, ip, port);
		setJedisPool(jedisPool);
	}
	
	public static Cache getCache()
	{
		if (null == redisCache)
		{
			synchronized (RedisCache.class) 
			{
				if (null == redisCache) 
				{
					redisCache = new RedisCache();
				}
			}
		}
			  
		return redisCache;
	}
	
	/**
	 * 将String类型数据插入或更新到redis缓存中
	 * @param key  插入的键
	 * @param value 插入的值
	 * @author lizeyu
	 * @
	 */
	public void saveValue(String key, String value)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
			jedis.set(key, value);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
	}
	
	/**
	 * 从redis中获取String类型的值
	 * @param key  需要查询的键
	 * @author lizeyu
	 * @
	 */
	public String getValue(String key)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}	
		
		return jedis.get(key);
	}
	
	
	public List<String> mget(String... keys)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return jedis.mget(keys);
	}
	
	/**
	 * 将map类型数据插入或更新到redis缓存中
	 * @param key  插入的键
	 * @param mapData 插入的map
	 * @author lizeyu
	 * @
	 */
	public void saveMap(String key, Map<String, String> mapData)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
			jedis.hmset(key, mapData);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
	}
	
	/**
	 * 从redis中获取某键值对应的map
	 * @param key  需要查询的键
	 * @author lizeyu
	 * @
	 */
	
	public Map<String, String> getMap(String key)
	{
		Jedis jedis = null;
		Map<String, String> resultMap = null;
		try
		{
			jedis = getJedis(); 
			resultMap = (Map<String, String>) jedis.hgetAll(key);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return resultMap;
	}
	
	/**
	 * 从redis中获取某map中对应键的值
	 * @param key  需要查询的键
	 * @param mapKey  需要查询的map的键
	 * @author lizeyu
	 * @
	 */
	public String getMapValue(String key, String mapKey)
	{
		Jedis jedis = null;
		String value = null;
		try
		{
			jedis = getJedis(); 
			value = jedis.hget(key, mapKey);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return value;
	}
	
	/**
	 * 在redis中获取设置map中对应键的值
	 * @param key  需要查询的键
	 * @param mapKey  需要查询的map的键
	 * @author lizeyu
	 * @
	 */
	public void setMapValue(String key, String mapKey, String value)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
			jedis.hset(key, mapKey, value);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
	}
	
	
	public void delMapValue(String key, String... mapKeys)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
			jedis.hdel(key, mapKeys);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
	}
	
	
	public void lpush(String key, String... value)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
			jedis.lpush(key, value);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
	}
	
	
	public String lpop(String key)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return jedis.lpop(key);
	}
	
	
	public void rpush(String key, String... values)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
			jedis.rpush(key, values);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
	}
	
	
	public String rpop(String key)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return jedis.rpop(key);
	}
	
	
	public List<String> lrange(String key, long start, long end)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return jedis.lrange(key, start, end);
	}
	
	
	public String lindex(String key, long index)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return jedis.lindex(key, index);
	}
	
	
	public void sadd(String key, String... members)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
			jedis.sadd(key, members);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
	}
	
	
	public String spop(String key)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return jedis.spop(key);
	}
	
	
	public Set<String> smembers(String key)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return jedis.smembers(key);
	}
	
	
	public boolean sismember(String key, String member)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return jedis.sismember(key, member);
	}
	
	
	public Long srem(String key, String... members)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return jedis.srem(key, members);
	}
	
	
	public Long scard(String key)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return jedis.scard(key);
	}
	
	
	public Long zadd(String key, Double score, String member)
		
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return jedis.zadd(key, score, member);
	}
	
	
	public Long zadd(String key, Double score,
			Map<String, Double> scoreMembers) 
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		return jedis.zadd(key, scoreMembers);
	}
	
	
	public Long zcard(String key)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		return jedis.zcard(key);
	}
	
	
	public Long zrem(String key, String... members)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return jedis.zrem(key, members);
	}
	
	
	public Long zremrangeByRank(String key, long start, long end)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		return jedis.zremrangeByRank(key, start, end);
	}
	
	
	public Long zremrangeByScore(String key, double start, double end) 
		
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		return jedis.zremrangeByScore(key, start, end);
	}
	
	
	public Set<String> zrange(String key, long start, long end) 
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		return jedis.zrange(key, start, end);
	}
	
	
	public Set<String> zrevrange(String key, long start, long end)
			
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return jedis.zrevrange(key, start, end);
	}
	
	public Set<String> zrangeByScore(String key, double min, double max) 
		
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return jedis.zrangeByScore(key, min, max);
	}
	
	
	public Set<String> zrangeByScore(String key, String min, String max) 
		
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return jedis.zrangeByScore(key, min, max);
	}
	
	/**
	 * 从redis中删除某一些键值对应的值
	 * @param key  需要删除的键的集合
	 * @author lizeyu
	 * @
	 */
	
	public void delete(String... keys)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis();
			jedis.del(keys);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}	
	}
	
	
	public boolean isExist(String key, String mapKey)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}
		
		return jedis.hexists(key, mapKey);
	}

	
	public boolean isExist(String key)
	{
		Jedis jedis = null;
		try
		{
			jedis = getJedis();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}	
		
		return jedis.exists(key);
	}
	
	public long delKeysLike(String likeKey)
	{
		Jedis jedis = null;
		long count = 0;
		try
		{
	        jedis = getJedis();
	        Set<String> keys = jedis.keys(likeKey + "*");  
	        count = keys == null || keys.size() == 0 ? 0 : jedis.del(keys.toArray(new String[keys.size()]));  
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			releaseConnection(jedis);
		}

		return count;  
	}
	
	private Jedis getJedis()
	{
		Jedis jedis = null;
		try
		{
			jedis = jedisPool.getResource();
		}
		catch (Exception e)
		{
			if (jedis != null)
			{
				jedisPool.returnBrokenResource(jedis);
			}
			e.printStackTrace();
		}
		return jedis;
	}
	
	private void releaseConnection(Jedis jedis)
	{
		try
		{
			if(null != jedis)
			{
//				jedis.disconnect();
				jedisPool.returnResource(jedis);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	
	public Long llen(String key)
	{
		Long length = 0L;
		Jedis jedis = null;
		try
		{
			jedis = getJedis(); 
			length = jedis.llen(key);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}	
		return length;
	}
	
	public Set<String> zrevrangeByScore(String key,String max,String min)
	{
		Jedis jedis = null;
		Set<String> set = null;
		try
		{
			jedis = getJedis(); 
			set = jedis.zrevrangeByScore(key, max, min);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}	
		return set;
	}
	
	public Set<String> keys(String keyPattern)
	{
		Jedis jedis = null;
		Set<String> set = null;
		try
		{
			jedis = getJedis(); 
			set = jedis.keys(keyPattern);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			releaseConnection(jedis);
		}	
		return set;
	}
	
	public JedisPool getJedisPool()
	{
		return jedisPool;
	}

	public void setJedisPool(JedisPool jedisPool)
	{
		this.jedisPool = jedisPool;
	}
	
	public static void main(String[] args)
	{
		Cache cache = new RedisCache();
		cache.saveValue("hello", "world");
		String string = cache.getValue("hello");
		System.out.println(string);
		
		
	}
}
