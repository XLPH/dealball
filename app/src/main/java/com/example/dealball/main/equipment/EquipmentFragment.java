package com.example.dealball.main.equipment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.dealball.R;

public class EquipmentFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View equipmentView = inflater.inflate(R.layout.equipment_fragment,container, false);

        TextView title = equipmentView.findViewById(R.id.tv_title);
        title.setText("装备");

        return equipmentView;
    }
}
