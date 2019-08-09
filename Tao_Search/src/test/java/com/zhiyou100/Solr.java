package com.zhiyou100;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.junit.Test;

public class Solr {

	@Test
	public void delete() throws Exception{
		SolrServer solrServer=new HttpSolrServer("http://192.168.16.3:8080/solr");
		solrServer.deleteByQuery("*:*");
		solrServer.commit();
	}
}
