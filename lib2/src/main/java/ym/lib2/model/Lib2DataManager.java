package ym.lib2.model;

import android.util.Log;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

/**
*
* @Author Mark.Yu
* @Date 2019/5/7
**/

@Singleton
public class Lib2DataManager {
    private PublishSubject<Notification<String>> mDataSubject = PublishSubject.create();

    @Inject
    public Lib2DataManager(){
        Log.d("Lib2DataManager", "Lib2DataManager init");
    }

    public Disposable subscribeData(Consumer<Notification<String>> consumer) {
        return subscribe(mDataSubject, consumer);
    }

    public void getSomeData(){
        publish(mDataSubject, Observable.just("Data from lib2"));
    }

    private  <T> Disposable subscribe(PublishSubject<T> ps, Consumer<T> c) {
        return ps.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(c);
    }

    private  <T> void publish(PublishSubject<Notification<T>> ps, Observable<T> o) {
        o.compose(applySchedulers()).subscribe(
                t1 -> ps.onNext(Notification.createOnNext(t1)),
                throwable -> ps.onNext(Notification.createOnError(throwable)));
    }

    private <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
