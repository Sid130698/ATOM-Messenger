package com.example.atommessenger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class TabsAccessorAdaptor extends FragmentPagerAdapter {
    public TabsAccessorAdaptor(FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch(position){
            case 0:
                ChatsFragment chatsFragment= new ChatsFragment();
                return  chatsFragment;
            case 2:
                ContactsFragment contactsFragment= new ContactsFragment();
                return  contactsFragment;
            case 1:
               GroupsFragment groupsFragment= new GroupsFragment();
                return  groupsFragment;
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
        switch(position){
            case 0:
               return "Chats";
            case 2:
               return "Contacts";
            case 1:
                return "Groups";
            default:
                return null;

        }
    }
}
