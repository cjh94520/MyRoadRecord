package com.smartman.base.http;

import com.alibaba.fastjson.JSON;
import com.smartman.base.task.TaskException;

import org.xutils.common.util.LogUtil;
import org.xutils.http.app.ResponseParser;
import org.xutils.http.request.UriRequest;

import java.lang.reflect.Type;

/**
 * Created by jiahui.chen on 2016/1/4.
 */
public class JsonResponseParser implements ResponseParser {
    @Override
    public void checkResponse(UriRequest request) throws Throwable {
        LogUtil.d("checkResponse");
    }

    @Override
    public Object parse(Type resultType, Class<?> resultClass, String result) throws Throwable {
        try {
            if (resultClass.getSimpleName().equals("String"))
                return resultClass;
            Object o =JSON.parseObject(result, resultClass);
            return o;
        } catch (Exception e) {
            e.printStackTrace();
            throw new TaskException(TaskException.TaskError.resultIllegal.toString());
        }
    }
}
