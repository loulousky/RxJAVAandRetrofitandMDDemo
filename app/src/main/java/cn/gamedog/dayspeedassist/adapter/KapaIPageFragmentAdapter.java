package cn.gamedog.dayspeedassist.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import cn.gamedog.dayspeedassist.fragment.TjFragment;

/**
 * Created by Tech on 2016/8/15.
 */
public class KapaIPageFragmentAdapter extends FragmentStatePagerAdapter {
    private TjFragment xy0;
    private TjFragment xy1;
    private TjFragment xy2;
    private TjFragment xy3;
    private TjFragment xy4;
    private TjFragment xy5;
    private final String[] titles = { "最新", "R级","S级","A级","B级","C级"};
    public KapaIPageFragmentAdapter(FragmentManager fm) {
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
                    xy0 = new TjFragment(3455+"",null);
                }
                return xy0;
            case 1:
                if (xy1 == null) {
                    xy1 = new TjFragment(30538+"",null);
                }
                return xy1;
            case 2:
                if (xy2 == null) {
                    xy2 = new TjFragment(3456+"",null);
                }
                return xy2;
            case 3:
                if (xy3 == null) {
                    xy3 = new TjFragment(3457+"",null);
                }
                return xy3;
            case 4:
                if (xy4 == null) {
                    xy4 = new TjFragment(3458+"",null);
                }
                return xy4;
            case 5:
                if (xy5 == null) {
                    xy5 = new TjFragment(3459+"",null);
                }
                return xy5;

            default:
                return null;
        }
    }
}
