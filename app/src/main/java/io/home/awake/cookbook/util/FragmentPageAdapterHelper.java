package io.home.awake.cookbook.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import io.home.awake.cookbook.fragments.IngredientsFragment;
import io.home.awake.cookbook.fragments.StepsFragment;

/**
 * Created by Awake on 26.04.2016.
 */
public class FragmentPageAdapterHelper extends FragmentPagerAdapter {
    private String ingredients;
    public FragmentPageAdapterHelper(FragmentManager mgr, String text) {
        super(mgr);
        ingredients = text;
    }
    @Override
    public int getCount() {
        return(2);
    }
    @Override
    public Fragment getItem(int position) {
        if(position != 1) {
            return (IngredientsFragment.newInstance(position, ingredients));
        }else {
            return (StepsFragment.newInstance(position));
        }
    }
}
