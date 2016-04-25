package io.home.awake.cookbook;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;
import static java.util.Calendar.*;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecipeEditorActivity extends AppCompatActivity {
    @Bind(R.id.titleText) EditText titleText;
    @Bind(R.id.descriptionText) EditText descriptionText;
    @Bind(R.id.dueDatePicker) DatePicker dueDatePicker;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_editor);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Recipe recipe = getIntent().getParcelableExtra("recipe");
        if (recipe != null) {
            titleText.setText(recipe.getTitle());
            descriptionText.setText(recipe.getDescription());
            Calendar calendar = recipe.getDueDateAsCalendar();
            dueDatePicker.init(calendar.get(YEAR), calendar.get(MONTH),
                    calendar.get(DAY_OF_MONTH), null);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cookbook, menu);
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
        if (recipe == null) recipe = new Recipe();
        recipe.setTitle(titleText.getText().toString());
        recipe.setDescription(descriptionText.getText().toString());
        recipe.setDueDate(dueDatePicker.getYear(),
                dueDatePicker.getMonth(), dueDatePicker.getDayOfMonth());
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