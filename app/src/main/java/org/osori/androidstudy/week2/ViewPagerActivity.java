package org.osori.androidstudy.week2;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import org.osori.androidstudy.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager Activity
 *
 * 중요 keyword
 * ViewPager
 */

public class ViewPagerActivity extends AppCompatActivity {

    private final String TAG = "ViewPagerActivity";

    // Layout 에 있는 View 객체에 대해 선언함
    private ViewPager viewpager;
    private TabLayout tabLayout;

    // Adapter 선언
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 해당 activity 에 띄우고 싶은 layout 을 setContentView 의 param 에 넣어준다.
        setContentView(R.layout.activity_viewpager);

        // xml 에 정의 되어있는 view 를 객체와 bind 해준다.
        // 이 때 view 는 위에 setContentView param 으로 쓴 layout 내에 있어야한다.
        viewpager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);

        List<Fragment> existFragments = getSupportFragmentManager().getFragments();
        List<Fragment> fragmentList = new ArrayList<>();

        // 첫 if 문 코드는 그냥 그렇구나 하고 치면 됨
        if (existFragments != null && existFragments.size() != 0) {
            boolean isExist = false;
            for (Fragment existFragment : existFragments) {
                if (existFragment instanceof FirstFragment) {
                    fragmentList.add(existFragment);
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                fragmentList.add(FirstFragment.newInstance());
            }
            isExist = false;
            for (Fragment existFragment : existFragments) {
                if (existFragment instanceof SecondFragment) {
                    fragmentList.add(existFragment);
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                fragmentList.add(SecondFragment.newInstance());
            }
            isExist = false;
            for (Fragment existFragment : existFragments) {
                if (existFragment instanceof ThirdFragment) {
                    fragmentList.add(existFragment);
                    isExist = true;
                    break;
                }
            }
            if (!isExist) {
                fragmentList.add(ThirdFragment.newInstance());
            }
        }
        // Adapter 에 넣을 fragment list 에 fragment 들을 생성해서 넣어줌
        else {
            fragmentList.add(FirstFragment.newInstance());
            fragmentList.add(SecondFragment.newInstance());
            fragmentList.add(ThirdFragment.newInstance());
        }

        // ViewPager 안에 fragment 를 넣어주려면 FragmentPagerAdapter 를 상속받은 adapter 가 필요하다.
        // 해당 adapter 에서 어떤 fragment 가 나올지를 setting 해준다.
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.setFragment(fragmentList);

        // 이후 ViewPager 의 adapter 를 set 해주면 adapter 에 설정해놓은 것이 ViewPager 에 반영된다.
        viewpager.setAdapter(viewPagerAdapter);

        // TabLayout 은 setupWithViewPager 함수를 통해 ViewPager 에 달아놓으면 ViewPager 의 움직임에 따라
        // 알아서 control 이 된다.
        tabLayout.setupWithViewPager(viewpager);
    }
}
