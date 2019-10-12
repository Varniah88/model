package com.example.model;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class EditProfile extends AppCompatActivity {

    EditText muser,mdob,mpw;
    RadioGroup mradiogrp;
    RadioButton mgender;
    Button medit,mdelete;
    DBHandler dbHandler;
    Intent intent;

    public static final String edit_userid = "userId";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        muser =(EditText)findViewById(R.id.editname);
        mdob=(EditText)findViewById(R.id.editdob);
        mpw=(EditText)findViewById(R.id.editpaswrd);
        mradiogrp =(RadioGroup)findViewById(R.id.rdgrp);
        medit = (Button)findViewById(R.id.editbtn);
        mdelete = (Button)findViewById(R.id.dltbtn);

        intent = getIntent();

        dbHandler = new DBHandler(EditProfile.this);


        setUserDetails();
        setUserDetails1();

    }

    public void setUserDetails(){

        String userid = intent.getStringExtra(Home.USERID);
//        if(userid == null){
//            Toast.makeText(EditProfile.this,"Error",Toast.LENGTH_SHORT).show();
//
//        }
        Toast.makeText(EditProfile.this,"WELCOME",Toast.LENGTH_SHORT).show();

            int userId = Integer.parseInt(userid);
            UserProfile.Users users = dbHandler.readallinfo(userId);
            muser.setText(users.getUsername());
            mdob.setText(users.getDob());
            mpw.setText(users.getPswrd());

    }

    public void setUserDetails1(){

        String userid = intent.getStringExtra(ProfileManagement.USERID);

            int userId = Integer.parseInt(userid);
            UserProfile.Users users = dbHandler.readallinfo(userId);
            muser.setText(users.getUsername());
            mdob.setText(users.getDob());
            mpw.setText(users.getPswrd());



    }

}
