package com.enit.monitoringRec.repository;

import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.enit.monitoringRec.entity.ShortAdInfo;

@Repository
public class RedisAdRepository {

	private HashOperations hashOperations;

	private RedisTemplate<String, ShortAdInfo> redisTemplate;

	public RedisAdRepository(RedisTemplate<String, ShortAdInfo> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = this.redisTemplate.opsForHash();
	}

//    public void save(String username,String id,Ad ad) {
//        hashOperations.put( username,id, ad);
//    }

	public void save(String adId, ShortAdInfo shortAdInfo) {
		hashOperations.put("AD_INFO", adId, shortAdInfo);
	}

//    public Ad findById(String id,String username){
//        return (Ad) hashOperations.get(username, id);
//    }

	public ShortAdInfo findById(String adId) {
		return (ShortAdInfo) hashOperations.get("AD_INFO", adId);
	}

	public List findAll() {
		return hashOperations.values("AD_INFO");
	}

	public void delete(String adId) {
		redisTemplate.delete(adId);
	}

//    public List findAll(String username){
//        return hashOperations.values(username);
//    }
//    public Map<String, List<Ad>> multiGetAds(List<String> adIds,String username){
//
//        Map<String, List<Ad>> adMap = new HashMap<>();
//        List<Object> ads = hashOperations.multiGet(username, adIds);
//        for (int i = 0; i < adIds.size(); i++) {
//            adMap.put(adIds.get(i), (List<Ad>) ads.get(i));
//        }
//        return adMap;
//    }

}