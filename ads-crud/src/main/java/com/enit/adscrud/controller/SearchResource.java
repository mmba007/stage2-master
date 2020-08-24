package com.enit.adscrud.controller;//package com.enit.AdsManagement.com.enit.randomrecommandationservice.controller;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.enit.AdsManagement.com.enit.randomrecommandationservice.entity.Ads;
//import com.enit.AdsManagement.com.enit.randomrecommandationservice.repository.AdsRepository;
//
//@RestController
//@RequestMapping("/rest/search")
//public class SearchResource {
//
//    @Autowired
//    AdsRepository adsRepository;
//    
////    @PostMapping(value = "/save")
////	public String saveOneUser(@RequestBody Ads ad) {
////    	adsRepository.save(ad);
////		
////		return "User saved successfully";
////	}
//
////    @GetMapping(value = "/name/{text}")
////    public List<Ads> searchName(@PathVariable final String text) {
////        return adsRepository.findById(text);
////    }
//
//    
//    @GetMapping(value = "/textField/{text}")
//    public List<Ads> searchSalary(@PathVariable String  text) {
//        return adsRepository.findByTextField(text);
//    }
//
//
//    @GetMapping(value = "/all")
//    public List<Ads> searchAll() {
//        List<Ads> usersList = new ArrayList<>();
//        Iterable<Ads> userses = adsRepository.findAll();
//        userses.forEach(usersList::add);
//        return usersList;
//    }
//
//
//}
