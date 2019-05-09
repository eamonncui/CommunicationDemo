package ym.lib1.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

import io.reactivex.Notification;
import io.reactivex.functions.Consumer;
import ym.communication.services.Lib2Service;
import ym.lib1.Lib1Application;
import ym.lib1.R;

public class Lib1Activity extends AppCompatActivity {
    private TextView dataText;
    private TextView goToLib2Activity;

    @Inject
    Lib2Service lib2Service;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib1);
        dataText = findViewById(R.id.data_text);
        goToLib2Activity = findViewById(R.id.go_to_lib2);

        inject();

        lib2Service.subscribeData(new Consumer<Notification<String>>() {
            @Override
            public void accept(Notification<String> response) throws Exception {
                dataText.setText(response.getValue());
            }
        });
        lib2Service.getSomeData();

        goToLib2Activity.setOnClickListener(v -> {
            lib2Service.goToLib2Activity(this);
        });
    }

    private void inject(){
        Lib1Application.getInstance().getLib1Component().inject(this);
    }
}
