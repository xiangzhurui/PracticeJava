package framework.spring.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import redis.clients.jedis.Jedis;

@Component
public class JedisUtils {

	@Value("poolConfig")
	private Jedis jedis;
	@Transactional(isolation=Isolation.SERIALIZABLE)
	void init(){
	}
	
	//	TODO getJedis
	
	public byte[] get(byte[] key){
		return jedis.get(key);
	}
}
