package com.example.dealball.main.index;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dealball.R;

public class IndexFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View indexView = inflater.inflate(R.layout.index_fragment,container, false);

        TextView title = indexView.findViewById(R.id.tv_title);
        title.setText("首页");

        return indexView;
    }
}
