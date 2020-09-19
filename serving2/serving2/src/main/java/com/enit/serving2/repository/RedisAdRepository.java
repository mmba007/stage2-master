package com.enit.serving2.repository;

import com.enit.serving2.entity.Ad;
import com.enit.serving2.entity.Recommandation;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class RedisAdRepository {

    private HashOperations hashOperations;

    private RedisTemplate redisTemplate;

    public RedisAdRepository(RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }


    public void save(String username,String id,Ad ad) {
        hashOperations.put( username,id, ad);
    }
    public Ad findById(String id,String username){
        return (Ad) hashOperations.get(username, id);
    }
    public void delete(String username){
        redisTemplate.delete(username);
    }


    public List findAll(String username){
        return hashOperations.values(username);
    }
    public Map<String, List<Ad>> multiGetAds(List<String> adIds,String username){

        Map<String, List<Ad>> adMap = new HashMap<>();
        List<Object> ads = hashOperations.multiGet(username, adIds);
        for (int i = 0; i < adIds.size(); i++) {
            adMap.put(adIds.get(i), (List<Ad>) ads.get(i));
        }
        return adMap;
    }

}