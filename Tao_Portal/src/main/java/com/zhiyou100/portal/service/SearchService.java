package com.zhiyou100.portal.service;

import com.zhiyou100.portal.pojo.SearchResult;

public interface SearchService {

	SearchResult search(String queryString, int page);
}
