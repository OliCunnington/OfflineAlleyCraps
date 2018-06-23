package example.eniac.alleycraps.gui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import example.eniac.alleycraps.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private static final String TAG = "SettingsFragment";

    private Button setUserName, reset,back;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        setUserName = view.findViewById(R.id.set_username_btn_settings);
        reset = view.findViewById(R.id.reset_btn_settings);
        back = view.findViewById(R.id.back_btn_settings);

        setUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainGUIActivity)getActivity()).setViewPager(GUIStatePagerAdapter.USERNAME);
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Not avail yet", Toast.LENGTH_SHORT).show();
            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ((MainGUIActivity)getActivity()).setViewPager(GUIStatePagerAdapter.MAIN_MENU);
            }
        });

        return view;
    }

}
