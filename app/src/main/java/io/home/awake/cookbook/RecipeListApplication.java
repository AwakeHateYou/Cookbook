package io.home.awake.cookbook;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;

public class RecipeListApplication extends Application {
    private DBHelper dbHelper;

    public SQLiteDatabase getDatabase() {
        return dbHelper.getWritableDatabase();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        dbHelper = new DBHelper(this);
    }
}
