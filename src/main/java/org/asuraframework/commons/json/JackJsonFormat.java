/**
 * Copyright 2018 asura
 */
package org.asuraframework.commons.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import org.asuraframework.commons.date.DatePattern;
import org.asuraframework.commons.exception.JsonTransformException;
import org.asuraframework.commons.util.Check;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

;

/**
 * <p>
 *   统一的JSON序列化接口
 * </p>
 *
 *
 * @author jiangn18
 * @version V1.0
 * @date 2018/3/19 19:43
 */
public class JackJsonFormat implements JsonInterface {
    private static JackJsonFormat jackJsonFormat;
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        //设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //当为空字符串的时候 可以反序列化为null
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        //序列化时候为空报错
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        //日期
        objectMapper.setDateFormat(new SimpleDateFormat(DatePattern.DEFAULT_ISO8601_FORMAT));
    }

    private JackJsonFormat() {

    }


    public synchronized static JackJsonFormat getInstance(){
        if(jackJsonFormat==null){
            jackJsonFormat=new JackJsonFormat();
        }
        return  jackJsonFormat;
    }

    /**
     * 将json转换成Object
     *
     * @param json  要转换的json数据
     * @param clazz 目标实体
     * @return Entity    转换后的实体对象
     */
    @Override
    public  <T> T parseObject(@Nonnull String json, @Nonnull Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonTransformException("Json转换成Object时出现异常", e);
        }
    }

    /**
     * 将自定义对象转json
     *
     * @param object 待转换的对象实体
     * @return json    转换后的json数据
     */
    @Override
    public  String toJsonString(@Nonnull Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new JsonTransformException("Entity转换成Json时出现异常", e);
        }
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
    @Override
    public  <T> List<T> parseObjectList(@Nonnull String json, @Nonnull Class<T> clazz) {
        try {
            return objectMapper.readValue(json, new TypeReference<List<T>>() {
            });
        } catch (IOException e) {
            throw new JsonTransformException("Json转换成List<T>异常。", e);
        }
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
    @Override
    public  JsonNode parseJsonNode(@Nonnull String json, @Nullable String... paths) {
        try {
            JsonNode rootNode = objectMapper.readTree(json);
            if (!Check.isNullOrEmpty(paths)) {
                for (String pt : paths) {
                    rootNode = rootNode.path(pt);
                    //如果不存在
                    if (rootNode.isMissingNode()) {
                        return null;
                    }
                }
            }
            return rootNode;
        } catch (IOException e) {
            throw new JsonTransformException("Json获取JsonNode时出现异常。", e);
        }
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
    @Override
    public  <T> T getPathObject(@Nonnull String json, @Nonnull Class<T> t, @Nullable String... paths) {
        JsonNode jsonNode = parseJsonNode(json, paths);
        if (Check.isNull(jsonNode) || jsonNode.isNull()) {
            return null;
        }
        try {
            return objectMapper.treeToValue(jsonNode, t);
        } catch (JsonProcessingException e) {
            throw new JsonTransformException("将json串内的某一个path下的json串转换为 object 时出现异常。", e);
        } catch (IOException e) {
            throw new JsonTransformException("将json串内的某一个path下的json串转换为 object 时出现异常。", e);
        }
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
    @Override
    public  <T> T getPathObject(@Nonnull String json, @Nonnull TypeReference typeReference, @Nullable String... paths) {
        String pathJson = getString(json, paths);
        if (Check.isNull(pathJson)) {
            return null;
        }
        try {
            return objectMapper.readValue(pathJson, typeReference);
        } catch (JsonProcessingException e) {
            throw new JsonTransformException("将json串内的某一个path下的json串转换为 object 时出现异常。", e);
        } catch (IOException e) {
            throw new JsonTransformException("将json串内的某一个path下的json串转换为 object 时出现异常。", e);
        }
    }

    @Override
    public  <T> T getPathObject(@Nonnull String json, @Nonnull JavaType javaType, @Nullable String... paths) {
        String pathJson = getString(json, paths);
        if (Check.isNull(pathJson)) {
            return null;
        }
        try {
            return objectMapper.readValue(pathJson, javaType);
        } catch (JsonProcessingException e) {
            throw new JsonTransformException("将json串内的某一个path下的json串转换为 object 时出现异常。", e);
        } catch (IOException e) {
            throw new JsonTransformException("将json串内的某一个path下的json串转换为 object 时出现异常。", e);
        }
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
    @Override
    public  <T> List<T> getPathArray(@Nonnull String json, @Nonnull Class<T> t, @Nullable String... paths) {
        JsonNode jsonNode = parseJsonNode(json, paths);
        if (Check.isNull(jsonNode) || jsonNode.isNull()) {
            return null;
        }
        List<T> list = new ArrayList<T>();
        try {
            if (jsonNode.isArray()) {
                for (JsonNode jnode : jsonNode) {
                    list.add(objectMapper.treeToValue(jnode, t));
                }
            }
            return list;
        } catch (JsonProcessingException e) {
            throw new JsonTransformException("将json串内的某一个path下的json串转换为 List 时出现异常。", e);
        } catch (IOException e) {
            throw new JsonTransformException("将json串内的某一个path下的json串转换为 List 时出现异常。", e);
        }
    }

    /**
     * 获取json串内的某一个path下的integer值
     *
     * @param json
     * @param paths
     * @return
     */
    @Override
    public  Integer getInt(@Nonnull String json, @Nullable String... paths) {
        return getPathObject(json, Integer.class, paths);
    }

    /**
     * @param json
     * @param paths
     * @return
     */
    @Override
    public  Long getLong(@Nonnull String json, @Nullable String... paths) {
        return getPathObject(json, Long.class, paths);
    }

    /**
     * @param json
     * @param paths
     * @return
     */
    @Override
    public  Short getShort(@Nonnull String json, @Nullable String... paths) {
        return getPathObject(json, Short.class, paths);
    }

    /**
     * @param json
     * @param paths
     * @return
     */
    @Override
    public  Byte getByte(@Nonnull String json, @Nullable String... paths) {
        return getPathObject(json, Byte.class, paths);
    }


    /**
     * @param json
     * @param paths
     * @return
     */
    @Override
    public  Float getFloat(@Nonnull String json, @Nullable String... paths) {
        return getPathObject(json, Float.class, paths);
    }

    /**
     * @param json
     * @param paths
     * @return
     */
    @Override
    public  Double getDouble(@Nonnull String json, @Nullable String... paths) {
        return getPathObject(json, Double.class, paths);
    }

    /**
     * 获取Boolean值
     *
     * @param json
     * @param paths
     * @return
     */
    @Override
    public  Boolean getBoolean(@Nonnull String json, @Nullable String... paths) {
        return getPathObject(json, Boolean.class, paths);
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
    @Override
    public  String getString(@Nonnull String json, @Nullable String... paths) {
        JsonNode jsonNode = parseJsonNode(json, paths);
        if (Check.isNull(jsonNode) || jsonNode.isNull()) {
            return null;
        }
        if (jsonNode.isValueNode()) {
            return jsonNode.textValue();
        }
        return toJsonString(jsonNode);
    }
}
