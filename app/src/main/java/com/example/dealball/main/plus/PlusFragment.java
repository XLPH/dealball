package com.example.dealball.main.plus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.example.dealball.main.base.BaseFragment;

import com.example.dealball.R;

public class PlusFragment extends BaseFragment {

    private View plusView;
    private LinearLayout test2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        plusView = inflater.inflate(R.layout.plus_fragment,container, false);
        test2 = plusView.findViewById(R.id.test2);
        test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PlusActivity.class);
                startActivity(intent);
            }
        });


        return plusView;


    }
}
