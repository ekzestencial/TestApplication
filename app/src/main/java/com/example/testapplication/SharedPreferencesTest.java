

package com.example.testapplication;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Created by alex on 19.05.17.
 */
public class SharedPreferencesTest extends AppCompatActivity {
    public static String lastUser;
    HashMap<String, String> map;
    SharedPreferences sharedPreferences;
    Context context;

    public SharedPreferencesTest(Context context) {
        this.context = context;
        map = readFromFile();
    }

    public Boolean enterCheckPassword(String username, String password) {

        if (map.containsKey(username) && map.get(username).equals(getMd5Hash(password))) {
            lastUser = username;
            return true;
        } else return false;
    }

    public Boolean addUsername(String username, String password) {

        if (!map.containsKey(username)) {
            map.put(username, getMd5Hash(password));
            saveToFile(map);
            return true;
        } else {

            return false;
        }

    }

    public static String getMd5Hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String md5 = number.toString(16);

            while (md5.length() < 32)
                md5 = "0" + md5;

            return md5;
        } catch (NoSuchAlgorithmException e) {
            Log.e("MD5", e.getLocalizedMessage());
            return null;
        }
    }

    private void saveToFile(HashMap<String, String> jsonMap) {
        sharedPreferences = context.getSharedPreferences("SaveAccounts", MODE_PRIVATE);

        HashListBean hashListBean = new HashListBean(jsonMap);
        String jsonString = new Gson().toJson(hashListBean);
        android.content.SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("map", jsonString);
        editor.apply();
    }


    private HashMap<String, String> readFromFile() {
        sharedPreferences = context.getSharedPreferences("SaveAccounts", MODE_PRIVATE);
        String defValue = new Gson().toJson(new HashListBean(new HashMap<String, String>()));
        String json = sharedPreferences.getString("map", defValue);
        HashListBean hashListBean = new Gson().fromJson(json, HashListBean.class);
        return hashListBean.getHashMap();
    }

    public class HashListBean {
        private HashMap<String, String> mChildMap;

        //create constructer with hashMap as argument
        public HashListBean(HashMap<String, String> mChildMap) {
            this.mChildMap = mChildMap;
        }

        public HashMap<String, String> getHashMap() {
            return mChildMap;
        }
    }
}
