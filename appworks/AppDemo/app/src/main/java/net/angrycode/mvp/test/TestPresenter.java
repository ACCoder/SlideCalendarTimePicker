package net.angrycode.mvp.test;

import android.os.Bundle;

import net.angrycode.mvp.presenter.BasePresenter;
import net.angrycode.mvp.view.IBaseView;

/**
 * Created by lancelot on 15/11/7.
 */
public class TestPresenter extends BasePresenter<IBaseView> {

    public TestPresenter(TestView view) {
    }

    @Override
    public IBaseView getView() {
        return null;
    }

    @Override
    public void onCreate(Bundle bundle) {

    }

    @Override
    public void onDestroy() {

    }
}
