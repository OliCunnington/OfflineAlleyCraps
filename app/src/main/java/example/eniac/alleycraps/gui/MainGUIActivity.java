package example.eniac.alleycraps.gui;

import android.content.pm.ActivityInfo;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;

import example.eniac.alleycraps.R;
import example.eniac.alleycraps.elements.User;


public class MainGUIActivity extends AppCompatActivity {

    public static final String TAG = "MainGUIActivity";

    public static User user;

    private ViewPager container;
    private TextView userInfo;
    private File userFile, transactionsFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d(TAG,"onCreate: Started");

        setContentView(R.layout.activity_main_gui);
        container = findViewById(R.id.container);
        userInfo = findViewById(R.id.user_info_txt);

        setupViewPager();
        if (user == null)setViewPager(GUIStatePagerAdapter.USERNAME);
        else setViewPager(GUIStatePagerAdapter.MAIN_MENU);

        File directory = getApplicationContext().getFilesDir();
        File testDir =  new File (directory.toString()+"/users");
        if(testDir.exists()){
            //TODO
        }
        else userFile = new File(directory, "users");

        testDir = new File(directory.toString()+"/transactions");
        if (testDir.exists()){
            //TODO
        }
        else transactionsFile = new File(directory, "transactions");

        Log.d(TAG, "directory: "+directory.toString());

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_IMMERSIVE
                // Set the content to appear under the system bars so that the
                // content doesn't resize when the system bars hide and show.
                                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                // Hide the nav bar and status bar
                                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        Log.d(TAG, "onCreate: screen orientation & mode set");
    }

    private void setupViewPager(){
        GUIStatePagerAdapter adapter = new GUIStatePagerAdapter(getSupportFragmentManager());

        Log.d(TAG, "setupViewPager: "+adapter.toString());

        container.setAdapter(adapter);
    }

    public void setViewPager(int position){
        container.setCurrentItem(position);
    }

    public void setUser(String username){
        user = new User(username);
        userInfo.setText("\n\n"+user.getName()); //+"\n\n"+this.user.userWallet.privateKey+"\n\n"+this.user.userWallet.publicKey);
    }

    @Override
    protected void onStop() {

        super.onStop();
        //saveUser();
    }

    public void saveUser(){
        //TODO
    }
}
