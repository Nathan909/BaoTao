package com.pareto.bao.baotao.view;

import android.support.v7.widget.RecyclerView;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Pareto909 on 2017/4/24.
 */

public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<HomeRecyclerViewAdapter.HomeViewHolder> {
    /**
     * 相当于getView方法中创建View和ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    /**
     * 相当于getView绑定数据部分的代码
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {

    }

    /**
     * 总条数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return 0;
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {
        public HomeViewHolder(View itemView) {
            super(itemView);
        }
    }
}
