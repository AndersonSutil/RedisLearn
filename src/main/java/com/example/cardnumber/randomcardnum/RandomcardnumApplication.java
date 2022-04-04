package com.example.cardnumber.randomcardnum;

import com.example.cardnumber.randomcardnum.repository.NumDao;
import com.example.cardnumber.randomcardnum.repository.RangeDTO;
import com.example.cardnumber.randomcardnum.service.RedisListCache;
import com.example.cardnumber.randomcardnum.service.RedisValueCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@SpringBootApplication
@RestController
@RequestMapping("/num")
public class RandomcardnumApplication {

//    Redisson redisson = new Redisson();
//    RReadWriteLock rReadWriteLock = redisson.getReadWriteLock("myLock"); ;

    private final RedisValueCache valueCache;
    private final RedisListCache listCache;

    @Autowired
    public RandomcardnumApplication(RedisValueCache valueCache, RedisListCache listCache) {
        this.valueCache = valueCache;
        this.listCache = listCache;
    }


//    @Autowired
//    private NumDao dao;
//
//
//    @PostMapping
//    public Num save(@RequestBody Num num) {
//        return dao.save(num);
//    }
//
//    @GetMapping("/{id}")
//    public Num findNumById(@PathVariable int num) {
//        return dao.findNumById(num);
//    }
//
//    @GetMapping
//    public List<Num> getAllNums() {
//        return dao.findAll();
//    }
//
//    @DeleteMapping("/{id}")
//    public String remove(@PathVariable int id) {
//        return dao.deleteNumeroById(id);
//    }

    @PostMapping
    public void cacheNum(@RequestBody final NumDao dto) {
        valueCache.cache(dto.getNum(), dto);

    }

    @GetMapping("/{id}")
    public NumDao getNum(@PathVariable final String id){
        return (NumDao) valueCache.getCachedValue(id);
    }

    @PostMapping("/list/{key}")
    public void cacheNums(@PathVariable final String key, @RequestBody final List<NumDao> numDaos) {
        listCache.cacheNums(key, numDaos);
    }

    @GetMapping("/list/{key}")
    public List<NumDao> getPersonsInRange(@PathVariable final String key, @RequestBody final RangeDTO range) {
//        rReadWriteLock.readLock().lock();
//        RLock lock = rReadWriteLock.writeLock();
        List<NumDao> list;
        try {
//            lock.lock(100,TimeUnit.SECONDS);
            list = listCache.getNumInRange(key, range);

        } finally {

//            lock.unlock();
        }
        return list;
    }

    public static void main(String[] args) {
        SpringApplication.run(RandomcardnumApplication.class, args);
    }




}
