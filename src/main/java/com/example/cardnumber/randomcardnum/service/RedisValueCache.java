package com.example.cardnumber.randomcardnum.service;


import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisValueCache {

private final ValueOperations<String,Object> valueOperations;

    public RedisValueCache(final RedisTemplate<String, Object> redisTemplate) {
        valueOperations = redisTemplate.opsForValue();
    }

    public void cache(final  String key, final Object data){
        valueOperations.set(key, data);
    }

    public Object getCachedValue(final String key) {
        return valueOperations.get(key);
    }

    public void deleteCachedValue(final String key) {
        valueOperations.getOperations().delete(key);
    }

}
