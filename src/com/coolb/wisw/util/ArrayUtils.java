package com.coolb.wisw.util;

import org.apache.log4j.Logger;

/**
 * <P>
 * 数组处理工具，负责处理常见的数组（Arrays）操作，包括数组的转化、计算等。
 * </p>
 * 
 * @author iAngus
 * @version 2.0, Nov 23, 2011
 * @since
 */
public class ArrayUtils {

	/**
	 * 日志对象
	 */
	private static final Logger log = Logger.getLogger(ArrayUtils.class);

	/**
	 * 向数组末尾添加元素
	 * 
	 * @param <T>
	 *            数组元素类型
	 * @param array
	 *            原始数组
	 * @param element
	 *            要添加的元素
	 * @return 添加元素后的数组
	 * @author miaofc
	 * @date Nov 23, 2011 1:56:37 PM
	 */
	public static <T> T[] add(T[] array, T element) {
		T[] newArray = (T[]) new Object[array.length + 1];
		System.arraycopy(array, 0, newArray, 0, array.length);
		newArray[newArray.length - 1] = element;
		return newArray;
	}

	/**
	 * 向数组中的指定位置添加元素。数组位置的计算从0开始。
	 * 
	 * @param <T>
	 *            数组元素类型
	 * @param array
	 *            原始数组
	 * @param index
	 *            添加元素位置
	 * @param element
	 *            要添加的元素
	 * @return 添加元素后的数组
	 * @throws IndexOutOfBoundsException
	 * @author miaofc
	 * @date Nov 23, 2011 2:07:08 PM
	 */
	public static <T> T[] add(T[] array, int index, T element) throws IndexOutOfBoundsException {
		// 如果index超出数组长度，则抛出异常
		if (index < 0 || index > array.length) {
			throw new IndexOutOfBoundsException("index超出数组长度[" + array.length + "," + index + "]");
		}
		T[] newArray = (T[]) new Object[array.length + 1];
		// 将插入位置之前的元素复制到新数组
		System.arraycopy(array, 0, newArray, 0, index);
		// 将插入位置之后的元素复制到新数组
		System.arraycopy(array, index, newArray, index + 1, array.length - index);
		// 插入新元素
		newArray[index] = element;
		return newArray;
	}

	/**
	 * 向数组中的指定位置添加元素。数组位置的计算从0开始。
	 * 
	 * @param <T>
	 *            数组元素类型
	 * @param array
	 *            原始数组
	 * @param index
	 *            添加元素位置
	 * @param elements
	 *            要添加的元素
	 * @return 添加元素后的数组
	 * @throws IndexOutOfBoundsException
	 * @author miaofc
	 * @date Nov 25, 2011 2:34:55 PM
	 */
	public static <T> T[] add(T[] array, int index, T[] elements) throws IndexOutOfBoundsException {
		// 如果index超出数组长度，则抛出异常
		if (index < 0 || index > array.length) {
			throw new IndexOutOfBoundsException("index超出数组长度[" + array.length + "," + index + "]");
		}
		T[] newArray = (T[]) new Object[array.length + elements.length];
		// 将插入位置之前的元素复制到新数组
		System.arraycopy(array, 0, newArray, 0, index);
		// 将新元素插入数组
		System.arraycopy(elements, 0, newArray, index, elements.length);
		// 将插入位置之后的元素复制到新数组
		System.arraycopy(array, index, newArray, index + elements.length, array.length - index);
		return newArray;
	}

	/**
	 * 删除数组中的元素。对于引用数据类型的变量使用哈希值判断元素是否相等，如果元素在数组中出现多次，则仅删除最开始的一个
	 * 
	 * @param <T>
	 *            数组元素类型
	 * @param array
	 *            原始数组
	 * @param element
	 *            要删除的元素
	 * @return 删除元素后的数组
	 * @author miaofc
	 * @date Nov 23, 2011 2:02:17 PM
	 */
	public static <T> T[] remove(T[] array, T element) {
		// 遍历原数组，查找元素第一次出现位置
		int index = -1;
		for (int i = 0; array != null && i < array.length; i++) {
			// 判断当前元素是否为要删除的元素
			if (array[i] == element) {
				index = i;
				break;
			}
		}
		// 如果数组中有要删除的元素，则删除该元素
		if (index >= 0) {
			return remove(array, index);
		}
		// 如果未在数组中找到要删除的元素，则返回原数组
		else {
			return array;
		}
	}

	/**
	 * 删除数组中指定位置的元素。数组位置的计算从0开始。
	 * 
	 * @param <T>
	 *            数组元素类型
	 * @param array
	 *            原始数组
	 * @param index
	 *            要删除的元素位置
	 * @return 删除元素后的数组
	 * @throws IndexOutOfBoundsException
	 * @author miaofc
	 * @date Nov 23, 2011 2:05:28 PM
	 */
	public static <T> T[] remove(T[] array, int index) throws IndexOutOfBoundsException {
		// 如果index超出数组长度，则抛出异常
		if (index < 0 || index > array.length) {
			throw new IndexOutOfBoundsException("index超出数组长度[" + array.length + "," + index + "]");
		}
		T[] newArray = (T[]) new Object[array.length - 1];
		// 将删除位置之前的元素复制到新数组
		System.arraycopy(array, 0, newArray, 0, index);
		// 将删除位置之后的元素复制到新数组
		System.arraycopy(array, index, newArray, index - 1, array.length - index);
		return newArray;
	}

	/**
	 * 判断数组是否包含某元素。对于引用数据类型的变量使用equals()判断元素是否相等。
	 * 
	 * @param <T>
	 *            数组元素类型
	 * @param array
	 *            原始数组
	 * @param element
	 *            要查找的元素
	 * @return 数组中是否包含该元素
	 * @author miaofc
	 * @date Nov 23, 2011 5:28:12 PM
	 */
	public static <T> boolean contains(T[] array, T element) {
		// 遍历数组，搜索目标元素
		for (int i = 0; i < array.length; i++) {
			// 如果找到目标元素则返回true
			if (array[i].equals(element)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断数组是否包含某些元素
	 * 
	 * @param <T>
	 *            数组元素类型
	 * @param array
	 *            源数组
	 * @param elements
	 *            目标元素
	 * @return 数组是否包含目标元素
	 * @author miaofc
	 * @date Nov 25, 2011 2:35:49 PM
	 */
	public static <T> boolean containsAll(T[] array, T[] elements) {
		// 遍历要检索的数组，检查是否存在于原始数组中
		for (int i = 0; i < elements.length; i++) {
			// 遍历原始数组，查找是否有当前的被检索元素
			boolean isInArray = false;
			for (int j = 0; j < array.length; j++) {
				// 如果找到该元素，则将检索标志置为true
				if (elements[i] == array[i]) {
					isInArray = true;
					break;
				}
			}
			// 如果为找到当前被检索元素，则不再继续遍历被检索数组，而返回false
			if (!isInArray) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 拼接两个数组
	 * 
	 * @param <T>
	 *            数组元素类型
	 * @param firstArray
	 *            第一个数组
	 * @param secondArray
	 *            第二个数组
	 * @return 拼接后的数组
	 * @author miaofc
	 * @date Nov 23, 2011 2:13:59 PM
	 */
	public static <T> T[] concat(T[] firstArray, T[] secondArray) {
		T[] newArray = (T[]) new Object[firstArray.length + secondArray.length];
		System.arraycopy(firstArray, 0, newArray, 0, firstArray.length);
		System.arraycopy(secondArray, 0, newArray, firstArray.length, secondArray.length);
		return newArray;
	}

	/**
	 * 将字符串数组转化成为长整型（long）数组
	 * 
	 * @param stringArray
	 *            字符串数组
	 * @return 转化后的长整形数组
	 * @throws NumberFormatException
	 * @author miaofc
	 * @date Nov 23, 2011 2:17:11 PM
	 */
	public static long[] stringArray2LongArray(String[] stringArray) throws NumberFormatException {
		long[] longArray = new long[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			longArray[i] = Long.parseLong(stringArray[i]);
		}
		return longArray;
	}

	/**
	 * 将字符串数组转化成为整型（int）数组
	 * 
	 * @param stringArray
	 *            字符串数组
	 * @return 转化后的整形数组
	 * @throws NumberFormatException
	 * @author miaofc
	 * @date Nov 23, 2011 2:20:53 PM
	 */
	public static int[] stringArray2IntArray(String[] stringArray) throws NumberFormatException {
		int[] intArray = new int[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			intArray[i] = Integer.parseInt(stringArray[i]);
		}
		return intArray;
	}

	/**
	 * 将字符串数组转化成为短整型（short）数组
	 * 
	 * @param stringArray
	 *            字符串数组
	 * @return 转化后的短整形数组
	 * @throws NumberFormatException
	 * @author miaofc
	 * @date Nov 23, 2011 2:21:15 PM
	 */
	public static short[] stringArray2ShortArray(String[] stringArray) throws NumberFormatException {
		short[] shortArray = new short[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			shortArray[i] = Short.parseShort(stringArray[i]);
		}
		return shortArray;
	}

	/**
	 * 将字符串数组转化成为双精度浮点型（double）数组
	 * 
	 * @param stringArray
	 *            字符串数组
	 * @return 转化后的双精度浮点型数组
	 * @throws NumberFormatException
	 * @author miaofc
	 * @date Nov 23, 2011 2:21:33 PM
	 */
	public static double[] stringArray2DoubleArray(String[] stringArray) throws NumberFormatException {
		double[] doubleArray = new double[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			doubleArray[i] = Double.parseDouble(stringArray[i]);
		}
		return doubleArray;
	}

	/**
	 * 将字符串数组转化成为单精度浮点型（float）数组
	 * 
	 * @param stringArray
	 *            字符串数组
	 * @return 转化后的单精度浮点型数组
	 * @throws NumberFormatException
	 * @author miaofc
	 * @date Nov 23, 2011 2:22:03 PM
	 */
	public static float[] stringArray2FloatArray(String[] stringArray) throws NumberFormatException {
		float[] floatArray = new float[stringArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			floatArray[i] = Float.parseFloat(stringArray[i]);
		}
		return floatArray;
	}

	/**
	 * 将长整型数组转化为字符串型数组
	 * 
	 * @param longArray
	 *            长整型数组
	 * @return 转化后的字符串型数组
	 * @author miaofc
	 * @date Nov 23, 2011 2:25:10 PM
	 */
	public static String[] longArray2StringArray(long[] longArray) {
		String[] stringArray = new String[longArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = String.valueOf(longArray[i]);
		}
		return stringArray;
	}

	/**
	 * 将整型数组转化为字符串型数组
	 * 
	 * @param intArray
	 *            整型数组
	 * @return 转化后的字符串型数组
	 * @author miaofc
	 * @date Nov 23, 2011 2:25:52 PM
	 */
	public static String[] intArray2StringArray(int[] intArray) {
		String[] stringArray = new String[intArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = String.valueOf(intArray[i]);
		}
		return stringArray;
	}

	/**
	 * 将短整型数组转化为字符串型数组
	 * 
	 * @param shortArray
	 *            短整型数组
	 * @return 转化后的字符串型数组
	 * @author miaofc
	 * @date Nov 23, 2011 2:26:20 PM
	 */
	public static String[] shortArray2StringArray(short[] shortArray) {
		String[] stringArray = new String[shortArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = String.valueOf(shortArray[i]);
		}
		return stringArray;
	}

	/**
	 * 将双精度浮点型数组转化为字符串型数组
	 * 
	 * @param doubleArray
	 *            双精度浮点型数组
	 * @return 转化后的字符串型数组
	 * @author miaofc
	 * @date Nov 23, 2011 2:26:44 PM
	 */
	public static String[] doubleArray2StringArray(double[] doubleArray) {
		String[] stringArray = new String[doubleArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = String.valueOf(doubleArray[i]);
		}
		return stringArray;
	}

	/**
	 * 将单精度浮点型数组转化为字符串型数组
	 * 
	 * @param floatArray
	 *            单精度浮点型数组
	 * @return 转化后的字符串型数组
	 * @author miaofc
	 * @date Nov 23, 2011 2:27:20 PM
	 */
	public static String[] floatArray2StringArray(float[] floatArray) {
		String[] stringArray = new String[floatArray.length];
		for (int i = 0; i < stringArray.length; i++) {
			stringArray[i] = String.valueOf(floatArray[i]);
		}
		return stringArray;
	}
	
}
