package com.example.charl.heroes;


import android.databinding.ObservableField;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ItemViewModel {

    public ObservableField<String> title = new ObservableField<>();
    public ObservableField<String> description = new ObservableField<>();
    public ObservableField<String> newHero = new ObservableField<>();

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("Heroes");


    public ItemViewModel(){
        assign();
    }

    private void assign() {
        newHero.set(String.valueOf(ItemListActivity.list.get(ItemListViewModel.items.size()).name));

    }


    void viewDetails(int pos){

        Log.d("ddddd", String.valueOf(pos));


    }
    public void delete(){


        Log.d("dddd", String.valueOf(ItemListViewModel.myPos));
        Log.d("rrrr", ItemListActivity.list.get(ItemListViewModel.myPos).getId());

        myRef.child(ItemListActivity.list.get(ItemListViewModel.myPos).getId()).removeValue();
        ItemListActivity.list.remove(ItemListViewModel.myPos);
        ItemListViewModel.items.remove(ItemListViewModel.items.size()-(ItemListViewModel.myPos+1));

/*
        myRef.child("ddddd").removeValue();
        final String id = ItemListViewModel.myId;
        String i = ItemListViewModel.myId;
        //ItemListActivity.list.remove(ItemListViewModel.myPos);
        myRef.child(i).removeValue();
*/




    }
}
