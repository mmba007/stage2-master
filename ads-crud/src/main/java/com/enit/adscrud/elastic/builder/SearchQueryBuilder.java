//package com.enit.adscrud.elastic.builder;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//import com.enit.adscrud.entity.Ad;
//import org.elasticsearch.common.unit.Fuzziness;
//import org.elasticsearch.index.query.QueryBuilder;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
//import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
//import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
//import org.springframework.stereotype.Component;
//
//
//
//@Component
//public class SearchQueryBuilder {
//
//	@Autowired
//	private ElasticsearchTemplate elasticsearchTemplate;
//
//	public Set<String> getAll(String text) {
//
//		QueryBuilder query = QueryBuilders.boolQuery()
//				.should(QueryBuilders.matchQuery("textField", text).fuzziness(Fuzziness.TWO))
//				.should(QueryBuilders.queryStringQuery(text).lenient(true).field("textField")
//						.fuzziness(Fuzziness.fromEdits(2)))
//				.should(QueryBuilders.queryStringQuery("*" + text + "*").lenient(true).field("textField"));
//
//		NativeSearchQuery build = new NativeSearchQueryBuilder().withQuery(query).build();
//		List<Ad> ads = elasticsearchTemplate.queryForList(build,Ad.class);
//		Set<String> adsIDs = new HashSet<String>();
//		for (Ad ad : ads) {
//			adsIDs.add(ad.getId().toString());
//		}
//		System.out.println(adsIDs);
//		return adsIDs;
//	}
//}
