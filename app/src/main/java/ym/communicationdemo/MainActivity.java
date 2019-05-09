package ym.communicationdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import ym.communicationdemo.model.AppDataManager;
import ym.lib1.views.Lib1Activity;

public class MainActivity extends AppCompatActivity {
    @Inject
    AppDataManager appDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getInstance().getMainComponent().inject(this);

        findViewById(R.id.go_to_lib1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Lib1Activity.class));
            }
        });
    }
}
