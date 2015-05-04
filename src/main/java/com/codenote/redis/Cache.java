package com.codenote.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;


public interface Cache
{
	/**
	 * 将String类型数据插入或更新到redis缓存中
	 * @param key  插入的键
	 * @param value 插入的值
	 * @author lizeyu
	 * @
	 */
	void saveValue(String key, String value) ;
	
	/**
	 * 从redis中获取String类型的值
	 * @param key  需要查询的键
	 * @author lizeyu
	 * @
	 */
	String getValue(String key) ;
	
	/**
	 * 将map类型数据插入或更新到redis缓存中
	 * @param key  插入的键
	 * @param mapData 插入的map
	 * @author lizeyu
	 * @
	 */
	void saveMap(String key, Map<String, String> mapData) ;
	
	/**
	 * 从redis缓存中获取一个Map
	 * @param key  需要获取的Map的key
	 * @return Map<String,String>
	 * @author lizeyu
	 * @
	 */
	Map<String,String> getMap(String key) ;
	
	/**
	 * 从redis中获取某map中对应键的值
	 * @param key  需要查询的键
	 * @param mapKey  需要查询的map的键
	 * @author lizeyu
	 * @
	 */
	String getMapValue(String key, String mapKey) ;
	
	/**
	 * 在redis中获取设置map中对应键的值
	 * @param key  需要查询的键
	 * @param mapKey  需要查询的map的键
	 * @author lizeyu
	 * @
	 */
	void setMapValue(String key, String mapKey, String value) ;
	

	/**
	 * 在redis中删除指定map中对应键的值
	 * @param key  需要操作的键
	 * @param mapKey  需要删除的值对应于map中的键
	 * @author zengxianwu
	 * @
	 */
	void delMapValue(String key, String... mapKey) ;
	
	/**
	 * 从redis中删除某一些键值对应的值
	 * @param key  需要删除的键的集合
	 * @author lizeyu
	 * @
	 */
	void delete(String... keys) ;
	
	/**
	 * 判断redis缓存中一个key_mapKey是否存在
	 * @param key  需要判断存在与否的Map的key及其mapKey
	 * @return boolean 存在返回true,不存在返回false
	 * @author zengxianwu
	 * @
	 */
	boolean isExist(String key,String mapKey) ;
	
	/**
	 * 判断redis缓存中一个key对应value是否存在
	 * @param key  需要判断的value存在与否的key
	 * @return boolean 存在返回true,不存在返回false
	 * @author zengxianwu
	 * @
	 */
	boolean isExist(String key) ;

	void lpush(String key, String... value) ;

	String lpop(String key) ;

	void rpush(String key, String... values) ;
	
	Long llen(String key) ;

	String rpop(String key) ;

	List<String> lrange(String key, long start, long end)
			;

	String lindex(String key, long index) ;

	void sadd(String key, String... members) ;

	String spop(String key) ;

	Set<String> smembers(String key) ;

	Long srem(String key, String... members) ;

	Long scard(String key) ;

	Long zadd(String key, Double score, String member) ;

	Long zadd(String key, Double score, Map<String, Double> scoreMembers)
			;

	Long zcard(String key) ;

	Long zrem(String key, String... members) ;

	Long zremrangeByRank(String key, long start, long end)
			;

	Long zremrangeByScore(String key, double start, double end)
			;

	Set<String> zrange(String key, long start, long end)
			;
	Set<String> zrevrange(String key, long start, long end)
	;
	List<String> mget(String... keys) ;

	Set<String> zrangeByScore(String key, double min, double max)
			;

	Set<String> zrangeByScore(String key, String min, String max)
			;
	/** 
     * 删除模糊匹配的key 
     * @param likeKey 模糊匹配的key 
     * @return 删除成功的条数 
     */  
    public long delKeysLike(final String likeKey);

	boolean sismember(String key, String member) ;

	Set<String> zrevrangeByScore(String key, String max, String min)
			;

	Set<String> keys(String keyPattern) ;
}
