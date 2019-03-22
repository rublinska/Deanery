package com.example.deanery;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;


public class FragmentExtend extends Fragment {

    static SwipeRefreshLayout swipeRefresh;

    public static FragmentExtend newInstance(SwipeRefreshLayout swipe) {
        FragmentExtend fragment = new FragmentExtend();

        swipeRefresh = swipe;
        return fragment;
    }
}
