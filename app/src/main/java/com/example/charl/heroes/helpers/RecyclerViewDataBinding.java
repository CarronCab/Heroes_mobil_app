package com.example.charl.heroes.helpers;

import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class RecyclerViewDataBinding<T> {
    @BindingAdapter({"app:itemsSource",
            "app:itemTemplate", "app:onItemTapped"})
    public void bind(RecyclerView recyclerView,
                     List<T> items, int itemTemplate,
                     GenericAdapter.ItemTappedListener
                             onItemTapped) {
        recyclerView.setAdapter(new
                GenericAdapter(items, itemTemplate,
                DataBindingUtil.findBinding(recyclerView),
                onItemTapped,recyclerView.getContext()));
    }
    @BindingAdapter({"app:onItemTapped"})
    public void bind(RecyclerView recyclerView,
                     GenericAdapter.ItemTappedListener
                             itemTapped) {
    }
}
