package com.blogspot.passovich.bearings;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button1,button2,button3;
    private HelperMethodClass helperMethodClass = new HelperMethodClass();
    private String nameDB="Rings.db";
    private String selection = "_id=1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Animation anim = AnimationUtils.loadAnimation(this,R.anim.alpha);
        button1=(Button) findViewById(R.id.button1); button1.setOnClickListener(this);
        button1.startAnimation(anim);
        button2=(Button) findViewById(R.id.button2); button2.setOnClickListener(this);
        button2.startAnimation(anim);
        button3=(Button) findViewById(R.id.button3); button3.setOnClickListener(this);
        button3.startAnimation(anim);
        helperMethodClass.onCreateDBAPK(this, nameDB);
    }
    @Override
    public void onClick(View view){
        Intent intent = null;
        Animation anim =AnimationUtils.loadAnimation(this, R.anim.translate);
        switch(view.getId())
        {
            case R.id.button1:
                button1.startAnimation(anim);
                selection = "_id=1";                                  //Выбор раздела с кольцами
                intent = new Intent(this,RingsViewActivity.class);
                intent.putExtra("selection",selection);
                startActivity(intent);
                break;
            case R.id.button2:
                button2.startAnimation(anim);
                selection = "_id=2";                                  //Выбор раздела с кольцами
                intent = new Intent(this,RingsViewActivity.class);
                intent.putExtra("selection",selection);
                startActivity(intent);
                break;
            case R.id.button3:
                button3.startAnimation(anim);
                finish();
                break;
        }
    }
}
