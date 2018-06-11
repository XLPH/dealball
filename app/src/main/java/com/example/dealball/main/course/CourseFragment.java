package com.example.dealball.main.course;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dealball.R;

public class CourseFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View courseView = inflater.inflate(R.layout.course_fragment,container, false);

        TextView title = courseView.findViewById(R.id.tv_title);
        title.setText("球场");

        return courseView;
    }
}
