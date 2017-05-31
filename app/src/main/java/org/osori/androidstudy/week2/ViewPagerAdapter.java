package org.osori.androidstudy.week2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by junsu on 2017-05-20.
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {

    // Fragment 를 가지고 있을 변수
    private List<Fragment> mFragmentList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }


    public void setFragment(List<Fragment> fragments) {
        mFragmentList = fragments;
    }

    // 몇번째 Page 에서 어떤 fragment 가 나올지 정의해준다.
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    // Page 의 title 을 정의해준다. 이것은 TabLayout 에 뜬다.
    @Override
    public CharSequence getPageTitle(int position) {
        return String.valueOf(position);
    }

    // 전체 Page 갯수를 정의해준다.
    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
