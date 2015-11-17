package net.angrycode.mvp.presenter;

import android.os.Bundle;

import net.angrycode.mvp.view.IBaseView;

/**
 * Created by lancelot on 15/11/7.
 */
public interface IBasePresenter<View extends IBaseView> {

    /**
     * 获取当前的View
     * @return
     */
    View getView();

    /**
     * 创建Presenter时调用
     * @param bundle
     */
    void onCreate(Bundle bundle);

    /**
     * 销毁Presenter时调用
     */
    void onDestroy();

}
