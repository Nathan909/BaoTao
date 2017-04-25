package com.pareto.bao.baotao.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pareto.bao.baotao.R;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private HomeRecyclerViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);
        initView(view);
        initData();
        return view;
    }

    private void initView(View view) {
        recyclerView = (RecyclerView) view.findViewById(R.id.rv_home);
        adapter = new HomeRecyclerViewAdapter();
    }

    private void initData() {
        recyclerView.setAdapter(adapter);
    }

}
