package org.asuraframework.commons.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

/**
 * <p></p>
 * <p>
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author jiangn18
 * @version V1.0
 * @date 2018/3/19 19:43
 */
public interface JsonInterface {
    /**
     * 将json转换成Object
     *
     * @param json  要转换的json数据
     * @param clazz 目标实体
     * @return Entity    转换后的实体对象
     */
      <T> T parseObject( String json,  Class<T> clazz) ;
    /**
     * 将自定义对象转json
     *
     * @param object 待转换的对象实体
     * @return json    转换后的json数据
     */
      String toJsonString( Object object);

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
    <T> List<T> parseObjectList( String json,  Class<T> clazz) ;

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
    public JsonNode parseJsonNode( String json,  String... paths) ;

    /**
     * 将json串内的某一个path下的json串转换为 object
     *
     * @param json
     * @param paths
     * @param t
     * @param <T>
     * @return
     */
    <T> T getPathObject( String json,  Class<T> t,  String... paths) ;


    /**
     * 将json串内的某一个path下的json串转换为 object
     *
     * @param json
     * @param paths
     * @param typeReference
     * @param <T>
     * @return
     */
    <T> T getPathObject( String json,  TypeReference typeReference,  String... paths);


    /**
     * 将json串内的某一个path下的json串转换为 List
     *
     * @param json
     * @param t
     * @param paths
     * @param <T>
     * @return
     */
    <T> List<T> getPathArray( String json,  Class<T> t,  String... paths) ;

    /**
     * 获取json串内的某一个path下的integer值
     *
     * @param json
     * @param paths
     * @return
     */
    Integer getInt( String json,  String... paths) ;

    /**
     * @param json
     * @param paths
     * @return
     */
    Long getLong( String json,  String... paths);

    /**
     * @param json
     * @param paths
     * @return
     */
    Short getShort( String json,  String... paths);

    /**
     * @param json
     * @param paths
     * @return
     */
    Byte getByte( String json,  String... paths);


    /**
     * @param json
     * @param paths
     * @return
     */
    Float getFloat( String json,  String... paths);

    /**
     * @param json
     * @param paths
     * @return
     */
    Double getDouble( String json,  String... paths);

    /**
     * 获取Boolean值
     *
     * @param json
     * @param paths
     * @return
     */
    Boolean getBoolean( String json,  String... paths) ;

    /**
     * 获取json传 K-V 的V值只适应于获取叶子节点的V值
     * 注意：如果{"a":"b","c":{"d":"d1","e":{"f","f1"}}}
     * 当 path为c时候,返回：{"d":"d1","e":{"f","f1"}}
     *
     * @param json
     * @param paths
     * @return
     */
      String getString( String json,  String... paths) ;
}
