package com.example.petmed;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class User_ChatFragment extends Fragment {

//    EditText textMsg;
//    ImageButton imgBtn;
//    ListView chatListView;
//    ArrayAdapter arrayAdapter;
//    ArrayList<String> messages = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user__chat, container, false);
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tabLayoutVar);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.users);
//        ViewPager viewPager1 = (ViewPager) view.findViewById(R.id.chats);
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(getActivity().getSupportFragmentManager());

        viewPageAdapter.addFragments(new user_fragment_chat(), "Users");
        viewPageAdapter.addFragments(new chat_user(), "Chats");

        viewPager.setAdapter(viewPageAdapter);
//        viewPager1.setAdapter(viewPageAdapter);

        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.setupWithViewPager(viewPager1);
        


        return view;
    }

    class ViewPageAdapter extends FragmentPagerAdapter{

        private ArrayList<Fragment> fragments;
        private ArrayList<String> titles;

        ViewPageAdapter(FragmentManager fm){
            super(fm);
            this.fragments = new ArrayList<>();
            this.titles = new ArrayList<>();

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
        public void addFragments(Fragment fragment, String title) {
            fragments.add(fragment);
            titles.add(title);
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}