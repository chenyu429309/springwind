package test.elasticsearch;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

/**
 * 应用工具类
 */
public class DataFactory {

	private DataFactory() {

	}

	private static class DataFactoryHolder {
		private static final DataFactory factory = new DataFactory();
	}

	public static DataFactory getInstance() {
		return DataFactoryHolder.factory;
	}

	public List<String> getInitJsonData() {
		String data1 = object2JsonData(new Medicine(1, "银花 感冒 颗粒", "功能主治：银花感冒颗粒 ，头痛,清热，解表，利咽。"));
		String data2 = object2JsonData(new Medicine(2, "感冒  止咳糖浆", "功能主治：感冒止咳糖浆,解表清热，止咳化痰。"));
		String data3 = object2JsonData(new Medicine(3, "感冒灵颗粒", "功能主治：解热镇痛。头痛 ,清热。"));
		String data4 = object2JsonData(new Medicine(4, "感冒  灵胶囊", "功能主治：银花感冒颗粒 ，头痛,清热，解表，利咽。"));
		String data5 = object2JsonData(new Medicine(5, "仁和 感冒 颗粒", "功能主治：疏风清热，宣肺止咳,解表清热，止咳化痰。"));

		return Arrays.asList(data1, data2, data3, data4, data5);
	}

	/**
	 * 实体对象转json对象
	 *
	 * @param medicine
	 * @return
	 */
	private static String object2JsonData(Medicine medicine) {
		String jsonData = null;
		try {
			// 使用XContentBuilder创建json数据
			XContentBuilder jsonBuild = XContentFactory.jsonBuilder();
			jsonBuild.startObject().field("id", medicine.getId()).field("name", medicine.getName())
					.field("function", medicine.getFunction()).endObject();
			jsonData = jsonBuild.string();
			System.out.println(jsonData);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonData;
	}
}
