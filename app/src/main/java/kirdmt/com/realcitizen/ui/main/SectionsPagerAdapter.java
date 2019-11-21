package kirdmt.com.realcitizen.ui.main;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import kirdmt.com.realcitizen.R;
import kirdmt.com.realcitizen.ui.constitution.ConstitutionFragment;
import kirdmt.com.realcitizen.ui.first.FirstFragment;
import kirdmt.com.realcitizen.ui.policeOrder.PoliceOrderFragment;

class SectionsPagerAdapter extends FragmentPagerAdapter {

    Context context;

    public SectionsPagerAdapter(FragmentManager fm, Context nContext) {
        //super(fm);
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        context = nContext;
    }


    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new FirstFragment();

            case 1:
                return new ConstitutionFragment();

            case 2:
                return new PoliceOrderFragment();

        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {

            case 0:
                return context.getString(R.string.label_main);
            case 1:
                return context.getString(R.string.label_constitution);
            case 2:
                return context.getString(R.string.label_police);

        }
        return null;
    }
}
