package com.example.dealball.main.course;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
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

        TabLayout course_tab = courseView.findViewById(R.id.course_tab);
        ViewPager course_viewpager = courseView.findViewById(R.id.course_viewpager);
        course_viewpager.setAdapter(new CourseTabAdapter(getChildFragmentManager()));
        course_tab.setupWithViewPager(course_viewpager);

        return courseView;
    }
}
