package com.xiaojinzi.code.modular.dynamicsDetail.blogDetail.fragment.view;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.xiaojinzi.code.R;
import com.xiaojinzi.code.util.BaseViewPagerFragment;
import com.xiaojinzi.viewinjection.annotation.Injection;

/**
 * Created by cxj on 2016/11/1.
 * 博客详情的fragment
 */
public class BlogDetailFragment extends BaseViewPagerFragment {

    @Injection(R.id.wv)
    WebView wv;

    @Override
    public int getLayoutId() {
        return R.layout.frag_blog_detail;
    }

    @Override
    public void initView() {
        super.initView();

        //让浏览器不弹出系统自带的
        wv.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                super.shouldOverrideUrlLoading(view, request);
                return true;
            }

        });

        wv.loadUrl("http://blog.csdn.net/lmj623565791/article/details/52761658");

    }
}
