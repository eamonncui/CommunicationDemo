package ym.communication.services;

import android.content.Context;

import io.reactivex.Notification;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
*
* @Author Mark.Yu
* @Date 2019/5/6
**/
public interface Lib2Service extends IService{
    void goToLib2Activity(Context context);
    Disposable subscribeData(Consumer<Notification<String>> consumer);
    void getSomeData();
}
