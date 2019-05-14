package ym.communicationdemo;

import android.app.Application;

import javax.inject.Inject;

import ym.communication.servicerouter.BaseServiceRouter;
import ym.communication.servicerouter.interfaces.ServiceRouter;
import ym.communicationdemo.injection.components.DaggerMainComponent;
import ym.communicationdemo.injection.components.MainComponent;
import ym.lib1.Lib1Application;
import ym.lib2.Lib2Application;

/**
*
* @Author Mark.Yu
* @Date 2019/5/7
**/

public class App extends Application {
    private static App INSTANCE;

    private MainComponent mMainComponent;

    @Inject
    ServiceRouter serviceRouter;

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);

        getMainComponent().inject(this);
        serviceRouter.register(BaseServiceRouter.TYPE_APP_SERVICE, AppServiceImpl.class);

        Lib1Application.createInstance(this);
        Lib2Application.createInstance(this);
    }

    public MainComponent getMainComponent(){
        if (mMainComponent == null) {
            mMainComponent = DaggerMainComponent.builder()
                    .build();
        }
        return mMainComponent;
    }

    public static App getInstance() {
        return INSTANCE;
    }

    private static void setInstance(App instance) {
        INSTANCE = instance;
    }
}
