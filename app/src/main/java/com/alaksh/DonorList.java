package com.alaksh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class DonorList extends AppCompatActivity {
    DatabaseReference reference;
    RecyclerView recyclerView;
    DonorListAdapter listAdapter;
    ArrayList<Details> list;
    ProgressDialog dialog;
    EditText etSearch;
    private static final String TAG = "DonorList";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_donor_list);

        etSearch = findViewById(R.id.etSearch);

        recyclerView = findViewById(R.id.rvView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Details>();
        reference = FirebaseDatabase.getInstance().getReference().child("Donor Details");
        enableLoader();

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Details donor = dataSnapshot1.getValue(Details.class);
                    list.add(donor);
                }

                listAdapter = new DonorListAdapter(DonorList.this, list);
                recyclerView.setAdapter(listAdapter);
                listAdapter.notifyDataSetChanged();
                disableLoader();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                disableLoader();
                Toast.makeText(DonorList.this, "Error Occured", Toast.LENGTH_LONG).show();
            }

        });


    }

    private void filter(String text) {
        ArrayList<Details> filterdItems = new ArrayList<>();
        for(Details s : list)
        {
            if(s.getBloodgroup().toLowerCase().contains(text.toLowerCase())|| s.getLocation().toLowerCase().contains(text.toLowerCase())) {
                filterdItems.add(s);

                }
        }
        listAdapter.filterList(filterdItems);
    }

    public  void enableLoader(){
        dialog = ProgressDialog.show(this, "", "Please Wait...");
    }

    public void disableLoader(){
        if(dialog !=null)
            dialog.dismiss();
    }
}