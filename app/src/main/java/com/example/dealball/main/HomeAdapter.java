package com.example.dealball.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dealball.main.course.CourseFragment;
import com.example.dealball.main.equipment.EquipmentFragment;
import com.example.dealball.main.index.IndexFragment;
import com.example.dealball.main.mine.MineFragment;
import com.example.dealball.main.plus.PlusFragment;

public class HomeAdapter extends FragmentPagerAdapter{
    private IndexFragment indexFragment;
    private CourseFragment courseFragment;
    private PlusFragment plusFragment;
    private EquipmentFragment equipmentFragment;
    private MineFragment mineFragment;

    public HomeAdapter(FragmentManager fm) {
        super(fm);
        indexFragment=new IndexFragment();
        courseFragment=new CourseFragment();
        plusFragment =new PlusFragment();
        equipmentFragment=new EquipmentFragment();
        mineFragment=new MineFragment();
    }

    @Override
    public int getItemPosition(Object object) {
        if(object instanceof IndexFragment) {
            return 0;
        }else if(object instanceof CourseFragment){
            return 1;
        }else if(object instanceof PlusFragment){
            return 2;
        }else if(object instanceof EquipmentFragment){
            return 3;
        }else{
            return 4;
        }


    }
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return indexFragment;
            case 1:
                return courseFragment;
            case 2:
                return plusFragment;
            case 3:
                return equipmentFragment;
            case 4:
                return mineFragment;
            default:
                return indexFragment;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }
}
