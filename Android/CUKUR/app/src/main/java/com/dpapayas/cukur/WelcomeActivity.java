package com.dpapayas.cukur;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by dpapayas on 9/16/15.
 */
public class WelcomeActivity extends Activity implements View.OnClickListener {

    Button btnTry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_welcome);

        btnTry = (Button)findViewById(R.id.btnTry);

        btnTry.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnTry:
                Intent intent = new Intent(WelcomeActivity.this, FindBarberActivity.class);
                startActivity(intent);
                break;
        }

    }
}
