package com.example.hellocat.mvvm;

import java.util.List;

public interface IBaseListener<T> {
    /**
     * 加载数据成功
     *
     * @param list
     */
    void loadSuccess(List<T> list);

    /**
     * 加载失败
     *
     */
    void loadFailure();

    /**
     * 开始加载
     */
    void loadStart();

    /**
     * 加载结束
     */
    void loadComplete();
}
