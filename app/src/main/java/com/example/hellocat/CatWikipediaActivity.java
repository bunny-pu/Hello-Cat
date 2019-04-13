package com.example.hellocat;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.hellocat.utils.LogUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author gegeding
 */
public class CatWikipediaActivity extends AppCompatActivity {

    private static final String TAG = "CatWikipediaActivity";

    @BindView(R.id.web_cat_wiki)
    WebView webCatWiki;

    @BindView(R.id.swipeRefreshLayout_wiki)
    SwipeRefreshLayout swipeRefreshLayoutWiki;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat_wikipedia);
        ButterKnife.bind(this);
        swipeRefreshLayoutWiki.setEnabled(false);
        swipeRefreshLayoutWiki.setRefreshing(true);
        webCatWiki.setWebViewClient(webViewClient);
        String catWikiUrl = getIntent().getStringExtra("cat_wiki");
        if (!TextUtils.isEmpty(catWikiUrl)) {
            webCatWiki.loadUrl(catWikiUrl);
        }
    }


    /**WebViewClient主要帮助WebView处理各种通知、请求事件*/
    private WebViewClient webViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {//页面加载完成
            LogUtil.e(TAG, "web onPageFinished");
            swipeRefreshLayoutWiki.setRefreshing(false);

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {//页面开始加载
            LogUtil.e(TAG, "web onPageStarted");

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            LogUtil.e(TAG, "web shouldOverrideUrlLoading");
            return super.shouldOverrideUrlLoading(view, url);
        }

    };
}
