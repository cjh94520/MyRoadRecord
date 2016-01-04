package com.smartman.base.task;

import android.text.TextUtils;

import com.smartman.base.utils.ResourceUtil;
import com.smartman.myroadrecord.R;

/**
 * Created by jiahui.chen on 2015/12/29.
 */
public class TaskException extends Exception {
    private static final long serialVersionUID = -4532619528902357226L;

    public enum TaskError {
        // 无网络链接
        noneNetwork,
        // 连接超时
        timeout,
        // 响应超时
        socketTimeout,
        // 返回数据不合法
        resultIllegal,
        // 文件不存在
        fileNotFound
    }

    private String errorCode;

    private String errorMsg;

    public TaskException(String errorCode, String errorMsg) {
        this(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public TaskException(String errorCode) {
        this.errorCode = errorCode;

        try {
            TaskError error = TaskError.valueOf(errorCode);
            if (TaskError.noneNetwork == error)
                errorMsg = ResourceUtil.getString(R.string.nonenetwork);
            else if (TaskError.timeout == error || TaskError.socketTimeout == error)
                errorMsg = ResourceUtil.getString(R.string.timeout);
            else if (TaskError.resultIllegal == error)
                errorMsg = ResourceUtil.getString(R.string.resultillegal);
        } catch (Exception e) {
        }
    }

    @Override
    public String getMessage() {
        if (!TextUtils.isEmpty(errorMsg))
            return errorMsg;

        return super.getMessage();
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

}
