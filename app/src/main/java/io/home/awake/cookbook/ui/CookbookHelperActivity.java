package io.home.awake.cookbook.ui;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.home.awake.cookbook.R;
import io.home.awake.cookbook.Recipe;
import io.home.awake.cookbook.fragments.IngredientsFragment;
import io.home.awake.cookbook.fragments.StepsFragment;
import io.home.awake.cookbook.util.FragmentPageAdapterHelper;

public class CookbookHelperActivity extends FragmentActivity {
    @Bind(R.id.pager) ViewPager mViewPager;
    @Bind(R.id.toolbarHelper) Toolbar toolbar;
    private String ingredientsText;
    private String stepsText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookbook_helper);
        ButterKnife.bind(this);
        Recipe recipe = getIntent().getParcelableExtra("recipe");
        if (recipe != null) {
            ingredientsText = recipe.getIngredients();
            stepsText = recipe.getSteps();
        }
        mViewPager.setAdapter(new FragmentPageAdapterHelper(getSupportFragmentManager(), ingredientsText, stepsText));
    }
}
