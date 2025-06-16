package com.example.fruit_selling.util;

public class FirebaseImage {
    private static final String FIREBASE_STORAGE_URL = "https://firebasestorage.googleapis.com/v0/b/%s/o/%s.png?alt=media";
    private static final String FIREBASE_BUCKET = "retailstorage-5432c.appspot.com"; //

    public static String getImageLink(String id){
        return String.format(FIREBASE_STORAGE_URL, FIREBASE_BUCKET, id);
    }
}
