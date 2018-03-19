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
    public static IJson  createProduct(JsonStyle jsonStyle) {
        IJson product = null;
        if(JsonStyle.JackJSon ==jsonStyle){
            product=JackJson.getInstance();
        }
        return product;
    }

    public static IJson  createProduct() {
        return createProduct(JsonStyle.JackJSon);
    }

    public static <T> T parseObject(@NotNull String json, @NotNull Class<T> clazz) {
        return  createProduct().parseObject(json,clazz);
    }

    public static String toJsonString(@NotNull Object object){
        return createProduct().toJsonString(object);
    }

    public static <T> List<T> parseObjectList(@NotNull String json, @NotNull Class<T> clazz) {
        return createProduct().parseObjectList(json,clazz);
    }

    public static JsonNode parseJsonNode(@NotNull String json, @Nullable String... paths) {
        return createProduct().parseJsonNode(json,paths);
    }

    public static <T> T getPathObject(@NotNull String json, @NotNull Class<T> t, @Nullable String... paths) {
        return createProduct().getPathObject(json,t,paths);
    }

    public static <T> T getPathObject(@NotNull String json, @NotNull TypeReference typeReference, @Nullable String... paths) {
        return createProduct().getPathObject(json,typeReference,paths);
    }

    public static <T> List<T> getPathArray(@NotNull String json, @NotNull Class<T> t, @Nullable String... paths) {
        return createProduct().getPathArray(json,t,paths);
    }

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

    public static String getString(@NotNull String json, @Nullable String... paths) {
        return createProduct().getString(json,paths);
    }

}
