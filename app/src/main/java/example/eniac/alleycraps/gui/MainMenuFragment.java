package example.eniac.alleycraps.gui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import example.eniac.alleycraps.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainMenuFragment extends Fragment {

    private static final String TAG = "MeinMenuFragment";


    private Button play,leaderboards,credits,howToPlay,settings;


    public MainMenuFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG,"onCreateView: created");
        // Inflate the layout for this fragment
        View view;
            view = inflater.inflate(R.layout.fragment_main_menu, container, false);
            play = view.findViewById(R.id.play_now_btn_mm);
            leaderboards = view.findViewById(R.id.leader_btn_mm);
            credits = view.findViewById(R.id.credits_btn_mm);
            howToPlay = view.findViewById(R.id.how_to_btn_mm);
            settings = view.findViewById(R.id.settings_btn_mm);


            play.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    ((MainGUIActivity) getActivity()).setViewPager(GUIStatePagerAdapter.BET);
                    /*
                    Intent intent = new Intent(getActivity(),MainGameActivity.class);
                    intent.putExtra("mode", 2);
                    startActivity(intent);
                    */

                }
            });

            leaderboards.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Not avail yet", Toast.LENGTH_SHORT).show();
                }
            });

            credits.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    Toast.makeText(getActivity(), "Not avail yet", Toast.LENGTH_SHORT).show();
                }
            });

            howToPlay.setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    ((MainGUIActivity) getActivity()).setViewPager(GUIStatePagerAdapter.INSTRUCTIONS);
                }
            });

            settings.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((MainGUIActivity) getActivity()).setViewPager(GUIStatePagerAdapter.SETTINGS);
                }
            });


        return view;
    }



}
