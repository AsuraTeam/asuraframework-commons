/**
 * Copyright 2018 asura
 */
package org.asuraframework.commons.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.sun.istack.internal.NotNull;
import com.sun.istack.internal.Nullable;

import java.util.List;

/**
 * <p>
 *   统一的JSON序列化接口
 * </p>
 *
 *
 * @author liusq23
 * @since 1.0
 * @version 1.0
 */
public class Json {
    public enum JsonStyle{
        JackJSon,FastJson;
    }
    public static JsonInterface createProduct(JsonStyle jsonStyle) {
        JsonInterface product = null;
        if(JsonStyle.JackJSon ==jsonStyle){
            product= JackJsonFormat.getInstance();
        }
        return product;
    }

    public static JsonInterface createProduct() {
        return createProduct(JsonStyle.JackJSon);
    }
    /**
     * 将json转换成Object
     *
     * @param json  要转换的json数据
     * @param clazz 目标实体
     * @return Entity    转换后的实体对象
     */
    public static <T> T parseObject(@NotNull String json, @NotNull Class<T> clazz) {
        return  createProduct().parseObject(json,clazz);
    }
    /**
     * 将自定义对象转json
     *
     * @param object 待转换的对象实体
     * @return json    转换后的json数据
     */
    public static String toJsonString(@NotNull Object object){
        return createProduct().toJsonString(object);
    }
    /**
     * json转换list通用
     * <p/>
     * 详细见：http://www.baeldung.com/jackson-collection-array
     * <p/>
     * One final note is that the <T> T class needs to have the no-args default constructor
     * if it doesn’t, Jackson will not be able to instantiate it:
     *
     * @param json  待转换的json串
     * @param clazz 需要转换的类类型
     * @return
     */
    public static <T> List<T> parseObjectList(@NotNull String json, @NotNull Class<T> clazz) {
        return createProduct().parseObjectList(json,clazz);
    }
    /**
     * 可以取出类似与fastjson JSONObject类似的一个node tree
     * path 为null时候 返回为 root json node
     * 例如：{"a":"a1","b":"b1","c":{"d":"d1","e":"e2"}}
     * 当path为null或者空时 返回jsonnode为：{"a":"a1","b":"b1","c":{"d":"d1","e":"e2"}}
     * 房path为c 返回jsonnode为： {"d":"d1","e":"e2"}
     *
     * @param json  json串
     * @param paths json key 路径path
     * @return
     */
    public static JsonNode parseJsonNode(@NotNull String json, @Nullable String... paths) {
        return createProduct().parseJsonNode(json,paths);
    }
    /**
     * 将json串内的某一个path下的json串转换为 object
     *
     * @param json
     * @param paths
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T getPathObject(@NotNull String json, @NotNull Class<T> t, @Nullable String... paths) {
        return createProduct().getPathObject(json,t,paths);
    }
    /**
     * 将json串内的某一个path下的json串转换为 object
     *
     * @param json
     * @param paths
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T getPathObject(@NotNull String json, @NotNull TypeReference typeReference, @Nullable String... paths) {
        return createProduct().getPathObject(json,typeReference,paths);
    }
    /**
     * 将json串内的某一个path下的json串转换为 List
     *
     * @param json
     * @param t
     * @param paths
     * @param <T>
     * @return
     */
    public static <T> List<T> getPathArray(@NotNull String json, @NotNull Class<T> t, @Nullable String... paths) {
        return createProduct().getPathArray(json,t,paths);
    }
    /**
     * 获取json串内的某一个path下的integer值
     *
     * @param json
     * @param paths
     * @return
     */
    public static Integer getInt(@NotNull String json, @Nullable String... paths) {
        return createProduct().getInt(json,paths);
    }

    public static Long getLong(@NotNull String json, @Nullable String... paths) {
        return createProduct().getLong(json,paths);
    }

    public static Short getShort(@NotNull String json, @Nullable String... paths) {
        return createProduct().getShort(json,paths);
    }

    public static Byte getByte(@NotNull String json, @Nullable String... paths) {
        return createProduct().getByte(json,paths);
    }

    public static Float getFloat(@NotNull String json, @Nullable String... paths) {
        return createProduct().getFloat(json,paths);
    }

    public static Double getDouble(@NotNull String json, @Nullable String... paths) {
        return createProduct().getDouble(json,paths);
    }

    public static Boolean getBoolean(@NotNull String json, @Nullable String... paths) {
        return createProduct().getBoolean(json,paths);
    }
    /**
     * 获取json传 K-V 的V值只适应于获取叶子节点的V值
     * 注意：如果{"a":"b","c":{"d":"d1","e":{"f","f1"}}}
     * 当 path为c时候,返回：{"d":"d1","e":{"f","f1"}}
     *
     * @param json
     * @param paths
     * @return
     */
    public static String getString(@NotNull String json, @Nullable String... paths) {
        return createProduct().getString(json,paths);
    }

}
