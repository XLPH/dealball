package com.example.dealball.main.course.nearby;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dealball.R;

public class NearbyFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View nearbyView = inflater.inflate(R.layout.nearby_fragment, container, false);
        return nearbyView;
    }
}
