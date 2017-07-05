package com.oxilo.aurion.activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import com.oxilo.aurion.R;
import com.oxilo.aurion.WEB;

public class MainActivity extends AppCompatActivity {

    @OnClick(R.id.card_about_us)
    public void aboutUS(View v){
        clickAction("1");
    }

    @OnClick(R.id.free_zone)
    public void freeZone(View v){
        clickAction("2");
    }

    @OnClick(R.id.main_land)
    public void mainLand(View v){
        clickAction("4");
    }

    @OnClick(R.id.cardView3)
    public void offShore(View v){
        clickAction("3");
    }

    @OnClick(R.id.cardView4)
    public void coorporateIdentity(View v){
        clickAction("5");
    }

    @OnClick(R.id.cardView7)
    public void isoConsultation(View v){
        clickAction("6");
    }

    @OnClick(R.id.cardView2)
    public void accountAuditing(View v){
        clickAction("7");
    }

    @OnClick(R.id.cardView)
    public void proServices(View v){
        clickAction("8");
    }

    @OnClick(R.id.cardView5)
    public void contactUS(View v){
        clickAction(WEB.CONTACTUS.getValue());
    }

    private void clickAction(String url){
        Intent i = new Intent(MainActivity.this,Web.class);
        i.putExtra("WEB_URL",url);
        startActivity(i);
        overridePendingTransition(R.anim.move_right_in_activity, R.anim.move_left_out_activity);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }


}
