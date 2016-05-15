package test.elasticsearch;

import java.util.List;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;

/**
 * ES 测试
 */
public class EsMain {
	public static void main(String[] args) {
		EsHandler esHandler = new EsHandler();
		List<String> jsonData = DataFactory.getInstance().getInitJsonData();

		String indexName = "index-demo";
		String type = "type-demo";

		esHandler.createIndex(indexName, type, jsonData);

		// QueryBuilder queryBuilder = QueryBuilders.matchQuery("name", "感冒");
		QueryBuilder queryBuilder = QueryBuilders.boolQuery().must(new QueryStringQueryBuilder("感冒").field("name"));

		List<Medicine> result = esHandler.searcher(queryBuilder, indexName, type);
		for (Medicine medicine : result) {
			System.out.println("(" + medicine.getId() + ") 药品名称: " + medicine.getName() + 
					"\t\t" + medicine.getFunction());
		}
	}
}
