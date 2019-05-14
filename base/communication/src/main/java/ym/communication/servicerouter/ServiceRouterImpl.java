package ym.communication.servicerouter;

import android.content.Context;
import android.content.Intent;

import io.reactivex.Notification;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import ym.communication.servicerouter.BaseServiceRouter;
import ym.communication.servicerouter.interfaces.AppService;
import ym.communication.servicerouter.interfaces.Lib2Service;

public class ServiceRouterImpl extends BaseServiceRouter {

    public ServiceRouterImpl(){

    }

    @Override
    public Intent getMainActivityIntent(Context context) {
        return ((AppService)getService(TYPE_APP_SERVICE)).getMainActivityIntent(context);
    }

    @Override
    public Intent getLib2ActivityIntent(Context context) {
        return ((Lib2Service)getService(TYPE_LIB2_SERVICE)).getLib2ActivityIntent(context);
    }

    @Override
    public Disposable subscribeData(Consumer<Notification<String>> consumer) {
        return ((Lib2Service)getService(TYPE_LIB2_SERVICE)).subscribeData(consumer);
    }

    @Override
    public void getSomeData() {
        ((Lib2Service)getService(TYPE_LIB2_SERVICE)).getSomeData();
    }

}
