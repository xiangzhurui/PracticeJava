package basic.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 复制一个对象同名（忽略类成员变量名称大小写）变量的值到另一个
 * 
 * @author XiangZhuRui
 *
 */
public class BeanCopysUtils {
	private static final Logger log = LoggerFactory.getLogger(BeanCopysUtils.class);

	/**
	 * 复制类成员变量的值，并返回目标类型的对象
	 * 
	 * @param target
	 * @param src
	 * @param srcInstance
	 * @return
	 */
	public static <T, S> T copy(Class<T> target, Class<S> src, S srcInstance) {
		if (srcInstance == null) {
			return null;
		}
		T targetInstance = null;
		try {
			targetInstance = target.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		Map<String, Field> targetMapping = getNameFieldMapping(target);
		Set<String> targetLowerCaseNames = targetMapping.keySet();

		Map<String, Field> srcMapping = getNameFieldMapping(src);
		Set<String> srcLowerCaseNames = srcMapping.keySet();
		// 成员变量交集
		boolean b = srcLowerCaseNames.retainAll(targetLowerCaseNames);
		log.info("交集运算结果是：[{}]", b);

		for (String srcName : srcLowerCaseNames) {
			Method srcMethod = null;
			try {
				srcMethod = src.getDeclaredMethod("get" + captureName(srcName));
			} catch (NoSuchMethodException e) {
				log.info("没有{}的getXxx方法，处理下一个Field", srcName);
				continue;
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			// 使用getXxx取值
			Object srcValue = null;
			try {
				srcValue = srcMethod.invoke(srcInstance);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			if (null == srcValue) {
				log.debug("源值域{}为空,跳过", srcName);
				continue;
			}
			// 准备调用setXxx方法赋值
			Field targetField = targetMapping.get(srcName);
			Class<?> targetType = targetField.getType();
			Method targetMethod = null;
			try {
				targetMethod = target.getMethod("set" + targetField.getName(), targetType);
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
			try {
				targetMethod.invoke(targetInstance, srcValue);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
		}

		return targetInstance;
	}

	private static <T> Map<String, Field> getNameFieldMapping(Class<T> schema) {
		Map<String, Field> mapping = new HashMap<>();
		Field[] fields = schema.getDeclaredFields();
		for (Field f : fields) {
			mapping.put(f.getName().toLowerCase(), f);
		}
		return mapping;
	}

	/**
	 * 首字母转为大写
	 * 
	 * @param name
	 * @return
	 */
	private static String captureName(String name) {
		char[] cs = name.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}
}
