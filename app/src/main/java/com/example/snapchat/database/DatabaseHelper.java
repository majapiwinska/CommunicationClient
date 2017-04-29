package com.example.snapchat.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.snapchat.database.model.Snap;
import com.example.snapchat.database.model.User;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

/**
 * Created by maja on 31.03.17.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    public static final String DATABASE_NAME = "snapchat.db";
    public static final int DATABASE_VERSION = 10;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        System.out.println("Tworzenie bazy");
        Log.i("databaseHelper","Creating database.");
        createTables(connectionSource);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        Log.i("databaseHelper","Upgrading database from version " + oldVersion + " to " + newVersion + ".");
        try {
            onCreate(database, connectionSource);
        } catch (Exception e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop db", e);
            throw new RuntimeException(e);
        }
    }

    private void createTables(ConnectionSource connectionSource) {

       try {
           TableUtils.createTableIfNotExists(connectionSource, Snap.class);
       } catch (java.sql.SQLException e) {
           Log.i("databaseHelper","Fatal error, cannot create database,tabl¨e photo!", e);
       }
       try {
            TableUtils.createTableIfNotExists(connectionSource, User.class);
        } catch (java.sql.SQLException e) {
            Log.i("databaseHelper","Fatal error, cannot create database,table user!", e);
        }

    }
}
