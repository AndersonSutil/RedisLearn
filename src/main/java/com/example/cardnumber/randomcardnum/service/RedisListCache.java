package com.example.cardnumber.randomcardnum.service;


import com.example.cardnumber.randomcardnum.repository.NumDao;
import com.example.cardnumber.randomcardnum.repository.RangeDTO;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RedisListCache {

    private ListOperations<String, Object> listOps;

    public RedisListCache(final RedisTemplate<String, Object> redisTemplate) {
        listOps = redisTemplate.opsForList();
    }

    public void cacheNums(final String key, final List<NumDao> nums) {
        for (final NumDao num : nums) {
            listOps.leftPush(key, num);
        }
    }

    public List<NumDao> getNumInRange(final String key, final RangeDTO range) {
        final List<Object> objects = listOps.range(key, range.getFrom(), range.getTo());
        if (CollectionUtils.isEmpty(objects)) {
            return Collections.emptyList();
        }
        return objects.stream()
                .map(x -> (NumDao) x)
                .collect(Collectors.toList());
    }

    public NumDao getLastElement(final String key) {
        final Object o = listOps.rightPop(key);
        if (o == null) {
            return null;
        }

        return (NumDao) o;
    }
    public void trim(final String key, final RangeDTO range) {
        listOps.trim(key, range.getFrom(), range.getTo());
    }



}
