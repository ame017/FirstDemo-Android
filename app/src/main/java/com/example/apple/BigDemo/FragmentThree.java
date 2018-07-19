package com.example.apple.BigDemo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentThree extends Fragment {


    @BindView(R.id.ameTextView)
    AMETextView ameTextView;
    @BindView(R.id.button)
    Button button;
    Unbinder unbinder;

    public FragmentThree() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();

        LinearLayout.LayoutParams layoutListView = (LinearLayout.LayoutParams) ameTextView
                .getLayoutParams();
        int top = activity.titleBar.getHeight();
        Log.d("top", "onActivityCreated: "+ top);
        layoutListView.topMargin = top;
        ameTextView.setLayoutParams(layoutListView);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment_three, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.button)
    public void onViewClicked() {
        ameTextView.startAnimation();
    }
}
