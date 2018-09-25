package com.example.dealball.main.course;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.dealball.main.course.friends.FriendsFragment;
import com.example.dealball.main.course.girl.GirlFragment;
import com.example.dealball.main.course.nearby.NearbyFragment;
import com.example.dealball.main.course.picture.PictureFragment;
import com.example.dealball.main.course.rank.RankFragment;
import com.example.dealball.main.course.recommend.RecommendFragment;
import com.example.dealball.main.course.video.VideoFragment;

public class CourseTabAdapter extends FragmentPagerAdapter {

    private RecommendFragment recommendFragment;
    private RankFragment rankFragment;
    private FriendsFragment friendsFragment;
    private GirlFragment girlFragment;
    private NearbyFragment nearbyFragment;
    private PictureFragment pictureFragment;
    private VideoFragment videoFragment;

    private String[] titles=new String[]{"推荐","排名","好友","附近","女生","图文","视频"};

    public CourseTabAdapter(FragmentManager fm) {
        super(fm);
        recommendFragment=new RecommendFragment();
        rankFragment=new RankFragment();
        friendsFragment=new FriendsFragment();
        girlFragment=new GirlFragment();
        nearbyFragment=new NearbyFragment();
        pictureFragment=new PictureFragment();
        videoFragment=new VideoFragment();

    }
    @Override
    public int getItemPosition(Object object) {
        if (object instanceof RecommendFragment) {
            return 0;
        } else if(object instanceof RankFragment){
            return 1;
        }else if(object instanceof FriendsFragment){
            return 2;
        }else if(object instanceof GirlFragment){
            return 3;
        }else if(object instanceof NearbyFragment){
            return 4;
        }else if(object instanceof PictureFragment){
            return 5;
        }else{
            return 6;
        }
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return recommendFragment;
            case 1:
                return rankFragment;
            case 2:
                return friendsFragment;
            case 3:
                return girlFragment;
            case 4:
                return nearbyFragment;
            case 5:
                return pictureFragment;
            case 6:
                return videoFragment;
            default:
                return recommendFragment;
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
