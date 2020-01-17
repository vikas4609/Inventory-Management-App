package com.example.inventory.responsibleMan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.inventory.R;
import com.example.inventory.model.PastRecord;
import com.example.inventory.responsibleMan.adapters.ShowDetailsAdapter;
import com.example.inventory.serviceMan.UpdateActivity;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShowDetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    private ShowDetailsAdapter showDetailsAdapter;
    FloatingActionButton floatingActionButton;
    String generationCode;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference historyReference,pastRecordsReference;
    SwipeRefreshLayout swipeRefereshLayout;
    ShimmerFrameLayout shimmerFrameLayout;

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

        shimmerFrameLayout = findViewById(R.id.shimmerFrameLayout);
        shimmerFrameLayout.startShimmerAnimation();

        swipeRefereshLayout = findViewById(R.id.swipeRefreshLayout);
        pastRecords = new ArrayList<>();
        swipeRefereshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefereshLayout.setColorSchemeColors(Color.BLUE);
                PastRecord pastRecord = new PastRecord();
                pastRecord.setDescription("Installation Of Machines");
                pastRecord.setServiceDate("14/01/2019");
                pastRecord.setDone(true);
                pastRecord.setServiceMan("aditya");
                historyReference.push().setValue(pastRecord);
                pastRecords.add(0,pastRecord);
                showDetailsAdapter.notifyDataSetChanged();
                swipeRefereshLayout.setRefreshing(false);

            }
        });



//        PastRecord pastRecord1 = new PastRecord();
//        pastRecord1.setDescription("Installation Of Machines");
//        pastRecord.setServiceDate(new Date(2020,1,8));
//        pastRecord1.setDone(true);
//        pastRecord1.setServiceMan("aditya");
//
//        List<PastRecord> list = new ArrayList<>();
//        list.add(pastRecord);
//        list.add(pastRecord1);




        historyReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot pastRecord : dataSnapshot.getChildren())
                {

                    PastRecord m = pastRecord.getValue(PastRecord.class);
                    String desc = m.getDescription();
                    Log.i("pastRecord",desc);
                    Toast.makeText(ShowDetailsActivity.this, "ha aagya", Toast.LENGTH_SHORT).show();
                    pastRecords.add(m);
                }

                showDetailsAdapter = new ShowDetailsAdapter(getApplicationContext(), pastRecords);
                recyclerView.setAdapter(showDetailsAdapter);
                shimmerFrameLayout.setVisibility(View.GONE);
                shimmerFrameLayout.stopShimmerAnimation();

            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ShowDetailsActivity.this, UpdateActivity.class);
                startActivity(i);

            }
        });
    }



}