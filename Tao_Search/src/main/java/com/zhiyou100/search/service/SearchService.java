package com.zhiyou100.search.service;

import com.zhiyou100.search.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString, int page, int rows) throws Exception;
}