package com.example.sih2020;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShowHistory extends AppCompatActivity {

    RecyclerView recyclerView;
    private MyAdapter myAdapter;
    FloatingActionButton floatingActionButton;
    String generationCode;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference historyReference;

    List<PastRecord> pastRecords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_history);


        generationCode = getIntent().getStringExtra("generationCode");
        firebaseDatabase = FirebaseDatabase.getInstance();
        historyReference = firebaseDatabase.getReference("machines").child(generationCode).child("pastRecords");

        floatingActionButton = findViewById(R.id.btn_float);
        recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PastRecord pastRecord = new PastRecord();
        pastRecord.setDescription("Installation Of Machines");
        pastRecord.setServiceDate(new Date(2020,1,8));
        pastRecord.setDone(true);
        pastRecord.setServiceMan("aditya");

        PastRecord pastRecord1 = new PastRecord();
        pastRecord1.setDescription("Installation Of Machines");
        pastRecord.setServiceDate(new Date(2020,1,8));
        pastRecord1.setDone(true);
        pastRecord1.setServiceMan("aditya");

        List<PastRecord> list = new ArrayList<>();
        list.add(pastRecord);
        list.add(pastRecord1);


        pastRecords = new ArrayList<>();

        historyReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot pastRecord : dataSnapshot.getChildren())
                {

                    PastRecord m = pastRecord.getValue(PastRecord.class);
                    String desc = m.getDescription();
                    Log.i("pastRecord",desc);
                    Toast.makeText(ShowHistory.this, "ha aagya", Toast.LENGTH_SHORT).show();
                    pastRecords.add(m);
                }

                myAdapter = new MyAdapter(getApplicationContext(), pastRecords);
                recyclerView.setAdapter(myAdapter);

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowHistory.this, UpdateActivity.class);
                startActivity(i);
            }
        });
    }



}
