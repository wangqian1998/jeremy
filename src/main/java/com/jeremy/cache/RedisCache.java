package com.jeremy.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.util.SerializationUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class RedisCache {
    private static Logger logger = LoggerFactory.getLogger(RedisCache.class);
    private static Map<String, Object> map = new HashMap<>();
    private JedisPool pool = null;

    @Value("spring.redis.host")
    private String host;

    @Value("spring.redis.port")
    private Integer port;

    @PostConstruct
    public void init() {
        try {
            Properties props = new Properties();
            pool = new JedisPool(host, port);
        } catch (Exception e) {
            logger.error("init redis failed", e);
        }
    }

    public <T> T get(String key) {
        Jedis jedis = null;
        try {
            if (pool == null) {
                return null;
            }
            jedis = pool.getResource();
            if (jedis != null) {
                byte[] bytes = jedis.get(SerializationUtils.serialize(key));
                if (bytes != null) {
                    logger.debug("hit cache, " + key);
                    return (T) SerializationUtils.deserialize(bytes);

                }
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return null;
    }

    public void set(String key, Object value) {
        if (pool == null) {
            return;
        }
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis != null) {
                jedis.set(SerializationUtils.serialize(key), SerializationUtils.serialize(value));
            }
            if (logger.isDebugEnabled()) {
                logger.debug("set cache ok ,key: " + key);
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void set(String key, Object value, int expireSeconds) {
        if (pool == null) {
            return;
        }
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis != null) {
                byte[] byteKey = SerializationUtils.serialize(key);
                jedis.set(byteKey, SerializationUtils.serialize(value));
                jedis.expire(byteKey, expireSeconds);
                if (logger.isDebugEnabled()) {
                    logger.debug("set cache ok ,key: " + key + ", expireSeconds: " + expireSeconds);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void remove(String key) {
        if (pool == null) {
            return;
        }
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis != null) {
                byte[] byteKey = SerializationUtils.serialize(key);
                jedis.del(byteKey);
                if (logger.isDebugEnabled()) {
                    logger.debug("remove cache ok ,key: " + key);
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }

    public void clear() {
        map.clear();
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis != null) {
                jedis.flushAll();
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
    }
}
