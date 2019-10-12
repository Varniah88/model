package com.example.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class ProfileManagement extends AppCompatActivity {
    EditText muser,mdob,mpw;
    RadioGroup mradiogrp;
    RadioButton mgender;
    Button madd;


public static final String USERID = "userId";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_management);

        muser =(EditText)findViewById(R.id.user);
        mdob =(EditText)findViewById(R.id.dob);
        mpw =(EditText)findViewById(R.id.pw);
        mradiogrp =(RadioGroup)findViewById(R.id.rdgrp);
        madd =(Button)findViewById(R.id.add);
        final DBHandler dbHandler = new DBHandler(ProfileManagement.this);

        madd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = muser.getText().toString().trim();
                String dob = mdob.getText().toString().trim();
                String password = mpw.getText().toString().trim();
                int selectedgender = mradiogrp.getCheckedRadioButtonId();
                mgender =(RadioButton)findViewById(selectedgender);
                String gender =mgender.getText().toString().trim();


                UserProfile.Users users = UserProfile.getprofile().getUser();
                users.setUsername(username);
                users.setDob(dob);
                users.setPswrd(password);
                users.setGender(gender);
                boolean results = dbHandler.addinfo(users);

                if(results == true){
                    Toast.makeText(ProfileManagement.this,"Sucsessfully Registered",Toast.LENGTH_SHORT).show();
                    UserProfile.Users newuser = dbHandler.readallinfo(username);
                    int userId = newuser.getId();
                    Intent intent = new Intent(ProfileManagement.this,EditProfile.class);
                    intent.putExtra(USERID,Integer.toString(userId));
                    startActivity(intent);
                }


                else{
                    Toast.makeText(ProfileManagement.this,"unSucsessfully Registered",Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
