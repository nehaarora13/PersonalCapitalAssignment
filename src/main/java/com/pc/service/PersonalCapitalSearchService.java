package com.pc.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pc.dto.PlanDocument;

@Service
public class PersonalCapitalSearchService {

	private static final String INDEX = "investment-plans";
    private static final String TYPE = "plan";

    private RestHighLevelClient client;

    private ObjectMapper objectMapper;
    
	private static final Logger logger = LogManager.getLogger(PersonalCapitalSearchService.class);

    @Autowired
    public PersonalCapitalSearchService(RestHighLevelClient client, ObjectMapper objectMapper) {
        this.client = client;
        this.objectMapper = objectMapper;
        this.objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
    }
    

    public PlanDocument findById(String id) throws Exception {

        GetRequest getRequest = new GetRequest(INDEX, TYPE, id);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> resultMap = getResponse.getSource();
        
        for (String key : resultMap.keySet()) {
			logger.info("Key = " + key);
			logger.info("value = " + resultMap.get(key));
		}

        return objectMapper
                .convertValue(resultMap, PlanDocument.class);


    }
    
	public List<PlanDocument> findByName(String name) throws Exception {
		try {
			SearchRequest searchRequest = new SearchRequest();
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

			BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
					.must(QueryBuilders.termQuery("PLAN_NAME.raw", name));
			searchSourceBuilder.query(queryBuilder);
			searchRequest.source(searchSourceBuilder);
			SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

			return getSearchResult(response);
		} catch (Exception e) {
			logger.error(String.format("Exception while trying to find the searchResults for name [%s]. Exception :",
					name, e));
			throw e;
		}
	}
	
	public List<PlanDocument> findByDfeName(String dfeName) throws Exception {
		try {
			SearchRequest searchRequest = new SearchRequest();
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

			BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
					.must(QueryBuilders.termQuery("SPONSOR_DFE_NAME.raw", dfeName));
			searchSourceBuilder.query(queryBuilder);
			searchRequest.source(searchSourceBuilder);
			SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

			return getSearchResult(response);
		} catch (Exception e) {
			logger.error(String.format("Exception while trying to find the searchResults for Sponsor name [%s]. Exception :",
					dfeName, e));
			throw e;
		}
	}
	
	public List<PlanDocument> findByDfeState(String dfeState) throws Exception {
		try {
			SearchRequest searchRequest = new SearchRequest();
			SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();

			BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery()
					.must(QueryBuilders.termQuery("SPONS_DFE_MAIL_US_STATE.raw", dfeState));
			searchSourceBuilder.query(queryBuilder);
			searchRequest.source(searchSourceBuilder);
			SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);

			return getSearchResult(response);
		} catch (Exception e) {
			logger.error(String.format("Exception while trying to find the searchResults for Sponsor name [%s]. Exception :",
					dfeState, e));
			throw e;
		}
	}
    
    public List<PlanDocument> findAll() throws Exception {
    	try {
            SearchRequest searchRequest = new SearchRequest();
            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(QueryBuilders.matchAllQuery());
            searchRequest.source(searchSourceBuilder);

            SearchResponse searchResponse =
                    client.search(searchRequest, RequestOptions.DEFAULT);

            return getSearchResult(searchResponse);
    	} catch(Exception e) {
    		logger.error(String.format("Exception while trying to find ALL searchResults. Exception :", e));
    		throw e;
    	}
    }
    
	private List<PlanDocument> getSearchResult(SearchResponse response) {

		SearchHit[] searchHit = response.getHits().getHits();
		List<PlanDocument> planDocuments = new ArrayList<>();

		if (searchHit.length > 0) {
			Arrays.stream(searchHit).forEach(hit -> planDocuments
					.add(objectMapper.convertValue(hit.getSourceAsMap(), PlanDocument.class)));
		}

		return planDocuments;
	}

 }
