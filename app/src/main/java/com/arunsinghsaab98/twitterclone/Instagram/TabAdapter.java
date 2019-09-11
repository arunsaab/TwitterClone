package com.arunsinghsaab98.twitterclone.Instagram;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.arunsinghsaab98.twitterclone.Instagram.fragment.ProfileTab;
import com.arunsinghsaab98.twitterclone.Instagram.fragment.SharePictureTab;
import com.arunsinghsaab98.twitterclone.Instagram.fragment.UserTab;


public class TabAdapter extends FragmentPagerAdapter {



    public TabAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int tabPosition) {

        switch (tabPosition)
        {
            case 0:
                return new ProfileTab();

            case 1:
                return new UserTab();

            case 2:
                return new SharePictureTab();

                default:
                    return null;

        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position)
        {
            case 0:
                return "profile Tab";
            case 1:
                return "user Tab";
            case 2:
                return "Share Picture Tab";

                default:
                    return null;

        }
    }
}
