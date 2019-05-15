package ym.lib1.model;

import com.blankj.rxbus.RxBus;

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
import ym.communication.services.Lib2Service;
import ym.lib1.Lib1Application;

// TODO: 2019-05-15 datamanager和自己的模块之间沟通 ,如果需要其他模块的功能，不同的datamanager之间相互沟通。

@Singleton
public class Lib1DataManger {

    @Inject
    Lib2Service mLib2Service;

    @Inject
    public Lib1DataManger() {
        RxBus.getDefault().subscribe(this, Lib2Service.Lib2ServiceTag.getSomeData.name(), new RxBus.Callback<String>() {
            @Override
            public void onEvent(String s) {
                publish(mDataSubject, Observable.just(s));
            }
        });
    }

    private PublishSubject<Notification<String>> mDataSubject = PublishSubject.create();


    public Disposable subscribeData(Consumer<Notification<String>> consumer) {
        return subscribe(mDataSubject, consumer);
    }

    private <T> Disposable subscribe(PublishSubject<T> ps, Consumer<T> c) {
        return ps.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(c);
    }

    private <T> void publish(PublishSubject<Notification<T>> ps, Observable<T> o) {
        o.compose(applySchedulers()).subscribe(
                t1 -> ps.onNext(Notification.createOnNext(t1)),
                throwable -> ps.onNext(Notification.createOnError(throwable)));
    }

    private <T> ObservableTransformer<T, T> applySchedulers() {
        return observable -> observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }

    public void requestLib2Data() {
        mLib2Service.getSomeData();
    }
}
