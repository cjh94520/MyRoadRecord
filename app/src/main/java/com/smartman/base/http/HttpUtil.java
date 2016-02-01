package com.smartman.base.http;

import android.text.TextUtils;

import com.smartman.base.task.TaskException;

import org.apache.http.conn.ConnectTimeoutException;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class HttpUtil {
    public static <T> T doGetSync(RequestParams params, Class<T> responseCls) throws TaskException {
        try {
            return x.http().getSync(params, responseCls);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            throw new TaskException(TaskException.TaskError.timeout.toString());
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            throw new TaskException(TaskException.TaskError.timeout.toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new TaskException(TaskException.TaskError.timeout.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new TaskException(TaskException.TaskError.timeout.toString());
        } catch (Throwable throwable) {
            TaskException taskException = null;
            if (throwable.getCause() instanceof TaskException) {
                taskException = (TaskException) throwable.getCause();
            } else if (throwable instanceof TaskException) {
                taskException = (TaskException) throwable;
            }
            if (taskException != null) {
                throw taskException;
            }
            throw new TaskException("", TextUtils.isEmpty(throwable.getMessage()) ? "服务器错误" : throwable.getMessage());
        }
    }

    public static void doGet(RequestParams params) {
        x.http().get(params, new Callback.CommonCallback<Object>() {
            @Override
            public void onSuccess(Object result) {

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }

    public static <T> T doPostSync(RequestParams params, Class<T> responseCls)throws TaskException {
        try {
            return x.http().postSync(params, responseCls);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            throw new TaskException(TaskException.TaskError.timeout.toString());
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            throw new TaskException(TaskException.TaskError.timeout.toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new TaskException(TaskException.TaskError.timeout.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new TaskException(TaskException.TaskError.timeout.toString());
        } catch (Throwable throwable) {
            TaskException taskException = null;
            if (throwable.getCause() instanceof TaskException) {
                taskException = (TaskException) throwable.getCause();
            } else if (throwable instanceof TaskException) {
                taskException = (TaskException) throwable;
            }
            if (taskException != null) {
                throw taskException;
            }
            throw new TaskException("", TextUtils.isEmpty(throwable.getMessage()) ? "服务器错误" : throwable.getMessage());
        }
    }

    public static void doPost(RequestParams params) {
        x.http().post(params, new Callback.CommonCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }


    public static <T> T uploadFile(RequestParams params, Class<T> responseCls) throws TaskException {
        try {
            return x.http().postSync(params, responseCls);
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            throw new TaskException(TaskException.TaskError.timeout.toString());
        } catch (ConnectTimeoutException e) {
            e.printStackTrace();
            throw new TaskException(TaskException.TaskError.timeout.toString());
        } catch (UnknownHostException e) {
            e.printStackTrace();
            throw new TaskException(TaskException.TaskError.timeout.toString());
        } catch (IOException e) {
            e.printStackTrace();
            throw new TaskException(TaskException.TaskError.timeout.toString());
        } catch (Throwable throwable) {
            TaskException taskException = null;
            if (throwable.getCause() instanceof TaskException) {
                taskException = (TaskException) throwable.getCause();
            } else if (throwable instanceof TaskException) {
                taskException = (TaskException) throwable;
            }
            if (taskException != null) {
                throw taskException;
            }
            throw new TaskException("", TextUtils.isEmpty(throwable.getMessage()) ? "服务器错误" : throwable.getMessage());
        }
    }

}
