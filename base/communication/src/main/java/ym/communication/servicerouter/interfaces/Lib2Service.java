package ym.communication.servicerouter.interfaces;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import io.reactivex.Notification;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
*
* @Author Mark.Yu
* @Date 2019/5/6
**/
public interface Lib2Service extends Service {
    Intent getLib2ActivityIntent(Context context);
    Disposable subscribeData(Consumer<Notification<String>> consumer);
    void getSomeData();
}
