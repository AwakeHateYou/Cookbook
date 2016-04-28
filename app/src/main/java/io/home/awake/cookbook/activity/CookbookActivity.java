package io.home.awake.cookbook.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import io.home.awake.cookbook.util.DBHelper;
import io.home.awake.cookbook.R;
import io.home.awake.cookbook.model.Recipe;
import io.home.awake.cookbook.util.SwipeDismissListViewTouchListener;

public class CookbookActivity extends AppCompatActivity {
    @Bind(R.id.toolbarMain) Toolbar toolbar;
    @Bind(R.id.recipeList) ListView recipeListView;
    SimpleCursorAdapter adapter;
    Cursor recipeListCursor;
    SQLiteDatabase db;
    DBHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookbook);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        dbh = new DBHelper(getApplication());
        db = dbh.getWritableDatabase();
        // db.execSQL("insert into recipes(title, ingredients, steps) values('Яйцо', 'коко', '123')");
        initListAdapter();
        swipe();
    }

    private void initListAdapter() {
        String[] param = new String[]{"Яйцо"};
//        recipeListCursor = db.rawQuery("select * from recipes where ingredients like '%лук%'", null);
        recipeListCursor = db.query("recipes", null, null, null, null, null, "_id desc");
        // tableName, tableColumns, whereClause, whereArgs, groupBy, having, orderBy
        String[] from = new String[]{"title"};
        int[] to = new int[]{R.id.titleText};
        adapter = new SimpleCursorAdapter(this,
                R.layout.recipe_item, recipeListCursor, from, to,
                CursorAdapter.FLAG_AUTO_REQUERY);
        recipeListView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_filter, menu);
        return true;
    }
    @OnClick(R.id.fab)
    public void onFABClick(View view) {
        Intent intent = new Intent(this, RecipeEditorActivity.class);
        startActivityForResult(intent, 1);
    }

    @OnItemLongClick(R.id.recipeList)
    public boolean onListItemLongClicked(int position) {
        String itemId = String.valueOf(adapter.getItemId(position));
        Cursor cursor = db.query("recipes", null,
                "_id = ?", new String[]{itemId}, null, null, null);
        cursor.moveToNext();
        Recipe recipe = new Recipe(
                Integer.valueOf(cursor.getInt(cursor.getColumnIndex("_id"))),
                cursor.getString(cursor.getColumnIndex("title")),
                cursor.getString(cursor.getColumnIndex("ingredients")),
                cursor.getString(cursor.getColumnIndex("steps")));
        cursor.close();
        Intent intent = new Intent(this, RecipeEditorActivity.class);
        intent.putExtra("recipe", recipe);
        startActivityForResult(intent, 1);
        return true;
    }
    @OnItemClick(R.id.recipeList)
    public void onListItemClicked(int position){
        String itemId = String.valueOf(adapter.getItemId(position));
        Cursor cursor = db.query("recipes", null,
                "_id = ?", new String[]{itemId}, null, null, null);
        cursor.moveToNext();
        Recipe recipe = new Recipe(
                Integer.valueOf(cursor.getInt(cursor.getColumnIndex("_id"))),
                cursor.getString(cursor.getColumnIndex("title")),
                cursor.getString(cursor.getColumnIndex("ingredients")),
                cursor.getString(cursor.getColumnIndex("steps")));
        cursor.close();
        Intent intent = new Intent(this, CookbookHelperActivity.class);
        intent.putExtra("recipe", recipe);
        startActivity(intent);
    }

    public void swipe() {
        SwipeDismissListViewTouchListener touchListener =
                new SwipeDismissListViewTouchListener(
                        recipeListView,
                        new SwipeDismissListViewTouchListener.DismissCallbacks() {
                            @Override
                            public boolean canDismiss(int position) {
                                return true;
                            }

                            @Override
                            public void onDismiss(ListView listView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    db.delete("recipes", "_id = " + String.valueOf(adapter.getItemId(position)), null);
                                    recipeListCursor.requery();
                                }
                                adapter.notifyDataSetChanged();
                            }
                        });
        recipeListView.setOnTouchListener(touchListener);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode != RESULT_OK) return;
            Recipe recipe = data.getParcelableExtra("recipe");
            ContentValues cv = new ContentValues();
            cv.put("title", recipe.getTitle());
            cv.put("ingredients", recipe.getIngredients());
            cv.put("steps", recipe.getSteps());
            if (recipe.isNew()) {
                db.insert("recipes", null, cv);
            } else {
                db.update("recipes", cv, "_id = ?",
                        new String[] { String.valueOf(recipe.getId()) });
            }
            recipeListCursor.requery();
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
