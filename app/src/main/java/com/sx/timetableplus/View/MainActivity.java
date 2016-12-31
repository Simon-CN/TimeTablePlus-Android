package com.sx.timetableplus.View;

import android.annotation.TargetApi;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.sx.timetableplus.Global.StaticResource;
import com.sx.timetableplus.Global.Timetable;
import com.sx.timetableplus.Model.UserInfo;
import com.sx.timetableplus.R;
import com.sx.timetableplus.Utility.GlideUtil;
import com.sx.timetableplus.View.Activity.Timeline.AddTimelineActivity;
import com.sx.timetableplus.View.Adapter.MainPagerAdapter;
import com.sx.timetableplus.View.Fragment.SocialFragment;
import com.sx.timetableplus.View.Fragment.TimelineFragment;
import com.sx.timetableplus.View.Fragment.TimetableFragment;
import com.sx.timetableplus.databinding.ActivityMainBinding;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public ActivityMainBinding mBinding;
    private PopupMenu pMenu;
    private Menu moreMenu;
    private MainPagerAdapter mAdapter;
    private List<Fragment> fragmentList = new ArrayList<>();

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();
        initListener();
    }

    private void initView() {
        pMenu = new PopupMenu(this, mBinding.moreButton);
        moreMenu = pMenu.getMenu();
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_timetable_setting, moreMenu);

        TextView username = (TextView) mBinding.mainNavigation.getHeaderView(0).findViewById(R.id.main_user_name);
        TextView userdesc = (TextView) mBinding.mainNavigation.getHeaderView(0).findViewById(R.id.main_user_desc);
        ImageView portrait = (ImageView) mBinding.mainNavigation.getHeaderView(0).findViewById(R.id.main_user_portrait);

        UserInfo ui = UserInfo.getInstance(this);

        username.setText(ui.getScreenName());
        userdesc.setText(ui.getDesc());
        GlideUtil.glidePortrait(this, portrait, ui.getPortrait());

        initData();

        initViewPager();
        mBinding.homeRadioGroup.check(R.id.home_radio_button);
    }

    private void initListener() {
        mBinding.hamburgerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mBinding.mainDrawerLy.isDrawerOpen(GravityCompat.START)) {
                    mBinding.mainDrawerLy.closeDrawer(GravityCompat.START);
                } else {
                    mBinding.mainDrawerLy.openDrawer(GravityCompat.START);
                }

            }
        });

        mBinding.mainViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectPage(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBinding.homeRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.home_radio_button:
                        selectPage(0);
                        break;
                    case R.id.timeline_radio_button:
                        selectPage(1);
                        break;
                    case R.id.social_radio_button:
                        selectPage(2);
                        break;
                    default:
                        break;
                }
            }
        });

        mBinding.mainNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mBinding.mainDrawerLy.closeDrawers();
                return true;
            }
        });
    }

    private void initViewPager() {
        fragmentList.add(new TimetableFragment());
        fragmentList.add(new TimelineFragment());
        fragmentList.add(new SocialFragment());
        mBinding.mainViewPager.setOffscreenPageLimit(3);
        mAdapter = new MainPagerAdapter(getSupportFragmentManager(), fragmentList);
        mBinding.mainViewPager.setAdapter(mAdapter);
    }

    private void selectPage(int position) {
        mBinding.homeRadioGroup.check(mBinding.homeRadioGroup.getChildAt(position).getId());
        mBinding.mainViewPager.setCurrentItem(position);
        if (position == 0) {
            mBinding.moreButton.setVisibility(View.VISIBLE);
        } else {
            mBinding.moreButton.setVisibility(View.GONE);
            if (position == 1) {
                refreshTimeline();
            }
        }
    }

    private void refreshTimeline() {
        TimelineFragment temp = (TimelineFragment) mAdapter.instantiateItem(mBinding.mainViewPager, 1);
        temp.startLoading();
    }

    public void popupmenu(View view) {
        pMenu.show();
    }

    private void initData() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }
}