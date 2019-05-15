package ym.lib1.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

import ym.lib1.Lib1Application;
import ym.lib1.R;
import ym.lib1.model.Lib1DataManger;

public class Lib1Activity extends AppCompatActivity {
    private TextView dataText;
    private TextView goToLib2Activity;

    @Inject
    Lib1DataManger lib1DataManger;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib1);
        dataText = findViewById(R.id.data_text);
        goToLib2Activity = findViewById(R.id.go_to_lib2);

        inject();

        lib1DataManger.subscribeData(response -> dataText.setText(response.getValue()));

        lib1DataManger.requestLib2Data();

        goToLib2Activity.setOnClickListener(v -> {
            //lib2会有自己启动入口，类似lib1中的，这里只是临时吧router get出来
            Lib1Application.getInstance().getmLib1Navigator().getAction().startNavigationAct(this);
        });
    }

    private void inject(){
        Lib1Application.getInstance().getLib1Component().inject(this);
    }
}
