package personal.xzr.practice.util;

import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xiangzhurui
 * @version 2017/11/24
 */
public class MapToMap {

    private Map<String, Object> nameObjectMap;

    //Map<key,$.class.field>
    public Map<String, Object> transMap(Map<String, String> input) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Set<String> classNameSet = Sets.newHashSet(input.values()).stream().map(new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.split(".")[1];
            }
        }).collect(Collectors.toSet());

        for (String className : classNameSet) {
            Method method = this.getClass().getMethod("get" + className, new Class[]{});
            Object obj = method.invoke(this, null);
            nameObjectMap.put(className, obj);
        }
        Map<String, Object> out = new HashMap<>();
        for (Map.Entry<String, String> entry : input.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            String[] arr = v.split(".");
            String clazzName = arr[1];
            String fieldName = arr[2];

            Object obj = nameObjectMap.get(clazzName);
            Method method = obj.getClass().getMethod("get" + StringUtils.uncapitalize(fieldName), new Class<?>[]{});
            Class clazz = method.getReturnType();
            Object fieldObj = method.invoke(obj);
            fieldObj = getTypeData(clazz, fieldObj);
            out.put(k, fieldObj);
        }
        return out;
    }

    public <T> T getTypeData(T t, Object obj) {
        return (T) obj;
    }
}
