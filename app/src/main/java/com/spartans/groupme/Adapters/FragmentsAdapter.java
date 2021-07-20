package com.spartans.groupme.Adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.spartans.groupme.Fragments.callsFragment;
import com.spartans.groupme.Fragments.chatsFragment;
import com.spartans.groupme.Fragments.statusFragment;

import org.jetbrains.annotations.NotNull;

public class FragmentsAdapter extends FragmentPagerAdapter {
    public FragmentsAdapter(@NonNull @NotNull FragmentManager fm) {
        super(fm);
    }

    public FragmentsAdapter(@NonNull @NotNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new chatsFragment();

            case 1:
                return new statusFragment();

            case 2:
                return new callsFragment();
            default:
                return new chatsFragment();

        }

    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if(position==0){
            title = "CHATS";
        }
        if(position==1){
            title = "STATUS";
        }
        if(position==2){
            title = "CALLS";
        }
        return title;
    }
}
