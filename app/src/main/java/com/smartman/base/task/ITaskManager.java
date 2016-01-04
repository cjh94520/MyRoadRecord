package com.smartman.base.task;

/**
 * Created by jiahui.chen on 2016/1/4.
 */
public interface ITaskManager {

    public void addTask(WorkTask task);

    public void removeTask(String taskId, boolean cancelIfRunning);

    public void removeAllTask(boolean cancelIfRunning);

    public int getTaskCount(String taskId);

}