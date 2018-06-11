package com.example.dealball.main.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.dealball.R;
import com.example.dealball.main.login.LoginBYActivity;

public class MineFragment extends Fragment implements View.OnClickListener {

    Button login;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mineView = inflater.inflate(R.layout.mine_fragment,container, false);

        TextView title = mineView.findViewById(R.id.tv_title);
        title.setText("我的");

        login = mineView.findViewById(R.id.login);
        login.setOnClickListener(this);


        return mineView;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                Intent intent=new Intent(getActivity(), LoginBYActivity.class);
                startActivity(intent);
                break;


        }
    }
}
