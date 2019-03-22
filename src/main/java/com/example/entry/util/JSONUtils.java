package com.example.entry.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName
 * @Auther: Administrator
 * @Date: 2019/3/22 08:57
 * @Description:
 * @Version 1.0
 */
public class JSONUtils {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /** 日志 */
    private static final Logger LOGGER = LoggerFactory.getLogger(JSONUtils.class);

    /**
     * Java对象转为JSON字符串
     */
    public static String toJSONString(Object target) {
        //经测试jackson > fastjson > json-lib > flexjson
        mapper.setDateFormat(format);
        mapper.configure(SerializationConfig.Feature.WRITE_NULL_MAP_VALUES, false);
        mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES, true);
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        String _json = null;
        try {
            _json = mapper.writeValueAsString(target);
        } catch (JsonGenerationException e) {
            LOGGER.error("", e);
        } catch (JsonMappingException e) {
            LOGGER.error("", e);
        } catch (IOException e) {
            LOGGER.error("", e);
        }
        return _json.replaceAll("(\r|\n|\t)", "");
    }

    /**
     * JSON字符串转java对象
     */
    public static Object toJava(String jsonStr, Class<?> clazz) {
        //经测试fastjson > jackson > json-lib > flexjson
        return JSON.parseObject(jsonStr, clazz);
    }

    /**
     * json转list加java对象
     */
    public static <T> List<T> toList(String str, Class<T> clazz) {
        return JSON.parseArray(str,clazz);
    }


    /**
     * list转json字符串
     * @param list
     * @return
     */
    public static String toJSONString(Collection<?> list) {
        if(!ArrayUtils.hasLength(list)) return null;
        //经测试fastjson > jackson > flexjson > json-lib
        return JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss", SerializerFeature.WriteDateUseDateFormat);
    }

    /**
     * 数组转json字符串
     * @param objects
     * @return
     */
    public static String toJSONString(Object[] objects) {
        if(!ArrayUtils.hasLength(objects)) return null;
        //经测试fastjson > jackson > flexjson > json-lib
        return JSON.toJSONString(objects);
    }

    /**
     * map转json字符串
     */
    public static String toJSONString(Map<String, Object> resultMap){
        return JSON.toJSONString(resultMap,SerializerFeature.WriteDateUseDateFormat,SerializerFeature.DisableCircularReferenceDetect);
    }

    /**
     * 返回json字符串
     *
     * @param data
     *            数据体
     * @param status
     *            状态码
     * @param message
     *            状态描述
     * @return
     */
    public static String returnJSON(Object data, String status, String message) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("data", data);
        resultMap.put("status", status);
        resultMap.put("message", message);
        return JSONUtils.toJSONString(resultMap);
    }

}
