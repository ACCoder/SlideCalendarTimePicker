package net.angrycode.mvp.test;

import net.angrycode.mvp.view.IBaseView;

/**
 * Created by lancelot on 15/11/7.
 */
public interface TestView extends IBaseView {

    void onGetTestModelFinish(TestModel model);

    void onGetTestModelFail(int code, String message);
}
