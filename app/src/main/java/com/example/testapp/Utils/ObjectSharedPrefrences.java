package com.example.testapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

public class ObjectSharedPrefrences{
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private Context context;
    private static String PREF_NAME="my pref";

    public ObjectSharedPrefrences(Context context)
    {
        this.context=context;
        sharedPreferences=context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
    }


    public  void

     SaveUser(ModelClass modelClass)
    {

        Gson gson=new Gson();
        String string=gson.toJson(modelClass);
        editor.putString("ModelClassObject",string);
        editor.commit();
    }

    public ModelClass GetUser()
    {
        Gson gson=new Gson();
        String getdata=sharedPreferences.getString("ModelClassObject","");
        ModelClass obj=  gson.fromJson(getdata,ModelClass.class);
        return obj;
    }


   }
