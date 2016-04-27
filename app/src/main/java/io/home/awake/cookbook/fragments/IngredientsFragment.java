package io.home.awake.cookbook.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.home.awake.cookbook.R;


public class IngredientsFragment extends Fragment {
    private String ingredients;
    public static IngredientsFragment newInstance(String ingredients) {
        IngredientsFragment fragment = new IngredientsFragment();
        Bundle args = new Bundle();
        args.putString("ing", ingredients);
        fragment.setArguments(args);
        return fragment;
    }

    public IngredientsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ingredients = getArguments() != null ? getArguments().getString("ing") : "";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.fragment_ingredients, container, false);
        TextView pageHeader = (TextView)result.findViewById(R.id.ingredientsTextFragment);
        pageHeader.setText(ingredients);
        return result;
    }
}
