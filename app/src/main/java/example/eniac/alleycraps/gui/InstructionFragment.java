package example.eniac.alleycraps.gui;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import example.eniac.alleycraps.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class InstructionFragment extends Fragment {

    public static final String TAG = "InstructionsFragment";

    private Button back;


    public InstructionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d(TAG,"onCreateView: created");

        View view = inflater.inflate(R.layout.fragment_instruction, container, false);
        back = view.findViewById(R.id.back_btn_inst);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainGUIActivity)getActivity()).setViewPager(GUIStatePagerAdapter.MAIN_MENU);
            }
        });

        return view;
    }

}
