package net.angrycode.appdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import net.angrycode.base.BaseActivity;

import butterknife.InjectView;

/**
 * lambda语法，在android中的使用
 * Created by lancelot on 15/7/6.
 */
public class LambdaActivity extends BaseActivity {

    @InjectView(R.id.ib_btn)
    ImageButton button;
    String text = "hello";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lambda);

        //访问局部和全局变量
        //局部变量默认final
        String str = " world!";
        button.setOnClickListener(v-> {
            //
            text = text +str;
        });

        button.setOnLongClickListener(this::onLongClick);

//        mListener.onDefaultMethod();
    }

    private boolean onLongClick(View view) {
        return true;
    }

    /**
     * 方法引用
     * @param view
     */
    private void onClick(View view) {

    }

    OnInteractionListener mListener;
    /***
     * 函数式接口，只含有一个方法
     */
    public interface OnInteractionListener{
        void onInteract();
    }

    /**
     * 接口可以有实现方法
     * 可以给接口定义默认方法，不妨碍此接口成为函数式接口
     */
    void onDefaultMethod() {

    }

    /**
     * 不再是函数式接口
     */
    public interface OnItemClickListener{
        void onItemClick(int position);

        void onItemLongClick(int position);

        default void onDefaultMethod() {
            //接口的默认方法
        }
    }

    //默认方法
    private interface Defaulable {
        // Interfaces now allow default methods, the implementer may or
        // may not implement (override) them.
        default String notRequired() {
            return "Default implementation";
        }
    }

    private static class DefaultableImpl implements Defaulable {
    }

    private static class OverridableImpl implements Defaulable {
        @Override
        public String notRequired() {
            return "Overridden implementation";
        }
    }

    void m1(){
        Defaulable defaulable = new DefaultableImpl();
        defaulable.notRequired();//Default implementation

        defaulable = new OverridableImpl();
        defaulable.notRequired();//Overridden implementation
    }
    /**
     * 静态方法
     */
    private interface DefaulableFactory {
        // Interfaces now allow static methods
        static Defaulable createDefaultImpl() {
            return new DefaultableImpl();
        }
    }
}
