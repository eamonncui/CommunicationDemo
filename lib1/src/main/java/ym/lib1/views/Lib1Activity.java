package ym.lib1.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import javax.inject.Inject;

import io.reactivex.Notification;
import io.reactivex.functions.Consumer;
import ym.communication.servicerouter.interfaces.Lib2Service;
import ym.communication.servicerouter.interfaces.ServiceRouter;
import ym.lib1.Lib1Application;
import ym.lib1.R;

public class Lib1Activity extends AppCompatActivity {
    private TextView dataText;
    private TextView goToLib2Activity;
    private TextView goToMainActivity;

    @Inject
    ServiceRouter serviceRouter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lib1);
        dataText = findViewById(R.id.data_text);
        goToLib2Activity = findViewById(R.id.go_to_lib2);

        inject();

        serviceRouter.subscribeData(new Consumer<Notification<String>>() {
            @Override
            public void accept(Notification<String> response) throws Exception {
                dataText.setText(response.getValue());
            }
        });
        serviceRouter.getSomeData();

        goToLib2Activity.setOnClickListener(v -> {
            startActivity(serviceRouter.getLib2ActivityIntent(this));
        });

        goToMainActivity.setOnClickListener(v -> {
            Intent intent = serviceRouter.getMainActivityIntent(this);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        });
    }

    private void inject(){
        Lib1Application.getInstance().getLib1Component().inject(this);
    }
}
