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
import android.widget.Toast;

import example.eniac.alleycraps.R;

public class UserNameFragment extends Fragment {

    private static final String TAG = "UserNameFragment";

    private Button setUser;
    private EditText userName;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_user_name, container, false);
        setUser = view.findViewById(R.id.set_btn_usr);
        userName = view.findViewById(R.id.etxt_usr);

        setUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText() != null){
                    ((MainGUIActivity)getActivity()).setUser(userName.getText().toString());
                    ((MainGUIActivity)getActivity()).setViewPager(GUIStatePagerAdapter.MAIN_MENU);
                } else {
                    Toast.makeText(getActivity(),"Please enter a username", Toast.LENGTH_SHORT).show();
                }
            }
        });


        return view;
    }
}
