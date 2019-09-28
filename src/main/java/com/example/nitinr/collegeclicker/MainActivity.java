package com.example.nitinr.collegeclicker;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView collegeImage,coin, coin2, coin3, coin4, perk, perk2, perk3, perk4;
    TextView tvtap, tvbudget, tvcount, tvminus, tvtuition;
    int budget = 0;
    int click;
    CountDownTimer ct = null;
    boolean gameOver = false;



    public void rounds(){
        final  ImageView[] perks = {perk, perk2, perk3, perk4};

        click++;
        tvcount.setText(String.valueOf(click));

        Random r = new Random();

        int round = click + (r.nextInt(40));

        if(click == round){



                Random pick = new Random();
                final int select = pick.nextInt(4);

                perks[select].setVisibility(View.VISIBLE);
                 perks[select].startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.credit_animation));
                ct = new CountDownTimer(3000, 1000 ) {

                    @Override
                    public void onTick(long l) {

                    }

                    @Override
                    public void onFinish() {
                        perks[select].setVisibility(View.INVISIBLE);
                    }
                }.start();


             perkOnClick(select);




        }


    }

    public void perkOnClick(int select){
        final  ImageView[] perks = {perk, perk2, perk3, perk4};

         perks[select].setOnClickListener(new View.OnClickListener() {
                @SuppressLint({"SetTextI18n", "ResourceAsColor"})
                public void onClick(View v) {
                    if(click < 199) {
                        budget -= 850;

                        tvminus.setText("-850");
                        tvminus.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.minus_animation));
                        tvbudget.setText(String.valueOf("$" + String.valueOf(budget)));

                        ct.onFinish();
                    } else if(click >=200 && click <799){
                        budget -= 1250;

                        tvminus.setText("-1250");
                        tvminus.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.minus_animation));
                        tvbudget.setText(String.valueOf("$" + String.valueOf(budget)));

                        ct.onFinish();
                    } else{
                        budget -= 3000;

                        tvminus.setText("-3000");
                        tvminus.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.minus_animation));
                        tvbudget.setText(String.valueOf("$" + String.valueOf(budget)));

                        ct.onFinish();
                    }
                }



            });

        }




    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    public void classes(String onTap) {

        Random random = new Random();
        int numberClasses = random.nextInt(20) + 1;

        tvtap = findViewById(R.id.tvtap);

        onTap = String.valueOf(numberClasses);


        tvtap.setText(onTap + " classes");
        tvtap.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.jump));

        tvtap.setVisibility(View.INVISIBLE);


        //budget logic
        if(numberClasses < 7){
            budget +=  15;
            tvminus.setText("+15");
        }
        else if(numberClasses > 7 && numberClasses <= 14){
            budget += 25;
            tvminus.setText("+25");
        } else{
            budget += 50;
            tvminus.setText("+50");
        }
        tvminus.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.minus_animation));
        tvminus.setVisibility(View.INVISIBLE);
        tvbudget.setText(String.valueOf("$" + String.valueOf(budget)));


    }



    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //resource IDS
        tvminus = findViewById(R.id.tvminus);
        tvtuition = findViewById(R.id.tvtuition);
        coin = findViewById(R.id.coin1);
        coin2 = findViewById(R.id.coin2);
        coin3 = findViewById(R.id.coin3);
        coin4= findViewById(R.id.coin4);
        perk = findViewById(R.id.perk);
        perk2 = findViewById(R.id.perk2);
        perk3 = findViewById(R.id.perk3);
        perk4 = findViewById(R.id.perk4);
        perk.setVisibility(View.INVISIBLE);
        perk2.setVisibility(View.INVISIBLE);
        perk3.setVisibility(View.INVISIBLE);
        perk4.setVisibility(View.INVISIBLE);
        coin.setVisibility(View.INVISIBLE);
        coin2.setVisibility(View.INVISIBLE);
        coin3.setVisibility(View.INVISIBLE);
        coin4.setVisibility(View.INVISIBLE);
        tvbudget = findViewById(R.id.tvbudget);
        tvcount = findViewById(R.id.tvcount);
        collegeImage = findViewById(R.id.imageView);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("SetTextI18n")
    public void onClick(View view) {

        final ImageView[] coins = {coin, coin2, coin3, coin4};

        String onTap = "";


        collegeImage.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake));


        //image animation
        Random pick = new Random();
        final int randomCoin = pick.nextInt(4);

        coins[randomCoin].setVisibility(View.VISIBLE);
        coins[randomCoin].startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.coin_animation));
        coins[randomCoin].setVisibility(View.INVISIBLE);


        //end of image animation

        classes(onTap);


        rounds();


    }

}