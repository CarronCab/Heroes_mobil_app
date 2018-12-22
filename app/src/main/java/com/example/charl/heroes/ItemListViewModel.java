package com.example.charl.heroes;

import android.content.Context;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableField;
import android.support.annotation.NonNull;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class ItemListViewModel {

    public ObservableField<String> screenTitle = new ObservableField<>();
    public static ObservableArrayList<ItemViewModel> items = new ObservableArrayList<>();

    public ObservableField<String> newHero = new ObservableField<>();

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Heroes");

    public static String myId;
    public static int myPos;




    public ItemListViewModel(Context context) {

        Context myContext = context;
        update();


        screenTitle.set("This is a list of super heroes, please feel free to check it, complete it, modify it, or delete heroes you do not want. PS: The Delete buttons delete the first item");



    }

    public void clear() { items.clear(); ItemListActivity.list.clear(); myRef.removeValue();}
    private void generateItem() {

        items.add(items.size(), new ItemViewModel());


    }

    public void onItemTapped(Object item){
        //int i = GenericAdapter.pos;
        //((ItemViewModel)item).squareTwo();
        String i = ((ItemViewModel)item).newHero.get();
        int pos  = findHero(i);
        myPos = pos;
        ((ItemViewModel)item).viewDetails(pos);

    }

    public void addNewHero(final String newHero) {

        final boolean flag = alreadyHere(newHero);



        //ItemListActivity.list.clear();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                if (flag){
                    if(newHero != null){
                        items.clear();
                        Log.d("ddddd", newHero);
                        String id = myRef.push().getKey();
                        myId = id;
                        Hero myNewHero = new Hero(id,newHero, "null", "null", "null");
                        myRef.child(id).setValue(myNewHero);
                        ItemListActivity.list.add(myNewHero);
                        update();

                    }
                    else{
                        Log.d("ddddd","hi");
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private boolean alreadyHere(String newHero) {
        int i = 0;
        String test;
        while(i < ItemListActivity.list.size()){
            test = ItemListActivity.list.get(i).name;
            if(newHero.equals(test)){ return false;}
            else {
                i++;
            }
        }
        return true;
    }



    private int findHero(String myHero) {
        int pos = 0;
        while(myHero != ItemListActivity.list.get(pos).name){
            pos++;
        }
        return pos;
    }

    private void update(){

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                ItemListActivity.list.clear();

                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String name = ds.getValue(Hero.class).getName();
                    Log.d("ffff", name);
                    Hero hero = new Hero(ds.getKey(),name,"null","null","null");
                    hero.setName(name);
                    ItemListActivity.list.add(hero);
                    generateItem();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

    }

}
