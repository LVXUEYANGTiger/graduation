package yang.org.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author LvXueYang
 * @description 常用的对象操作方法
 */
public class ObjectMethods {
    /**
     * @param object: 传进来的对象
     * @return Map<Field, Method> 字段和对应的get方法
     * @description 根据传进来对象获取属性名和对应的get方法
     * @author LvXueYang
     * @date 2021/12/6 10:46
     */
    public static Map<Field, Method> getObjectAndGetMethod(Object object) {
        final String booleanValue = "boolean";
        Map<Field, Method> map = new HashMap<>(16);
        // 获取这个对象的类
        Class<?> clazz = object.getClass();
        Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
            // 获取是否可访问
            boolean flag = field.isAccessible();
            try {
                field.setAccessible(true);
                // 获取属性名
                String name = field.getName();
                // 拼装方法
                String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
                Method getMethod;
                // 布尔值特殊
                if (booleanValue.equals(field.getType().getName())) {
                    getMethod = clazz.getMethod("is" + methodName);
                } else {
                    getMethod = clazz.getMethod("get" + methodName);
                }
                map.put(field, getMethod);
                // 恢复访问权限
                field.setAccessible(flag);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
        return map;
    }

    /**
     * @param object: 对象
     * @param list:   对象的属性列表
     * @return Map<Field, Method>
     * @description 通过list传下来属性值然后获取对象的get方法和对应属性
     * @author LvXueYang
     * @date 2021/12/6 15:56
     */
    public static Map<Field, Method> getObjectAndGetMethod(Object object, List<String> list) {
        Map<Field, Method> map = new HashMap<>(16);
        // 获取这个对象的类
        Class<?> clazz = object.getClass();
        Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
            // 获取是否可访问
            boolean flag = field.isAccessible();
            try {
                field.setAccessible(true);
                // 获取属性名
                String name = field.getName();
                if (list.contains(name)) {
                    // 拼装方法
                    String methodName = name.substring(0, 1).toUpperCase() + name.substring(1);
                    Method getMethod = clazz.getMethod("get" + methodName);
                    map.put(field, getMethod);
                }
                // 恢复访问权限
                field.setAccessible(flag);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
        return map;
    }

    /**
     * @param object: 传进来的对象
     * @return List<String>
     * @description 传进来一个对象获取它的非空属性 注意只能属性值都是对象
     * @author LvXueYang
     * @date 2021/12/6 16:06
     */
    public static List<String> getObjectFiled(Object object) {
        Class<?> clazz = object.getClass();
        List<String> list = new ArrayList<>();
        Arrays.stream(clazz.getDeclaredFields()).forEach(field -> {
            // 获取是否可访问
            boolean flag = field.isAccessible();
            field.setAccessible(true);
            // 获取属性名
            String name = field.getName();
            try {
                if (field.get(object) != null) {
                    list.add(name);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            // 恢复访问权限
            field.setAccessible(flag);
        });
        return list;
    }
}
