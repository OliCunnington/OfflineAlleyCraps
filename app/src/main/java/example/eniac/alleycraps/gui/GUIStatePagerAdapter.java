package example.eniac.alleycraps.gui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import java.util.ArrayList;
import java.util.List;

public class GUIStatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragmentList = new ArrayList<>();

    public static final int MAIN_MENU = 0;
    public static final int GAME = 1;
    public static final int INSTRUCTIONS = 2;
    public static final int USERNAME = 3;
    public static final int SETTINGS = 4;
    public static final int BET = 5;

    protected GUIStatePagerAdapter(FragmentManager fm) {
        super(fm);
        mFragmentList.add(new MainMenuFragment());
        mFragmentList.add(new TwoDiceFragment());
        mFragmentList.add(new InstructionFragment());
        mFragmentList.add(new UserNameFragment());
        mFragmentList.add(new SettingsFragment());
        mFragmentList.add(new BetFragment());
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
