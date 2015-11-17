package net.angrycode.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

public abstract class BaseMultiLayoutAdapter<T> extends BaseAdapter {

    protected Context mContext;

    protected List<T> mList;

    public BaseMultiLayoutAdapter(Context context) {
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
    public final View getView(int position, View convertView, ViewGroup parent) {
        BaseViewHolder holder;
        int type = getItemViewType(position);
        if (convertView == null) {
            convertView = View.inflate(mContext, getLayoutResource(type), null);
            holder = getViewHolder(convertView, type);
        } else {
            holder = (BaseViewHolder) convertView.getTag();
        }
        holder.render(position);

        getView(position);
        return convertView;
    }

    @Override
    public abstract int getViewTypeCount();

    @Override
    public abstract int getItemViewType(int position);

    /**
     * 返回对应的layout
     *
     * @param type
     * @return
     */
    public abstract int getLayoutResource(int type);

    /**
     * 返回对应的ViewHolder
     *
     * @param convertView
     * @param type
     * @return
     */
    public abstract BaseViewHolder getViewHolder(View convertView, int type);


    public void getView(int position) {

    }

    public abstract class BaseViewHolder {
        public BaseViewHolder(View convertView) {
            ButterKnife.inject(this, convertView);
            convertView.setTag(this);
        }
        public abstract void render(int position);
    }

}
