package com.example.charl.heroes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;


public class ItemDetailsActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;

    FirebaseAuth mAuth;

    private ListView myListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_detail);

        final String pos = getIntent().getExtras().getString("id");

        final String id = ItemListActivity.list.get(Integer.parseInt(pos)).getId();
        Log.d("ddddd", id);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Heroes");



        final EditText nom = (EditText)findViewById(R.id.name);
        final EditText vraiNom = (EditText)findViewById(R.id.realName);
        final EditText equipe = (EditText)findViewById(R.id.team);
        final EditText anne = (EditText)findViewById(R.id.year);

        final Button apply = (Button)findViewById(R.id.apply);



        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    //Log.d("eeeee", ds.getKey());

                    if(ds.getKey().equals(id)){
                        Log.d("eeeee", ds.getValue(Hero.class).getName());
                        final String name = ds.getValue(Hero.class).getName();
                        final String realName = Objects.requireNonNull(ds.getValue(Hero.class)).getRealName();
                        final String team = Objects.requireNonNull(ds.getValue(Hero.class)).getTeam();
                        final String year = Objects.requireNonNull(ds.getValue(Hero.class)).getYear();
                        nom.setText(name);

                        if(!realName.equals("null")){
                            Log.d("eeee", String.valueOf(realName));
                            vraiNom.setText(realName);

                        }

                        if(!team.equals("null")){
                            Log.d("eeee", String.valueOf(realName));
                            equipe.setText(team);

                        }

                        if(!year.equals("null")){
                            Log.d("eeee", String.valueOf(realName));
                            anne.setText(year);

                        }

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }

        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = String.valueOf(nom);

                ItemListActivity.list.set(Integer.parseInt(pos),new Hero(id,name,null,null,null));
                Log.d("eeee", pos);
                finish();
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {

                    if(ds.getKey().equals(id)){
                        Log.d("eeeee", ds.getValue(Hero.class).getName());
                        final String name = ds.getValue(Hero.class).getName();
                        nom.setText(name);

                        apply.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                String tmp = nom.getText().toString();
                                String tmp_realName = vraiNom.getText().toString();
                                String tmp_team = equipe.getText().toString();
                                String tmp_year = anne.getText().toString();

                                myRef.child(id).child("name").setValue(tmp);
                                myRef.child(id).child("realName").setValue(tmp_realName);
                                myRef.child(id).child("team").setValue(tmp_team);
                                myRef.child(id).child("year").setValue(tmp_year);

                                ItemListActivity.list.set(Integer.parseInt(pos),new Hero(id,tmp ,tmp_realName,tmp_team,tmp_year));
                                Log.d("eeee", pos);
                                finish();
                            }
                        });

                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }


    private int findHero(String myHero) {
        int pos = 0;
        while(myHero != ItemListActivity.list.get(pos).name){
            pos++;
        }
        return pos;
    }
}
