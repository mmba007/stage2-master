package com.enit.serving2.controller;


import com.enit.serving2.entity.Ad;
import com.enit.serving2.entity.ConsumerRequest;
import com.enit.serving2.entity.Recommandation;
import com.enit.serving2.repository.RedisAdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class AdController {

    private int counter=0;

    @Autowired
    KafkaTemplate<String, ConsumerRequest> kafkaTemplate;

    @Autowired
    RedisAdRepository adsService;

    @Autowired
    private RedisAdRepository adRepository;

    @PostMapping
    public Ad save(@RequestBody Recommandation rec){
       adRepository.save(rec.getUsername(),Integer.toString(counter),rec.getAd());
       counter++;
        return rec.getAd();
    }

    @GetMapping("/{username}")
    public List list(@PathVariable String username){

        return adsService.findAll(username);

    }

    @GetMapping("/{id}/{username}")
    public Ad getUser(@PathVariable String id,@PathVariable String username){
        return adRepository.findById(id,username);
    }

//    @DeleteMapping("/{username}")
//    public String deleteAd(@PathVariable String id,@PathVariable String username){
//        adsService.delete(id,username);
//        return id;
//    }

    @GetMapping(value = "/request/{username}/{lat}/{lon}")
    public List<Ad> saveConsumer(@PathVariable String username ,@PathVariable Double lon,@PathVariable Double lat ) throws InterruptedException {


        Double[] location=new Double[]{lat,lon};
        ConsumerRequest consumerRequest = new ConsumerRequest(username,lon,lat);
        kafkaTemplate.send("requestRecommandation", consumerRequest);


        return adsService.findAll(username);

    }

}
