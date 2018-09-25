package com.example.dealball.main.mine.collect;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dealball.R;

public class CollectFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View collectView = inflater.inflate(R.layout.collect_fragment, container, false);
        return collectView;
    }
}
