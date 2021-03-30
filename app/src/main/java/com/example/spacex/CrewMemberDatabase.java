package com.example.spacex;

import android.annotation.SuppressLint;
import android.content.AsyncQueryHandler;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Database(entities = {CrewMember.class}, version = 1, exportSchema = false)
public abstract class CrewMemberDatabase extends RoomDatabase {

    public static CrewMemberDatabase instance;
    private static Context activity;

    public abstract CrewMemberDoa crewMemberDoa();

    public static synchronized CrewMemberDatabase getInstance(Context context) {
        activity = context.getApplicationContext();
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CrewMemberDatabase.class, "crew_member_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}