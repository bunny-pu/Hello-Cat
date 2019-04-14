package com.example.hellocat.mvvm;

public interface IBaseView {
        /**
         * 开始加载
         */
        void loadStart();

        /**
         * 加载完成
         */
        void loadComplete();

        /**
         * 加载失败
         *
         */
        void loadFailure();
}
