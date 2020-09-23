package com.enit.monitoringRec.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.enit.monitoringRec.configuration.EventService;
import com.enit.monitoringRec.entity.Algorithm;
import com.enit.monitoringRec.repository.AlgoRepo;
import com.enit.monitoringRec.repository.RedisAdRepository;

@CrossOrigin(origins = "*")
@RestController
public class AdController {

	private int counter = 0;

	@Autowired
	private EventService kafkaTemplate;

	@Autowired
	RedisAdRepository adsService;

	@Autowired
	AlgoRepo algoRepo;

	@Autowired
	private RedisAdRepository adRepository;

//    @PostMapping
//    public Ad save(@RequestBody Recommandation rec){
//       adRepository.save(rec.getUsername(),Integer.toString(counter),rec.getAd());
//       counter++;
//        return rec.getAd();
//    }

	@GetMapping("/adsInfo/all")
	public List listAdsInfos() {

		return adsService.findAll();

	}

	@GetMapping("/algo/all")
	public Iterable<Algorithm> listAlgos() {

		return algoRepo.findAll();

	}

	@GetMapping("/algo/{algoId}/increment")
	public String incrementAlgoScore(@PathVariable String algoId) {
		Algorithm algo = algoRepo.findById(algoId).get();
		if (algo != null) {
			System.out.println("\n ------ Algorithm " + algoId + " found in db and has score: " + algo.getScore());
			algo.incrementScore();
			algoRepo.save(algo);
			return " ------------ Score of Algorithm " + algoId + " incremented";
		}

		return "algo " + algoId + " not found";

	}

//    @GetMapping("/{id}/{username}")
//    public Ad getUser(@PathVariable String id,@PathVariable String username){
//        return adRepository.findById(id,username);
//    }

//    @DeleteMapping("/{username}")
//    public String deleteAd(@PathVariable String id,@PathVariable String username){
//        adsService.delete(id,username);
//        return id;
//    }

//    @GetMapping(value = "/request/{username}/{lat}/{lon}")
//    public List<Ad> saveConsumer(@PathVariable String username ,@PathVariable Double lon,@PathVariable Double lat ) throws InterruptedException {
//        List<String> preferences= new ArrayList<>();
//        preferences.add("car");
//        preferences.add("food");
//
//        Double[] location=new Double[]{lat,lon};
//        ConsumerRequest consumerRequest = new ConsumerRequest(username,lon,lat,preferences);
//        kafkaTemplate.sendUserRequest(consumerRequest);
//
//
//        return adsService.findAll(username);
//
//    }

}
