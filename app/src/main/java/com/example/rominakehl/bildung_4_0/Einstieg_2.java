package com.example.rominakehl.bildung_4_0;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class Einstieg_2 extends AppCompatActivity {

    Button buttonPrev, buttonNext;
    ViewSwitcher viewSwitcher;
    Animation slide_in_left, slide_out_right;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einstieg_2);

       buttonPrev = (Button) findViewById(R.id.btnPrev2);
        buttonNext = (Button) findViewById(R.id.btnNext2);
        viewSwitcher = (ViewSwitcher) findViewById(R.id.switcher2);

        slide_in_left = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left);
        slide_out_right = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right);

        viewSwitcher.setInAnimation(slide_in_left);
        viewSwitcher.setOutAnimation(slide_out_right);
        TextView tView1 = (TextView)findViewById(R.id.txtKompetenzen);
        tView1.setText(getString(R.string.textKompetenzen));
        TextView tView2 = (TextView)findViewById(R.id.txtUebersicht);
        tView2.setText(getString(R.string.textUebersicht));

        buttonPrev.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                viewSwitcher.showPrevious();
            }
        });


        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                viewSwitcher.showNext();
            }
        });

    }
}
