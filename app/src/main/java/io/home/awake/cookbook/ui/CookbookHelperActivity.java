package io.home.awake.cookbook.ui;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.home.awake.cookbook.R;
import io.home.awake.cookbook.util.FragmentPageAdapterHelper;

public class CookbookHelperActivity extends FragmentActivity {
    @Bind(R.id.pager) ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookbook_helper);
        ButterKnife.bind(this);
        mViewPager.setAdapter(new FragmentPageAdapterHelper(getSupportFragmentManager()));
    }
}
