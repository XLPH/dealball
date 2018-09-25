package com.example.dealball.main.mine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dealball.main.course.rank.RankFragment;

import com.example.dealball.main.mine.collect.CollectFragment;
import com.example.dealball.main.mine.post.PostFragment;
import com.example.dealball.main.mine.record.RecordFragment;

public class MineTabAdapter extends FragmentPagerAdapter {

    private RankFragment rankFragment;
    private PostFragment postFragment;
    private RecordFragment recordFragment;
    private CollectFragment collectFragment;

    private String[] titles=new String[]{"发帖","排名","收藏","约球记录"};

    public MineTabAdapter(FragmentManager fm) {
        super(fm);

        rankFragment=new RankFragment();
        postFragment=new PostFragment();
        recordFragment=new RecordFragment();
        collectFragment=new CollectFragment();

    }
    @Override
    public int getItemPosition(Object object) {
        if (object instanceof PostFragment) {
            return 0;
        } else if(object instanceof RankFragment){
            return 1;
        }else if(object instanceof CollectFragment){
            return 2;
        }else{
            return 3;
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return postFragment;
            case 1:
                return rankFragment;
            case 2:
                return collectFragment;
            case 3:
                return recordFragment;
            default:
                return postFragment;
        }
    }


    @Override
    public int getCount() {
        return titles.length;
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
