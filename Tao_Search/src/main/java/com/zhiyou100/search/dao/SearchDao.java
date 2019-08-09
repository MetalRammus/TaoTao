package com.zhiyou100.search.dao;

import org.apache.solr.client.solrj.SolrQuery;

import com.zhiyou100.search.pojo.SearchResult;

public interface SearchDao {

	SearchResult search(SolrQuery query) throws Exception;
}
