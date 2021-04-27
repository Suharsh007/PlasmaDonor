package com.alaksh;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Donor extends AppCompatActivity {
EditText etName, etBloodGroup, etLocation, etPhoneNumber;
Button btnSubmit;
    private FirebaseDatabase database =  FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_donor);
        etName = findViewById(R.id.etName);
        etBloodGroup = findViewById(R.id.etBloodGroup);
        etLocation = findViewById(R.id.etLocation);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String bloodGroup = etBloodGroup.getText().toString();
                String location = etLocation.getText().toString();
                String phoneNumber = etPhoneNumber.getText().toString();
                if(name.length()==0 || bloodGroup.length()==0 || location.length()==0 || phoneNumber.length()==0)
                {
                    Toast.makeText(Donor.this, "Enter all details", Toast.LENGTH_SHORT).show();
                }
                else {

                    Map<String, Object> details = new HashMap<>();

                    details.put(name, new Details(name, bloodGroup, location, phoneNumber));
                    DatabaseReference ref = database.getReference().child("Donor Details/");
                    ref.updateChildren(details);
                    Toast.makeText(Donor.this, "Thank you for being a donor", Toast.LENGTH_SHORT).show();

                    Intent i1 = new Intent(Donor.this, MainActivity.class);
                    startActivity(i1);
                    finish();
                }

            }
        });

    }
}