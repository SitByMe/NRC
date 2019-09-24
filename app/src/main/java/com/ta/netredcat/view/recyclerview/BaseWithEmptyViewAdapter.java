package com.ta.netredcat.view.recyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.ta.netredcat.R;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseWithEmptyViewAdapter<B> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public Context mContext;
    protected List<B> mDataList;
    private LayoutInflater mInflater;

    protected OnItemClickListener<B> mOnItemClickListener;

    public interface OnItemClickListener<B> {
        void onItemClick(View view, B item, int position);
    }

    protected OnEmptyViewClickList onEmptyViewClickList;

    public void setOnEmptyViewClickList(OnEmptyViewClickList onEmptyViewClickList) {
        this.onEmptyViewClickList = onEmptyViewClickList;
    }

    public interface OnEmptyViewClickList {
        void onEmptyClick();
    }

    /**
     * 空数据时，显示空布局类型
     */
    private final int EMPTY_VIEW = 1;

    /**
     * 控制空布局的显隐 1：显示1个布局；0：不显示任何布局
     */
    private int mEmptyType = 0;

    public BaseWithEmptyViewAdapter(Context context) {
        this.mContext = context;
        this.mDataList = new ArrayList<>();
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (EMPTY_VIEW != viewType) {
            //返回空布局的viewHolder
            return onCreateItemHolder(mInflater, parent, viewType);
        }
        return onCreateEmptyHolder(mInflater, parent, viewType);
    }

    public RecyclerView.ViewHolder onCreateEmptyHolder(LayoutInflater mInflater, ViewGroup parent, int viewType) {
        View view = new View(mContext);
        return new DefaultEmptyHolder(view);
    }

    public void onBindEmptyHolder(RecyclerView.ViewHolder holder) {
    }

    public abstract RecyclerView.ViewHolder onCreateItemHolder(LayoutInflater mInflater, ViewGroup parent, int viewType);

    public abstract void onBindItemHolder(List<B> mDataList, RecyclerView.ViewHolder holder, int position);

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        if (EMPTY_VIEW != itemViewType) {
            onBindItemHolder(mDataList, holder, position);
            initItemClickListener(holder, position);
        } else {
            onBindEmptyHolder(holder);
        }
    }

    private void initItemClickListener(final RecyclerView.ViewHolder holder, final int position) {
        if (useInitItemClick()) {
            //如果设置了回调，则设置点击事件
            holder.itemView.setOnClickListener(v -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(holder.itemView, mDataList.get(position), position);
                }
            });
        }
    }

    public boolean useInitItemClick() {
        return true;
    }

    @Override
    public int getItemCount() {
        return mDataList != null ? mDataList.size() + mEmptyType : mEmptyType;
    }

    @Override
    public int getItemViewType(int position) {
        if (mEmptyType == EMPTY_VIEW) {
            //空布局的类型
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    public B getItem(int position) {
        return mDataList.get(position);
    }

    public int getDataCount() {
        return mDataList.size();
    }

    /**
     * 显示添加的数据
     * 如果数据为空，则显示空布局
     */
    public void addData(List<B> data) {
        if (data != null && !data.isEmpty()) {
            if (mEmptyType == 1) {
                mEmptyType = 0;
                notifyItemRemoved(0);
            }
            mDataList.addAll(data);
            if (mDataList.size() == 0) {
                showEmptyView();
            } else {
                notifyDataSetChanged();
            }
        } else if (mDataList.size() == 0) {
            if (mEmptyType != EMPTY_VIEW) {
                mEmptyType = EMPTY_VIEW;
                notifyItemInserted(0);
            }
        }
    }

    /**
     * 显示空布局
     */
    public void showEmptyView() {
        if (!mDataList.isEmpty()) {
            mDataList.clear();
            notifyDataSetChanged();
        }
        if (mEmptyType != EMPTY_VIEW) {
            //当前布局不是空布局，则刷新显示空布局
            mEmptyType = EMPTY_VIEW;
            notifyItemInserted(0);
        }
    }

    public void clearData() {
        if (!mDataList.isEmpty()) {
            mDataList.clear();
        }
    }

    public List<B> getAllData() {
        return mDataList;
    }

    public void setNormalDecoration(RecyclerView rv, Context context) {
        DividerItemDecoration divider = new DividerItemDecoration(context, LinearLayout.VERTICAL);
        divider.setDrawable(context.getResources().getDrawable(R.drawable.item_line));
        rv.addItemDecoration(divider);
    }

    public void setItemClickListener(OnItemClickListener<B> onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    static class DefaultEmptyHolder extends RecyclerView.ViewHolder {

        DefaultEmptyHolder(View view) {
            super(view);
        }
    }
}
