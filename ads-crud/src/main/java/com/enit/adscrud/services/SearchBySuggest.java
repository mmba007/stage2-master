//package com.enit.adscrud.com.enit.randomrecommandationservice.services;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import com.enit.adscrud.com.enit.randomrecommandationservice.entity.Ad;
//import org.elasticsearch.client.Client;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.http.HttpEntity;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//
//import com.fasterxml.jackson.core.type.TypeReference;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//@Service
//public class SearchBySuggest {
//	@Autowired
//	Client client;
//
//	@Autowired
//	private ElasticsearchTemplate template;
//
//	public List<Ad> searchMultiField(String preference) {
//		QueryBuilder query = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("textField", preference));
//		NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder().withQuery(query).build();
//		List<Ad> ads = template.queryForList(nativeSearchQuery, Ad.class);
//		return ads;
//	}
//
//	public Set<String> suggestSearch(String preference) throws Exception {
//		long startTime = System.nanoTime();
//		RestTemplate restTemplate = new RestTemplate();
//
//		String url = "http://localhost:9200/advertiser/_search";
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//
//		String json = "{\"suggest\":  { \"my-suggestion\": { \"text\": \"" + preference
//				+ "\",  \"term\": { \"field\":\"category\"} } }}";
//
//		HttpEntity<String> requestEntity = new HttpEntity<>(json, headers);
//
//		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
//
//		String resultString = response.toString();
//		StringBuilder sb = new StringBuilder(resultString);
//		String jsonString = sb.substring(sb.indexOf("{"), sb.lastIndexOf("}") + 1);
//		System.out.println(jsonString);
//
//		ObjectMapper mapper = new ObjectMapper();
//		JsonNode actualObj = mapper.readTree(jsonString);
//		JsonNode options = actualObj.get("suggest").get("my-suggestion").get(0).get("options");
//
//		ObjectMapper mapper2 = new ObjectMapper();
//		List<JsonNode> optionsList = mapper2.convertValue(options, new TypeReference<List<JsonNode>>() {
//		});
//		List<String> suggestions = new ArrayList<String>();
//		for (JsonNode suggestion : optionsList) {
//			suggestions.add(suggestion.get("text").toString());
//		}
//		System.out.println(suggestions);
//		List<Ad> ads = new ArrayList<Ad>();
//		for (String suggestion : suggestions) {
//			ads.addAll(searchMultiField(suggestion));
//		}
//
//		// to eliminate redundant Ads we use a set
//		Set<String> adsIDs = new HashSet<String>();
//		for (Ad ad : ads) {
//			adsIDs.add(ad.getId());
//		}
//		System.out.println(adsIDs);
//		// adsIDs is what we've been looking for
//		// Now we make the search for these ids in kafka topic
//
//		long endTime = System.nanoTime();
//		long duration = (endTime - startTime) / 1000000;
//		System.out.println("Execution time = " + duration + "ms");
//
//		// String suggestionStr =
//		// mapper.writerWithDefaultPrettyPrinter().writeValueAsString(options);
//		return adsIDs;
//
//	}
//
////	public String makeApiCall() {
////	final String uri = "https://localhost:9200/advertiser/_search";
////
////	RestTemplate restTemplate = new RestTemplate();
////
////	String reqBody = "{\"suggest\":  { \"my-suggestion\": { \"text\\\": \"powerfol\",  \"term\": { \"field\":\"textField\"} } }}";
////	String result = restTemplate.postForObject(uri, reqBody, String.class);
//
////	 convert your result into json
////	try {
////		jsonResponse = new JsonObject(result);
////	} catch (JSONException e) {
////		e.printStackTrace();
////	}
//////extract a value "name" from your json data:
////	try {
////		String value = jsonResponse.getString("name");
////	} catch (JSONException e) {
////		e.printStackTrace();
////	}
//
////	System.out.println(result);
////	return result;
////}
//
////	public String makeSuggestCall() {
////		BoolQueryBuilder boolQuery = QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("textField", "lenovo"));
////
////		SuggestionBuilder suggestionBuilder1 = new TermSuggestionBuilder("textField").text("leno");
////
////		SuggestBuilder suggestion1 = new SuggestBuilder().addSuggestion("my-suggest-1", suggestionBuilder1);
////
////		SearchRequestBuilder builder = client.prepareSearch("advertiser").setTypes("_doc")
////				.suggest(suggestion1);
////		String str = "";
////		SearchResponse searchResponse;
////		try {
////			searchResponse = builder.execute().get();
////
////			for (SearchHit hit : searchResponse.getHits().getHits()) {
////				str += "Result: " + hit.getSourceAsString() + "\n";
////			}
////			System.out.println("Number of hits : " + searchResponse.getHits().totalHits);
////			return str;
////		} catch (InterruptedException | ExecutionException e) {
////			System.out.println("error!!!!!!!!!!!!!!!");
////		}
////		return "Exception occured!!!!!!!!!!!!";
////	}
//
////
////
////
////	public List<String> getSuggestions(String partial) {
////	    List<String> returnList = new ArrayList<>();
////
////	    String suggestionName = "suggestion";
////
////	    CompletionSuggestionBuilder completionSuggestionBuilder = new CompletionSuggestionBuilder(suggestionName);
////	    SuggestResponse suggestResponse = client.prepareSuggest("wow").setSuggestText(partial).addSuggestion(completionSuggestionBuilder.field("name.suggest")).execute().actionGet();
////	    Suggest suggest = suggestResponse.getSuggest();
////	    Suggest.Suggestion suggestion = suggest.getSuggestion(suggestionName);
////
////	    List<Suggest.Suggestion.Entry> list = suggestion.getEntries();
////	    for(Suggest.Suggestion.Entry entry : list) {
////	        List<Suggest.Suggestion.Entry.Option> options = entry.getOptions();
////	        for(Suggest.Suggestion.Entry.Option option : options) {
////	            returnList.add(option.getText().toString());
////	        }
////	    }
////
////	    return returnList;
////	}
//
//}
