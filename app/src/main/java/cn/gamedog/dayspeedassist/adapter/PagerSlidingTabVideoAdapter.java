package cn.gamedog.dayspeedassist.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import cn.gamedog.dayspeedassist.fragment.TjFragment;
import cn.gamedog.dayspeedassist.fragment.VideoFragment;

/**
 * Created by Tech on 2016/8/16.
 */
public class PagerSlidingTabVideoAdapter extends FragmentPagerAdapter {

    private VideoFragment xy0;
    private VideoFragment xy1;
    private VideoFragment xy2;

    private final String[] titles = {"最新", "教学", "高手"};

    public PagerSlidingTabVideoAdapter(FragmentManager fm) {
        super(fm);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (xy0 == null) {
                    xy0 = new VideoFragment(2944 + "", null);
                }
                return xy0;
            case 1:
                if (xy1 == null) {
                    xy1 = new VideoFragment(3461 + "", null);
                }
                return xy1;
            case 2:
                if (xy2 == null) {
                    xy2 = new VideoFragment(3462 + "", null);
                }
                return xy2;


            default:
                return null;
        }
    }
}
