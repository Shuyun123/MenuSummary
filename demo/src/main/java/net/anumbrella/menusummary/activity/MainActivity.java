package net.anumbrella.menusummary.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import net.anumbrella.menusummary.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.classifyMenu,R.id.SwipeMenu})
    public void click(View view){
        switch (view.getId()){
            case R.id.classifyMenu:
                startActivity(ClassifyMenuActivity.class);
                break;
            case R.id.SwipeMenu:
                startActivity(SwipeMenuActivity.class);
                break;
        }
    }


    public void startActivity(Class<?> name){
        Intent intent = new Intent();
        intent.setClass(this,name);
        startActivity(intent);
    }



}
