package com.example.model;

import android.provider.BaseColumns;

public class UserProfile {


    private UserProfile() {
    }

public static UserProfile getprofile(){

        UserProfile userProfile = new UserProfile();
        return userProfile;
    }

    class Users implements BaseColumn {

        public static final String TABLE_NAME = "User_information";
        public static final String COL_1 = "ID";
        public static final String COL_2 = "User_Name";
        public static final String COL_3 = "Date_of_Birth";
        public static final String COL_4 = "Password";
        public static final String COL_5 = "Gender";


        private int id;
        private String username;
        private String dob;
        private String pswrd;
        private String gender;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getPswrd() {
            return pswrd;
        }

        public void setPswrd(String pswrd) {
            this.pswrd = pswrd;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

    }
public Users getUser(){
        Users users = new Users();
        return users;
}

}
