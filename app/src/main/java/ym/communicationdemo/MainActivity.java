package ym.communicationdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import javax.inject.Inject;

import ym.communicationdemo.bus.LibNavigator;
import ym.communicationdemo.model.AppDataManager;

public class MainActivity extends AppCompatActivity {
    @Inject
    AppDataManager appDataManager;

    @Inject
    LibNavigator mLibNavigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        App.getInstance().getMainComponent().inject(this);

        mLibNavigator.init(this);

        findViewById(R.id.go_to_lib1).setOnClickListener(v -> mLibNavigator.toModule1());
    }
}
