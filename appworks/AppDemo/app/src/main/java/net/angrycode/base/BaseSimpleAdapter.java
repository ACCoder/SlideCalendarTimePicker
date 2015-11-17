package net.angrycode.base;

import android.content.Context;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lancelot on 15/5/24.
 */
public abstract class BaseSimpleAdapter<T> extends BaseAdapter{
    private Context mContext;
    private List<T> mList;

    public BaseSimpleAdapter(Context context) {
        this.mContext = context;
        mList = new ArrayList<>(10);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public T getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addAll(List<T> elem) {
        mList.addAll(elem);
        notifyDataSetChanged();
    }

    public void add(T elem) {
        mList.add(elem);
        notifyDataSetChanged();
    }

    public void remove(T elem) {
        mList.remove(elem);
        notifyDataSetChanged();
    }

    public void remove(int index) {
        mList.remove(index);
        notifyDataSetChanged();
    }

    public void replaceAll(List<T> elem) {
        mList.clear();
        mList.addAll(elem);
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, getLayoutResource(), null);
            holder = new ViewHolder(convertView);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }
        return getView(position,convertView,holder);
    }

    /**
     * item layout resource
     * @return
     */
    public abstract int getLayoutResource();

    public abstract View getView(int position,View convertView,ViewHolder holder);

    public class ViewHolder {
        private SparseArray<View> views = new SparseArray<>();
        private View convertView;

        public ViewHolder(View convertView) {
            this.convertView = convertView;
            convertView.setTag(this);
        }

        @SuppressWarnings("unchecked")
        public <T extends View> T getView(int resId) {
            View v = views.get(resId);
            if (null == v) {
                v = convertView.findViewById(resId);
                views.put(resId, v);
            }
            return (T) v;
        }
    }
}
