package com.pc.config;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:cloud_config.properties")
public class ElasticsearchConfig {

    @Value("${elasticsearch.host}")
    private String elasticsearchHost;

    @Bean(destroyMethod = "close")
    public RestHighLevelClient restClient() {
    	Header[] headers = { new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/json")};

        RestClientBuilder restClientBuilder = RestClient.builder(new HttpHost(elasticsearchHost, 443, "https"));
        restClientBuilder.setDefaultHeaders(headers);
        
        RestHighLevelClient client = new RestHighLevelClient(restClientBuilder);

        return client;

    }


}
