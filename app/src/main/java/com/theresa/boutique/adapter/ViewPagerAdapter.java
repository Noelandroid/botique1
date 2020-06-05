package com.theresa.boutique.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.theresa.boutique.fragment.M1Fragment;
import com.theresa.boutique.fragment.M2Fragment;
import com.theresa.boutique.fragment.M3Fragment;

import io.reactivex.functions.Function3;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    static M1Fragment m1Frag= new M1Fragment();
    static M2Fragment m2Frag= new M2Fragment();
    static M3Fragment m3Frag=new M3Fragment();

    public ViewPagerAdapter(FragmentManager fm){
        super(fm);
        /*m1Frag = new M1Fragment();
        m2Frag = new M2Fragment();
        m3Frag = new M3Fragment();*/
    }

    @Override
    public Fragment getItem(int position) {

        Fragment mFragment = null;
        switch (position){
            case 0:
                mFragment = m1Frag;
                break;
            case 1:
                mFragment = m2Frag;
                break;
            case 2:
                mFragment = m3Frag;
                break;
        }

        return mFragment;
    }

    public Fragment getFrag(int position){
        Fragment frag = null;
        switch (position) {
            case 0:frag = m1Frag;
            break;
            case 1:frag = m2Frag;
                break;
            case 2:frag = m3Frag;
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
