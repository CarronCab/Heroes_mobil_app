package com.example.charl.heroes;

import android.databinding.ObservableField;

public class ItemDetailViewModel {

    public ObservableField<String> name = new ObservableField<>();

    private void assign() {
        name.set(String.valueOf(ItemListActivity.list.get(ItemListViewModel.items.size()).name));
    }

}
