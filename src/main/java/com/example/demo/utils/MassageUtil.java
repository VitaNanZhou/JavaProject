package com.example.demo.utils;


import org.reflections.Reflections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * 错误码工具类
 */
public class MassageUtil {
    private final static String METHOD="values";
    public final static String KEY_METHOD="getKey";
    public final static String EN_DESCRIPTION_METHOD="getEnDescription";
    public final static String ZH_DESCRIPTION_METHOD="getZhDescription";

    /**
     * 获取所有实现类
     * @param scanPackageName 扫描包路径
     * @param type 父接口类型
     * @return 所有实现类
     * @param <R> 父接口类型
     */
    public static <R> Set<Class<? extends R>> getAllImpl(String scanPackageName,Class<R> type){
        Reflections reflections = new Reflections(scanPackageName);
        Set<Class<? extends R>> classes = reflections.getSubTypesOf(type);
        return classes;
    }

    /**
     * 获取所有枚举类
     * @param scanPackageName 扫描包路径
     * @param type 接口类型
     * @param function 回调函数
     * @return 返回错误码集合
     * @param <R> 接口类型
     * @param <ErrorMsg> 错误码对象
     * @throws Exception
     */
    public static <R,ErrorMsg> List<ErrorMsg> getAllImplEnums(String scanPackageName, Class<R> type, BiFunction<Object,Class<? extends R>,ErrorMsg> function)  throws Exception{
        Set<Class<? extends R>> allImpl = getAllImpl(scanPackageName, type);
        List<ErrorMsg> msgs = new ArrayList<>(allImpl.size());
        Iterator<Class<? extends R>> iterator = allImpl.iterator();
        while (iterator.hasNext()){
            Class<? extends R> next = iterator.next();
            Method values = next.getDeclaredMethod(METHOD);
            Object[] childs = (Object[]) values.invoke(next);
            for (int i = 0; i < childs.length; i++) {
                ErrorMsg errorMsg = function.apply(childs[i], next);
                msgs.add(errorMsg);
            }
        }
        return msgs;
    }
}
