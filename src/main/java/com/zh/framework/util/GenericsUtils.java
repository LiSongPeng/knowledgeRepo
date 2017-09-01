package com.zh.framework.util;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * 泛型工具类
 *
 * @author JChang
 *
 * @notes Created on 2017-9-1
 */
public class GenericsUtils {

    public static Class getSuperClassGenricType(Class clazz, int index) {
        Type genType = clazz.getGenericSuperclass();// 得到泛型父类
        // 如果没有实现ParameterizedType接口，即不支持泛型，直接返回Object.class
        if (!(genType instanceof ParameterizedType)) {
            return Object.class;
        }
        // 返回表示此类型实际类型参数的Type对象的数组,数组里放的都是对应类型的Class, 如BuyerServiceBean extends
        // DaoSupport<Buyer,Contact>就返回Buyer和Contact类型
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        if (index >= params.length || index < 0) {
            throw new RuntimeException("你输入的索引" + (index < 0 ? "不能小于0" : "超出了参数的总数"));
        }
        if (!(params[index] instanceof Class)) {
            return Object.class;
        }
        return (Class) params[index];
    }

    public static Class getSuperClassGenricType(Class clazz) {
        return getSuperClassGenricType(clazz, 0);
    }
    /**
     * 根据实体得到实体的所有属性
     * @param objClass
     * @return
     * @throws ClassNotFoundException
     */
    public static String[] getColumnNames(String objClass) throws ClassNotFoundException {
        String[] wageStrArray = null;
        if (objClass != null) {
            Class class1 = Class.forName(objClass);
            Field[] field = class1.getDeclaredFields();// 这里便是获得实体Bean中所有属性的方法
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < field.length; i++) {// 这里不多说了

                sb.append(field[i].getName());

                // 这是分割符 是为了去掉最后那个逗号

                // 比如 如果不去最后那个逗号 最后打印出来的结果是 "id,name,"

                // 去了以后打印出来的是 "id,name"
                if (i < field.length - 1) {
                    sb.append(",");

                }
            }

            // split(",");这是根据逗号来切割字符串使字符串变成一个数组

            wageStrArray = sb.toString().split(",");
            return wageStrArray;
        } else {
            return wageStrArray;
        }
    }
}