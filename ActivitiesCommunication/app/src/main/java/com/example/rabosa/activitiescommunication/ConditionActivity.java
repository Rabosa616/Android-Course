package com.example.rabosa.activitiescommunication;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Rabosa on 31/05/2015.
 */
public class ConditionActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.condition);
        Bundle extras = getIntent().getExtras();
        String userName = extras.getString("User");

        TextView textView = (TextView)findViewById(R.id.welcome);
        Resources res = getResources();
        String text = String.format(res.getString(R.string.accept_conditions), extras.getString("User"));
        textView.setText(text);
    }

    public void onClickAccept(View view)
    {

    }

    public void onClickDecline(View view)
    {

    }

}
