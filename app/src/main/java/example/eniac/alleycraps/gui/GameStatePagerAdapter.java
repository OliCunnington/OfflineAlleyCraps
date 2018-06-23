package example.eniac.alleycraps.gui;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GameStatePagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> gameFragments = new ArrayList<>();

    public static final int TWO = 0;
    public static final int THREE = 1;

    public GameStatePagerAdapter(FragmentManager fm) {
        super(fm);
        gameFragments.add(new TwoDiceFragment());
        gameFragments.add(new ThreeDiceFragment());
    }



    @Override
    public Fragment getItem(int position) {
        return gameFragments.get(position);
    }

    @Override
    public int getCount() {
        return gameFragments.size();
    }
}
