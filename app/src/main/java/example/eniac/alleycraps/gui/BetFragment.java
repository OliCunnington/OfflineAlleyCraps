package example.eniac.alleycraps.gui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import example.eniac.alleycraps.R;

public class BetFragment extends Fragment {

    private SeekBar slider;
    private Button bet, back;
    private EditText bet_input;

    public BetFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmet_bet,container,false);
        back = view.findViewById(R.id.back_btn_bet);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainGUIActivity)getActivity()).setViewPager(GUIStatePagerAdapter.MAIN_MENU);
            }
        });

        bet = view.findViewById(R.id.btn_bet_dia);
        bet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainGUIActivity)getActivity()).setViewPager(GUIStatePagerAdapter.GAME);
            }
        });

        return view;
    }
}
