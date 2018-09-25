package com.example.dealball.main.course.picture;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dealball.R;

public class PictureFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View pictureView = inflater.inflate(R.layout.picture_fragment, container, false);
        return pictureView;
    }
}
