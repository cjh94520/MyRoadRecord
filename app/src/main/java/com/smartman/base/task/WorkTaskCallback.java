package com.smartman.base.task;

/**
 * Created by jiahui.chen on 2016/1/4.
 */
public interface WorkTaskCallback {

    public void onPrepareStart();

    public void onFailure(TaskException exception);

    public void onSuccess(Object result);

    public void onCompleted();

}
