package com.example.testapplication;

import android.util.Log;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alex on 19.05.17.
 */
public class SingletonSession {

    private static SingletonSession instance;
    public static String lastUser;
    private Map<String, String> map = new HashMap<>();

    //no outer class can initialize this class's object
    private SingletonSession() {
    }

    public static SingletonSession Instance() {
        //if no instance is initialized yet then create new instance
        //else return stored instance
        if (instance == null) {
            instance = new SingletonSession();
        }
        return instance;
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
}
