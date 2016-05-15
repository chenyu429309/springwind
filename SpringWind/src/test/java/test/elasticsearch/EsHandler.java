package test.elasticsearch;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;

/**
 * ElasticSearch 核心搜索模拟类
 */
public class EsHandler {

	private Client client;

	private void initClient() {
		// 使用本机作为节点
		try {
			/*
			 * 查看cluster名称，访问 http://localhost:9200/ 
			 * 如果不是默认的"elasticsearch"，通过以下方式可设置
			 */
			/*
			 * Settings settings =
			 * Settings.settingsBuilder().put("cluster.name",
			 * "myClusterName").build(); client =
			 * TransportClient.builder().settings(settings).build();
			 * 
			 * Settings settings = ImmutableSettings.settingsBuilder() //指定集群名称
			 * .put("cluster.name", "elasticsearch") //探测集群中机器状态
			 * .put("client.transport.sniff", true).build();
			 */

			// 创建客户端，所有的操作都由客户端开始，这个就好像是JDBC的Connection对象，用完记得要关闭

			// 必须要,否则会报None of the configured nodes are available.
			Settings settings = Settings.settingsBuilder().put("cluster.name", "elasticsearch").build();

			client = TransportClient.builder().settings(settings).build()
					.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}

	private void closeClient() {
		if (null != client)
			client.close();
	}

	/**
	 * 执行搜索
	 *
	 * @param queryBuilder
	 *            查询条件
	 * @param indexName
	 *            索引库名
	 * @param type
	 *            索引类型
	 * @return
	 */
	public List<Medicine> searcher(QueryBuilder queryBuilder, String indexName, String type) {
		List<Medicine> list = new ArrayList<Medicine>();
		initClient();
		SearchResponse searchResponse = client.prepareSearch(indexName).setTypes(type).setQuery(queryBuilder).execute()
				.actionGet();
		SearchHits hits = searchResponse.getHits();
		System.out.println("查询到记录数 = " + hits.getTotalHits());
		SearchHit[] hitArr = hits.getHits();
		if (hitArr.length > 0) {
			for (SearchHit hit : hitArr) {
				Integer id = (Integer) hit.getSource().get("id");
				String name = (String) hit.getSource().get("name");
				String function = (String) hit.getSource().get("function");
				list.add(new Medicine(id, name, function));
			}
		}
		closeClient();
		return list;
	}

	/**
	 * 批量建立索引,索引建立好之后,会在elasticsearch-0.20.6\data\elasticsearch\nodes\0创建所以你看
	 *
	 * @param indexName
	 *            为索引库名，一个es集群中可以有多个索引库。 名称必须为小写
	 * @param type
	 *            Type为索引类型，是用来区分同索引库下不同类型的数据的，一个索引库下可以有多个索引类型。
	 * @param dataList
	 *            json格式的数据集合
	 */
	public void createIndex(String indexName, String type, List<String> dataList) {
		initClient();
		// 创建索引库 需要注意的是.setRefresh(true)这里一定要设置,否则第一次建立索引查找不到数据
		IndexRequestBuilder requestBuilder = client.prepareIndex(indexName, type).setRefresh(true);
		for (String data : dataList) {
			requestBuilder.setSource(data).setId(data.substring(6, 7)).execute().actionGet();
		}
		closeClient();
	}

	/**
	 * 建立索引
	 *
	 * @param indexName
	 *            索引库名
	 * @param type
	 *            索引类型
	 * @param id
	 *            索引id
	 * @param data
	 *            json格式的数据
	 * @return
	 */
	public IndexResponse createIndex(String indexName, String type, String id, String data) {
		initClient();
		IndexResponse response = client.prepareIndex(indexName, type, id).setSource(data).execute().actionGet();
		System.out.println("response version : " + response.getVersion());
		closeClient();
		return response;
	}

	/**
	 * 取得索引
	 *
	 * @param indexName
	 *            索引库名
	 * @param type
	 *            索引类型
	 * @param id
	 *            索引id
	 * @return
	 */
	public GetResponse getIndex(String indexName, String type, String id) {
		initClient();
		GetResponse response = client.prepareGet(indexName, type, id).execute().actionGet();
		System.out.println(response.getSourceAsString());
		return response;
	}

	/**
	 * 删除索引
	 *
	 * @param indexName
	 *            索引库名
	 * @param type
	 *            索引类型
	 * @param id
	 *            索引id
	 * @return
	 */
	public DeleteResponse deleteIndex(String indexName, String type, String id) {
		initClient();
		DeleteResponse response = client.prepareDelete(indexName, type, id).execute().actionGet();
		System.out.println(response.getId());
		return response;
	}
}
