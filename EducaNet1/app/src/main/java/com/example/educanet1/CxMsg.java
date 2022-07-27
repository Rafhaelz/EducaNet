package com.example.educanet1;

import android.app.Activity;
import android.app.AlertDialog;

public class CxMsg {
    public static void mostrar(String txt, Activity activity){
        AlertDialog.Builder adb = new AlertDialog.Builder(activity);
        adb.setMessage(txt);
        adb.setNeutralButton("OK", null);
        adb.show();
    }
}
