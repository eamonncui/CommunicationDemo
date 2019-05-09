package ym.lib2.services;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;

import io.reactivex.Notification;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import ym.communication.services.Lib2Service;
import ym.lib2.Lib2Application;
import ym.lib2.model.Lib2DataManager;
import ym.lib2.view.Lib2Activity;

/**
*
* @Author Mark.Yu
* @Date 2019/5/6
**/
public class Lib2ServiceImpl implements Lib2Service {
    @Inject
    Lib2DataManager lib2DataManager;

    public Lib2ServiceImpl(){
        inject();
    }

    @Override
    public void goToLib2Activity(Context context) {
        context.startActivity(new Intent(context, Lib2Activity.class));
    }

    @Override
    public Disposable subscribeData(Consumer<Notification<String>> consumer) {
        return lib2DataManager.subscribeData(consumer);
    }

    @Override
    public void getSomeData() {
        lib2DataManager.getSomeData();
    }

    @Override
    public void inject() {
        Lib2Application.getInstance().getLib2Component().inject(this);
    }
}
