package example.eniac.alleycraps.gui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import example.eniac.alleycraps.R;

public class MainGameActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "MainGameActivity";
    private Button back;
    private ViewPager dice_layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_game);

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

        dice_layout = findViewById(R.id.dice_container);
        setupGamePagerAdapter();
        back = findViewById(R.id.back_btn_game);
        back.setOnClickListener(this);
        initDice(getIntent().getIntExtra("mode", 0));
    }

    private void setupGamePagerAdapter(){
        GameStatePagerAdapter adapter = new GameStatePagerAdapter(getSupportFragmentManager());
        dice_layout.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, MainGUIActivity.class);
        startActivity(intent);
    }

    public void setViewPager(int position){
        dice_layout.setCurrentItem(position);
    }

    public void initDice(int i){

        switch(i){
            case 2: setViewPager(GameStatePagerAdapter.TWO); break;
            case 3: setViewPager(GameStatePagerAdapter.THREE); break;
            default: startActivity(new Intent(this, MainGUIActivity.class));
                Log.d(TAG, "No reference found for dice fragment");
        }

    }
}
