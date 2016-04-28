package io.home.awake.cookbook.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Помощник в работе с бд.
 */
public class DBHelper extends SQLiteOpenHelper {
    private static final String tableName = "recipes";
    public DBHelper(Context context) {
        super(context, tableName, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table recipes (" +
                "_id integer primary key autoincrement," +
                "title text," +
                "ingredients text," +
                "steps text);"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF IT EXISTS " + tableName);
        db.execSQL("create table recipes (" +
                "_id integer primary key autoincrement," +
                "title text," +
                "ingredients text," +
                "steps text);"
        );
    }
}
