package io.home.awake.cookbook.ui;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.home.awake.cookbook.R;
import io.home.awake.cookbook.Recipe;

public class RecipeEditorActivity extends AppCompatActivity {
    @Bind(R.id.titleText) EditText titleText;
    @Bind(R.id.ingredientsText) EditText ingredientsText;
    @Bind(R.id.stepsText) EditText stepsText;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_editor);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Recipe recipe = getIntent().getParcelableExtra("recipe");
        if (recipe != null) {
            titleText.setText(recipe.getTitle());
            ingredientsText.setText(recipe.getIngredients());
            stepsText.setText(recipe.getSteps());
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit_cookbook, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                onSaveButtonClick();
                return true;
            case android.R.id.home:
                onUpButtonClick();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSaveButtonClick() {
        Recipe recipe = getIntent().getParcelableExtra("recipe");
        if (recipe == null)
            recipe = new Recipe();
        recipe.setTitle(titleText.getText().toString());
        recipe.setIngredients(ingredientsText.getText().toString());
        recipe.setSteps(stepsText.getText().toString());
        Intent resultIntent = new Intent();
        resultIntent.putExtra("recipe", recipe);
        setResult(RESULT_OK, resultIntent);
        finish();
    }

    public void onUpButtonClick() {
        setResult(RESULT_CANCELED);
        finish();
    }
}
