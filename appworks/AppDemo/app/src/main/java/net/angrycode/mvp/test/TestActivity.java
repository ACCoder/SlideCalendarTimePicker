package net.angrycode.mvp.test;

import android.content.Context;
import android.os.Bundle;

import net.angrycode.base.BaseActivity;

/**
 * Created by lancelot on 15/11/7.
 */
public class TestActivity extends BaseActivity<TestPresenter> implements TestView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected TestPresenter createPresenter() {
        TestPresenter mPresenter = new TestPresenter(this);
        return mPresenter;
    }

    @Override
    public void onGetTestModelFinish(TestModel model) {

    }

    @Override
    public void onGetTestModelFail(int code, String message) {

    }


}
