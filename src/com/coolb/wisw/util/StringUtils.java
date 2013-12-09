package com.coolb.wisw.util;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

/**
 * <P>
 * 字符串处理工具，负责处理常见的字符串操作，包括字符串相关的变换、判断等。
 * Copyright 2011 AsiaInfo-Linkage Inc. All Rights Reserved.
 * </p>
 * @author miaofc. Email:miaofc@asiainfo-linkage.com
 * @version 2.0, Nov 22, 2011
 * @see com.ailk.dazzle.util.type.StringUtils 
 * @since
 */
public class StringUtils {

	/**
	 * 日志对象
	 */
	private static final Logger log = Logger.getLogger(StringUtils.class);

	/**
	 * 去掉字符串左边的无效字符
	 * 
	 * @param str
	 *            待处理字符串
	 * @param invalidStr
	 *            无效字符组成的字符串
	 * @return 去除左边无效字符后的字符串
	 */
	public static String ltrim(String str, String invalidStr) {

		// 如果待处理字符串为空，或是无效字符为空，则直接返回原字符串
		if (str == null || str.length() == 0 || invalidStr == null || invalidStr.length() == 0) {
			return str;
		}

		// 计算字符串切除位置
		int trimLength = 0;

		for (int i = 0; str != null && i < str.length(); i++) {
			boolean stopFlag = true;
			for (int j = 0; invalidStr != null && j < invalidStr.length(); j++) {
				if (str.charAt(i) == invalidStr.charAt(j)) {
					trimLength++;
					stopFlag = false;
					break;
				}
			}
			if (stopFlag) {
				break;
			}
		}

		return str.substring(trimLength);
	}

	/**
	 * 去掉字符串右边的无效字符
	 * 
	 * @param str
	 *            待处理字符串
	 * @param invalidStr
	 *            无效字符组成的字符串
	 * @return 去除右边无效字符后的字符串
	 */
	public static String rtrim(String str, String invalidStr) {

		// 如果待处理字符串为空，或是无效字符为空，则直接返回原字符串
		if (str == null || str.length() == 0 || invalidStr == null || invalidStr.length() == 0) {
			return str;
		}

		// 计算字符串切除位置
		int trimLength = 0;

		for (int i = str.length() - 1; i >= 0; i--) {
			boolean stopFlag = true;
			for (int j = 0; invalidStr != null && j < invalidStr.length(); j++) {
				if (str.charAt(i) == invalidStr.charAt(j)) {
					trimLength++;
					stopFlag = false;
					break;
				}
			}
			if (stopFlag) {
				break;
			}
		}

		return str.substring(0, str.length() - trimLength);
	}

	/**
	 * 去掉字符串左右两边的无效字符
	 * 
	 * @param str
	 *            待处理字符串
	 * @param invalidStr
	 *            无效字符组成的字符串
	 * @return 去除两边无效字符后的字符串
	 */
	public static String trim(String str, String invalidStr) {
		return ltrim(rtrim(str, invalidStr), invalidStr);
	}

	/**
	 * 去掉字符串左边的空白字符。遵循String类trim()方法的空白字符判定标准，认为ascii取值为0-32的都是空白字符。
	 * 
	 * @param str
	 *            待处理字符串
	 * @return 去除左边空白字符后的字符串
	 * @author miaofc
	 * @date Nov 22, 2011 11:11:27 AM
	 */
	public static String ltrim(String str) {

		// 不对于空字符串做处理
		if (str == null || str.length() == 0) {
			return str;
		}

		// 计算字符串切除位置
		int beginIndex = 0;
		while (beginIndex < str.length() && str.charAt(beginIndex) <= ' ') {
			beginIndex++;
		}
		return str.substring(beginIndex);
	}

	/**
	 * 去掉字符串右边的空白字符。遵循String类trim()方法的空白字符判定标准，认为ascii取值为0-32的都是空白字符。
	 * 
	 * @param str
	 *            待处理字符串
	 * @return 去除右侧空白字符后的字符串
	 * @author miaofc
	 * @date Nov 22, 2011 1:52:38 PM
	 */
	public static String rtrim(String str) {
		// 不对于空字符串做处理
		if (str == null || str.length() == 0) {
			return str;
		}

		// 计算字符串切除位置
		int endIndex = str.length();
		while (endIndex > 0 && str.charAt(endIndex - 1) <= ' ') {
			endIndex--;
		}
		return str.substring(0, endIndex);
	}

	/**
	 * 去掉字符串左右两边的空白字符。遵循String类trim()方法的空白字符判定标准，认为ascii取值为0-32的都是空白字符。
	 * 
	 * @param str
	 *            待处理字符串
	 * @return 去除两侧空白字符后的字符串
	 * @author miaofc
	 * @date Nov 22, 2011 1:57:50 PM
	 */
	public static String trim(String str) {
		return ltrim(rtrim(str));
	}

	/**
	 * 判断当前字符串是否为空。“为空”是指当字符串为null或为空串。
	 * 
	 * @param str
	 *            待判断字符串
	 * @return 字符串是否为空
	 * @author miaofc
	 * @date Nov 22, 2011 2:06:51 PM
	 */
	public static boolean isEmpty(String str) {
		// 判断当前字符串是否为null或为空串
		return str == null || str.length() == 0;
	}

	/**
	 * 判断字符串是否为数字。数字的范围包括正负、浮点与非浮点数字。
	 * 
	 * @param str
	 *            待判断字符串
	 * @return 字符串是否为数字
	 * @author miaofc
	 * @date Nov 22, 2011 2:11:22 PM
	 */
	public static boolean isNumber(String str) {
		return str != null && (str.matches("[+-]?[0-9]+\\.?[0-9]*") || str.matches("[+-]?[0-9]*\\.?[0-9]+"));
	}

	/**
	 * 判断字符串是否为整数。整数的范围包括正负整数，无数值上限或下限。
	 * 
	 * @param str
	 *            待判断字符串
	 * @return 字符串是否为整数
	 * @author miaofc
	 * @date Nov 22, 2011 2:22:24 PM
	 */
	public static boolean isInteger(String str) {
		return str != null && str.matches("[+-]?[0-9]+");
	}

	/**
	 * 判断字符串是否为自然数。自然数的范围为非负整数。
	 * 
	 * @param str
	 *            待判断字符串
	 * @return 字符串是否为自然数
	 * @author miaofc
	 * @date Nov 22, 2011 2:24:15 PM
	 */
	public static boolean isNaturalNumber(String str) {
		return str != null && str.matches("+?[0-9]+");
	}

	/**
	 * 判断字符串是否为布尔值
	 * 
	 * @param str
	 *            待判断字符串
	 * @return 字符串是否为布尔值
	 * @author miaofc
	 * @date Nov 22, 2011 2:35:11 PM
	 */
	public static boolean isBoolean(String str) {
		// 判断字符串是否为“true”或“false”
		return str != null && ("true".equals(str) || "false".equals(str));
	}

	/**
	 * 判断字符串是否仅由拉丁字母(a-z,A-Z)组成
	 * @param str 待判断字符串
	 * @return 字符串是否仅由字母组成
	 * @author miaofc
	 * @date Nov 22, 2011 2:44:12 PM
	 */
	public static boolean isLetters(String str) {
		return str != null && str.matches("[a-zA-z]+");
	}

	/**
	 * 判断字符串str是否包含某字符串value。当被判断字符串或被包含字符串为null时，方法返回false。空串被所有字符串包含。
	 * 
	 * @param str
	 *            待判断字符串
	 * @param value
	 *            被包含字符串
	 * @return 被包含字符串第一次出现的位置。如果返回值为-1，则说明str不包含value
	 * @author miaofc
	 * @date Nov 22, 2011 2:56:18 PM
	 */
	public static boolean contains(String str, String value) {
		// 如果待判断字符串或被包含字符串为null，则返回false
		if (str == null || value == null) {
			return false;
		}
		// 如果被包含字符串为空串，则返回true
		if ("".equals(value)) {
			return false;
		}
		// 调用String的API
		int index = str.indexOf(value);
		if (index == -1) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * 对字符串进行硬编码，使用系统默认字符集对于字符串解码后，使用指定字符集对字符集编码
	 * 
	 * @param str
	 *            请求参数
	 * @param destCharset
	 *            字符集
	 * @return 按照字符集重新编码后的字符串
	 * @throws Exception
	 * @author miaofc
	 * @date Nov 22, 2011 2:56:18 PM
	 */
	public static String encodeString(String str, String destCharset) throws Exception {
		// 若参数为null，则直接返回null
		if (str == null) {
			return null;
		}
		// 先使用系统字符集解码后，再使用charset字符集硬编码
		else {
			return new String(str.getBytes(), destCharset);
		}
	}

	/**
	 * 对字符串进行硬编码
	 * @param str 待编码字符串
	 * @param srcCharset 原字符集
	 * @param destCharset 目标字符集
	 * @return 编码后的字符串
	 * @throws Exception
	 * @author miaofc
	 * @date Nov 25, 2011 2:55:04 PM
	 */
	public static String encodeString(String str, String srcCharset, String destCharset) throws Exception {
		// 若参数为null，则直接返回null
		if (str == null) {
			return null;
		}
		// 先使用srcCharset解码后，再使用destCharset字符集硬编码
		else {
			return new String(str.getBytes(srcCharset), destCharset);
		}
	}

	/**
	 * 驼峰式字符串转化为下划线字符串
	 * 
	 * @param str
	 *            待转换字符串
	 * @return 转化后的数据库字段名
	 * @author miaofc
	 * @date Nov 22, 2011 2:56:18 PM
	 */
	public static String camel2Underline(String str) {
		// 如果字符串为空，则直接返回null
		if (str == null || "".equals(str)) {
			return null;
		}
		String result = "";
		// 遍历字符串
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			// 当遇到“驼峰”时，则相应的在字段字符串中添加下划线
			if (c >= 'A' && c <= 'Z') {
				result += "_" + c;
			}
			// 其他情况直接复制当前字符
			else {
				result += c;
			}
		}
		return result.toUpperCase();
	}

	/**
	 * 普通字符串转化为下划线字符串。转化过程中并不按照大写字母来作为一个单词的开始，而使用splitIndex来标志各个单词的开始位置。
	 * 
	 * @param str
	 *            待转换字符串
	 * @param splitIndexes
	 *            切割索引，标志着属性名中的各个单词的开始位置
	 * @return 转化后的数据库字段字符串
	 * @author miaofc
	 * @date Nov 22, 2011 2:56:18 PM
	 */
	public static String string2Underline(String str, int[] splitIndexes) {
		// 如果字符串为空，则直接返回null
		if (str == null || "".equals(str) || str.length() < splitIndexes.length) {
			return null;
		}
		String result = "";
		// 遍历待转换字符串
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			boolean split = false;
			// 遍历切割索引
			for (int j = 0; j < splitIndexes.length; j++) {
				// 如果字符串遍历位置等于当前切割索引的值，则退出遍历切割索引，进行切割与转化
				if (i == splitIndexes[j]) {
					split = true;
					break;
				}
			}
			// 如果是在找到了切割位置时，进行了退出，则认为是新单词的开始，在转化后的字段字符串中拼接下划线
			if (split) {
				result += "_" + c;
			}
			// 其他情况则直接复制当前字符
			else {
				result += c;
			}
		}
		return result.toUpperCase();
	}

	/**
	 * 下划线字符串转化为驼峰式字符串
	 * 
	 * @param str
	 *            待转换字符串，数据库字段名
	 * @return 转化后的java对象属性名
	 * @author miaofc
	 * @date Nov 22, 2011 2:56:18 PM
	 */
	public static String underline2Camel(String str) {
		// 如果字段名为空，则返回null
		if (str == null || "".equals(str)) {
			return null;
		}
		str = str.toLowerCase();
		// 根据下划线拆分出单词数组
		String[] values = str.split("_");
		String result = "";
		// 遍历单词数组
		for (int i = 0; i < values.length; i++) {
			// 对于第一个字母不做操作，直接拷贝至结果字符串中
			if (i == 0) {
				result += values[i];
			}
			// 如果不是第一个单词，则将首字母大写
			else {
				result += values[i].substring(0, 1).toUpperCase() + values[i].substring(1);
			}
		}
		return result;
	}

	/**
	 * 将十六进制串转换成字符串，源十六进制字符串为先根据每位上字符所代表的数值解析成byte数组，然后再生成相应的字符串
	 * 
	 * @param hexStr
	 *            十六进制字符串
	 * @return 相应字符串
	 * @author miaofc
	 * @throws UnsupportedEncodingException
	 * @date Nov 24, 2011 12:05:52 PM
	 */
	public static String hexStr2String(String hexStr) throws UnsupportedEncodingException {
		byte[] out = hexStr2ByteArray(hexStr);
		return new String(out);
	}

	/**
	 * 将十六进制串转换成字符串，源十六进制字符串为先根据每位上字符所代表的数值解析成byte数组，然后再生成相应的字符串
	 * 
	 * @param hexStr
	 *            十六进制字符串
	 * @param charset
	 *            字符串编码字符集
	 * @return 相应字符串
	 * @author miaofc
	 * @throws UnsupportedEncodingException
	 * @date Nov 24, 2011 12:06:49 PM
	 */
	public static String hexStr2String(String hexStr, String charset) throws UnsupportedEncodingException {
		byte[] out = hexStr2ByteArray(hexStr);
		return new String(out, charset);
	}

	/**
	 * 将十六进制串转换成byte数组
	 * 
	 * @param hexStr
	 *            源十六进制字符串
	 * @return 相应的字节数组
	 * @author miaofc
	 * @date Nov 28, 2011 5:02:20 PM
	 */
	public static byte[] hexStr2ByteArray(String hexStr) {
		hexStr = hexStr.toUpperCase();
		char[] data = hexStr.toCharArray();
		int len = data.length;

		byte[] out = new byte[len / 2];
		for (int i = 0; i < len; i = i + 2) {
			String strTmp = new String(data, i, 2);
			out[i / 2] = (byte) Integer.parseInt(strTmp, 16);
		}
		return out;
	}

	/**
	 * 将字符串转换成十六进制串，方法会首先根据系统默认字符集对于源字符串进行解码，生成byte数组，然后将byte数组的每个元素为一个字符，生成十六进制数字组成的字符串
	 * 
	 * @param str
	 *            源字符串
	 * @return 十六进制字符串
	 * @author miaofc
	 * @throws UnsupportedEncodingException
	 * @date Nov 24, 2011 12:07:53 PM
	 */
	public static String string2HexStr(String str) throws UnsupportedEncodingException {
		byte[] data = str.getBytes();
		return byteArray2HexStr(data);
	}

	/**
	 * 将字符串转换成十六进制串，方法会首先根据制定字符集对于源字符串进行解码，生成byte数组，然后将byte数组的每个元素为一个字符，生成十六进制数字组成的字符串
	 * 
	 * @param str
	 *            源字符串
	 * @param charset
	 *            解码字符集
	 * @return 十六进制字符串
	 * @author miaofc
	 * @throws UnsupportedEncodingException
	 * @date Nov 24, 2011 12:08:05 PM
	 */
	public static String string2HexStr(String str, String charset) throws UnsupportedEncodingException {
		byte[] data = str.getBytes(charset);
		return byteArray2HexStr(data);
	}

	/**
	 * 将字符串的byte数组转换成十六进制串
	 * 
	 * @param data
	 *            byte数组
	 * @return 十六进制字符串
	 * @author miaofc
	 * @date Nov 28, 2011 4:40:26 PM
	 */
	public static String byteArray2HexStr(byte[] data) {
		char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
		int l = data.length;
		char[] out = new char[l << 1];
		// two characters form the hex value.
		for (int i = 0, j = 0; i < l; i++) {
			out[j++] = digits[(0xF0 & data[i]) >>> 4];
			out[j++] = digits[0x0F & data[i]];
		}
		return new String(out);
	}

}