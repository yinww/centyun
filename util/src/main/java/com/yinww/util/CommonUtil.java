package com.yinww.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CommonUtil {
	
	public static boolean isEmpty(String str) {
		return str == null || str.trim().length() == 0;
	}

	public static <T> boolean isEmpty(Collection<T> values) {
		return values == null || values.size() == 0;
	}

	/**
	 * 将datas按splitLen的大小拆分成多个
	 * @param datas
	 * @param splitLen
	 * @return
	 */
	public static <T> List<List<T>> splitData(List<T> datas, int splitLen) {
		int size = datas.size() / splitLen;
		List<List<T>> result = new ArrayList<List<T>>(size+1);
		for (int i = 0; i < size; i++) {
			List<T> item = new ArrayList<T>();
			item.addAll(datas.subList(i*splitLen, (i+1)*splitLen));
			result.add(item);
		}

		if(datas.size() % splitLen > 0) {
			List<T> item = new ArrayList<T>();
			item.addAll(datas.subList(size*splitLen, datas.size()));
			result.add(item);
		}
		return result;
	}

}
