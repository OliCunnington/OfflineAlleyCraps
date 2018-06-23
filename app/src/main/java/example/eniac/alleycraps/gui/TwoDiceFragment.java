package example.eniac.alleycraps.gui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import example.eniac.alleycraps.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class TwoDiceFragment extends Fragment {

    private Button back;


    public TwoDiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two_dice, container, false);
        back = view.findViewById(R.id.back_btn_game);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainGUIActivity)getActivity()).setViewPager(GUIStatePagerAdapter.MAIN_MENU);
            }
        });
        return view;
    }

}
