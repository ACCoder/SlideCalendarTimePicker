package net.angrycode.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import net.angrycode.mvp.presenter.IBasePresenter;
import net.angrycode.mvp.view.IBaseView;

import butterknife.ButterKnife;
import icepick.Icepick;

/**
 * Created by lancelot on 15/5/24.
 */
public class BaseActivity<Presenter extends IBasePresenter> extends AppCompatActivity implements IBaseView{
    Presenter mPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.inject(this);
        Icepick.restoreInstanceState(this, savedInstanceState);

        mPresenter = createPresenter();
        if (mPresenter != null) {
            mPresenter.onCreate(savedInstanceState);
        }
    }

    protected Presenter createPresenter() {
        return null;
    }

    @Override
    public Context getContext(){
        return this;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Icepick.saveInstanceState(this, outState);
    }

    @Override
    protected void onDestroy() {
        if (mPresenter != null) {
            mPresenter.onDestroy();
        }
        super.onDestroy();
    }
}
