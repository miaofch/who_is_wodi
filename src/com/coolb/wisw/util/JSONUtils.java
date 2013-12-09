package com.coolb.wisw.util;

import java.util.Arrays;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.log4j.Logger;

/**
 * <P>
 * JSON处理工具，负责处理常见的JSON操作，包括JSON的变换等。
 * </p>
 * @author iAngus
 * @version 2.0, Nov 23, 2011
 */
public class JSONUtils {

	/**
	 * 日志对象
	 */
	private static final Logger log = Logger.getLogger(JSONUtils.class);

	/**
	 * 将json字符串转化为对象
	 * 
	 * @param <T>
	 *            对象类型
	 * @param jsonString
	 *            json字符串
	 * @param clazz
	 *            对象类型
	 * @return 由json字符串转化成的对象
	 * @throws Exception
	 * @author miaofc
	 * @date Nov 23, 2011 3:35:51 PM
	 */
	public static <T> T jsonString2Bean(String jsonString, Class<T> clazz) throws Exception {
		JSONObject jsonObject = JSONObject.fromObject(jsonString);
		T bean = (T) JSONObject.toBean(jsonObject, clazz);
		return bean;
	}

	/**
	 * 将json字符串转化为数组（List）
	 * 
	 * @param <T>
	 *            数组元素类型
	 * @param jsonString
	 *            json字符串
	 * @param clazz
	 *            数组元素类型
	 * @return 由json字符串转化成的数组
	 * @throws Exception
	 * @author miaofc
	 * @date Nov 23, 2011 3:39:22 PM
	 */
	public static <T> List<T> jsonString2List(String jsonString, Class<T> clazz) throws Exception {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		T[] array = (T[]) jsonArray.toArray(jsonArray, clazz);
		List<T> list = Arrays.asList(array);
		return list;
	}

	/**
	 * 将json字符串转化为数组（Array）
	 * 
	 * @param <T>
	 *            数组元素类型
	 * @param jsonString
	 *            json字符串
	 * @param clazz
	 *            数组元素类型
	 * @return 由json字符串转化成的数组
	 * @throws Exception
	 * @author miaofc
	 * @date Nov 23, 2011 3:40:44 PM
	 */
	public static <T> T[] jsonString2Array(String jsonString, Class<T> clazz) throws Exception {
		JSONArray jsonArray = JSONArray.fromObject(jsonString);
		return (T[]) jsonArray.toArray(jsonArray, clazz);
	}

	/**
	 * 将一个对象转化为json字符串
	 * 
	 * @param bean
	 *            任意对象
	 * @return json字符串
	 * @throws Exception
	 * @author miaofc
	 * @date Nov 23, 2011 3:43:57 PM
	 */
	public static String bean2JsonString(Object bean) {
		return bean2JsonString(bean, null);
	}

	/**
	 * 将一个对象转化为json字符串
	 * 
	 * @param bean
	 *            任意对象
	 * @param properties
	 *            要被json序列化的属性名
	 * @return json字符串
	 * @throws Exception
	 * @author miaofc
	 * @date Nov 23, 2011 6:07:36 PM
	 */
	public static String bean2JsonString(Object bean, String[] properties) {
		JsonConfig jsonConfig = new JsonConfig();
		// 设置属性过滤器，负责筛选指定的属性
		if (properties != null) {
			jsonConfig.setJsonPropertyFilter(new PropertyFilterImpl(properties));
		}
		JSONObject jsonObject = JSONObject.fromObject(bean, jsonConfig);
		return jsonObject.toString();
	}

	/**
	 * 将一个数组（List）转化为json字符串
	 * 
	 * @param list
	 *            任意数组
	 * @return json字符串
	 * @throws Exception
	 * @author miaofc
	 * @date Nov 23, 2011 3:45:07 PM
	 */
	public static String list2JsonString(List list) {
		return list2JsonString(list, null);
	}

	/**
	 * 将一个数组（List）转化为json字符串
	 * 
	 * @param list
	 *            任意数组
	 * @param properties
	 *            要被json序列化的属性名
	 * @return json字符串
	 * @throws Exception
	 * @author miaofc
	 * @date Nov 23, 2011 6:08:18 PM
	 */
	public static String list2JsonString(List list, String[] properties) {
		JsonConfig jsonConfig = new JsonConfig();
		// 设置属性过滤器，负责筛选指定的属性
		if (properties != null) {
			jsonConfig.setJsonPropertyFilter(new PropertyFilterImpl(properties));
		}
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		return jsonArray.toString();
	}

	/**
	 * 将一个数组（Array）转化为json字符串
	 * 
	 * @param array
	 *            任意数组
	 * @return json字符串
	 * @throws Exception
	 * @author miaofc
	 * @date Nov 23, 2011 3:46:34 PM
	 */
	public static String array2JsonString(Object[] array) throws Exception {
		return array2JsonString(array, null);
	}

	/**
	 * 将一个数组（Array）转化为json字符串
	 * 
	 * @param array
	 *            任意数组
	 * @param properties
	 *            要被json序列化的属性名
	 * @return json字符串
	 * @throws Exception
	 * @author miaofc
	 * @date Nov 23, 2011 6:09:00 PM
	 */
	public static String array2JsonString(Object[] array, String[] properties) throws Exception {
		JsonConfig jsonConfig = new JsonConfig();
		// 设置属性过滤器，负责筛选指定的属性
		if (properties != null) {
			jsonConfig.setJsonPropertyFilter(new PropertyFilterImpl(properties));
		}
		JSONArray jsonArray = JSONArray.fromObject(array, jsonConfig);
		return jsonArray.toString();
	}

}

/**
 * 属性过滤器，根据设定判断究竟哪些属性将被排除，不写入json字符串
 * 
 * @author miaofc. Email:miaofc@asiainfo-linkage.com
 * @version 2.0, Nov 25, 2011
 * @see com.ailk.dazzle.util.data.PropertyFilterImpl
 * @since
 */
class PropertyFilterImpl implements PropertyFilter {

	private String[] properties;

	PropertyFilterImpl(String[] properties) {
		this.properties = properties;
	}

	/**
	 * 筛选属性
	 */
	public boolean apply(Object source, String name, Object value) {
		// 如果properties数组中包含改属性，则返回false
		if (ArrayUtils.contains(properties, name)) {
			return false;
		}
		// 否则返回true
		return true;
	}

}
