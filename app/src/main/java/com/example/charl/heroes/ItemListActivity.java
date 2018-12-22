package com.example.charl.heroes;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.charl.heroes.databinding.ActivityItemListBinding;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class ItemListActivity extends AppCompatActivity {

    static List<Hero> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityItemListBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_item_list);
        binding.setViewModel( new ItemListViewModel(getApplicationContext()));
        RecyclerView recyclerView = binding.getRoot().findViewById(R.id.data_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), VERTICAL));

        list = new ArrayList<>();
    }
}
