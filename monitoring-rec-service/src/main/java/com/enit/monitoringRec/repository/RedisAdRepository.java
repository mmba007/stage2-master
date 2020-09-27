package com.enit.monitoringRec.repository;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.enit.monitoringRec.entity.Algorithm;
import com.enit.monitoringRec.entity.ShortAdInfo;

@Repository
public class RedisAdRepository {

	@Autowired
	AlgorithmRepository algoRepo;

	private HashOperations<String, String, ShortAdInfo> hashOperations;

	private RedisTemplate<String, ShortAdInfo> redisTemplate;

	public RedisAdRepository(RedisTemplate<String, ShortAdInfo> redisTemplate) {
		this.redisTemplate = redisTemplate;
		this.hashOperations = this.redisTemplate.opsForHash();
	}

//    public void save(String username,String id,Ad ad) {
//        hashOperations.put( username,id, ad);
//    }

	public void save(String algoId, String adId, ShortAdInfo shortAdInfo) {
		hashOperations.put(algoId, adId, shortAdInfo);
	}

//    public Ad findById(String id,String username){
//        return (Ad) hashOperations.get(username, id);
//    }

	public ShortAdInfo findById(String algoId, String adId) {
		return (ShortAdInfo) hashOperations.get(algoId, adId);
	}

	public List<ShortAdInfo> findAll(String algoId) {
		return hashOperations.values(algoId);
	}

	public Set<ShortAdInfo> findOldAds(long period) {
		Set<ShortAdInfo> oldAds = new HashSet<ShortAdInfo>();
		for (Algorithm algo : algoRepo.findAll()) {
			if (!findAll(algo.getAlgoId()).isEmpty()) {
				for (ShortAdInfo ad : findAll(algo.getAlgoId())) {
					if (new Date().getTime() - ad.getTimeOfRecommandation().getTime() >= period) {
						System.out.println("\n ******* found one old ad to be removed");
						oldAds.add(ad);
					}
				}
			} else {
				System.out.println("\n          no ads were saved with the algo: " + algo.getAlgoId());
			}
		}
		return oldAds;
	}

	public Boolean deleteOldAds(long period) {
		Set<ShortAdInfo> oldAds = findOldAds(period);
		if (!oldAds.isEmpty()) {
			for (ShortAdInfo ad : oldAds) {
				deleteAd(ad.getAlgoId(), ad.getAdId());
				System.out.println("\n ****** old ad with id " + ad.getAdId() + " removed from cache");
			}
			System.out.println("\n    ~~~~~~~~~~~~ old ads deleted successfully :) ~~~~~~~~~~~~~~~");
			return true;
//			return "old ads deleted successfully";
		} else {
			System.out.println("There is no recommandation older than " + period / 1000 + " seconds !!!");
//			return "There is no ads recommanded in the last " + period / 1000 + " seconds !!!";
			return false;
		}
	}

	public void deleteAd(String algoId, String adId) {
		hashOperations.delete(algoId, adId);
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