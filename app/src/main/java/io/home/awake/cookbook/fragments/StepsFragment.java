package io.home.awake.cookbook.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import io.home.awake.cookbook.R;


public class StepsFragment extends Fragment {
    private int pageNumber;

    public static StepsFragment newInstance(int page) {
        StepsFragment fragment = new StepsFragment();
        Bundle args = new Bundle();
        args.putInt("num", page);
        fragment.setArguments(args);
        return fragment;
    }

    public StepsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageNumber = 1;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View result=inflater.inflate(R.layout.fragment_steps, container, false);
        TextView pageHeader = (TextView)result.findViewById(R.id.stepsTextFragment);
        pageHeader.setText("ФрагментB " + String.valueOf(pageNumber));
        return result;
    }
}

