package example.eniac.alleycraps.gui;


import android.content.Intent;
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
public class GameModeFragment extends Fragment implements View.OnClickListener{

    private static final String TAG = "GameModeFragment";
    private Button twoDice,threeDice;


    public GameModeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_game_mode, container, false);
        twoDice = view.findViewById(R.id.two_btn_gm);
        threeDice = view.findViewById(R.id.three_btn_gm);
        twoDice.setOnClickListener(this);
        threeDice.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent(getActivity(),MainGameActivity.class);

        if(v.equals(twoDice)) intent.putExtra("mode", 2);
        else intent.putExtra("mode", 3);

        startActivity(intent);
    }
}
