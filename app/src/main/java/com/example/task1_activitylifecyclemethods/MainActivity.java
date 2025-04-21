package com.example.task1_activitylifecyclemethods;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.tabs.TabLayout;
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showLog("onCreate called");
        MaterialButton buyButton1 = findViewById(R.id.card1).findViewById(R.id.buyButton1);
        MaterialButton sellButton1 = findViewById(R.id.card1).findViewById(R.id.sellButton1);
        buyButton1.setOnClickListener(v -> showToast("Buy clicked for Dodge Challenger SRT"));
        sellButton1.setOnClickListener(v -> showToast("Sell clicked for Dodge Challenger SRT"));
        MaterialButton buyButton2 = findViewById(R.id.card2).findViewById(R.id.buyButton2);
        MaterialButton sellButton2 = findViewById(R.id.card2).findViewById(R.id.sellButton2);
        buyButton2.setOnClickListener(v -> showToast("Buy clicked for Porsche 911 GT3 RS"));
        sellButton2.setOnClickListener(v -> showToast("Sell clicked for Porsche 911 GT3 RS"));
        MaterialButton buyButton3 = findViewById(R.id.card3).findViewById(R.id.buyButton3);
        MaterialButton sellButton3 = findViewById(R.id.card3).findViewById(R.id.sellButton3);
        buyButton3.setOnClickListener(v -> showToast("Buy clicked for Ferrari LaFerrari"));
        sellButton3.setOnClickListener(v -> showToast("Sell clicked for Ferrari LaFerrari"));
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        showToast("Home tab selected");
                        break;
                    case 1:
                        showToast("User tab selected");
                        break;
                    case 2:
                        showToast("Info tab selected");
                        break;
                }
            } @Override public void onTabUnselected(TabLayout.Tab tab) {
            } @Override public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }
    public void toggleStar(View view) {
        ImageView starIcon = (ImageView) view;
        int cardId = Integer.parseInt(view.getTag().toString());

        if (starIcon.getTag() == null || !starIcon.getTag().equals("liked")) {

            starIcon.setImageResource(R.drawable.ic_star_filled);
            starIcon.setTag("liked");
            showToast("Liked Card " + cardId);
        } else {

            starIcon.setImageResource(R.drawable.ic_star_outline);
            starIcon.setTag(null);
            showToast("Unliked Card " + cardId);
        }
    }
    @Override  protected void onStart() {
        super.onStart();
        showLog("onStart called");
    }    @Override protected void onResume() {
        super.onResume();
        showLog("onResume called");
    } @Override protected void onPause() {
        super.onPause();
        showLog("onPause called");
    } @Override protected void onStop() {
        super.onStop();
        showLog("onStop called");
    }
    @Override  protected void onRestart() {
        super.onRestart();
        showLog("onRestart called");
    }  @Override protected void onDestroy() {
        super.onDestroy();
        showLog("onDestroy called");
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void showLog(String message) {
        Log.d(TAG, message);
    }
}