package ym.communicationdemo;

import android.app.Application;

import ym.communication.servicerouter.BaseServiceRouter;
import ym.communication.servicerouter.ServiceRouterImpl;
import ym.lib1.Lib1Application;
import ym.lib2.Lib2Application;

/**
*
* @Author Mark.Yu
* @Date 2019/5/7
**/

public class App extends Application {
    private static App INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        setInstance(this);

        ServiceRouterImpl.getInstance().register(BaseServiceRouter.TYPE_APP_SERVICE, AppServiceImpl.class);

        Lib1Application.createInstance(this);
        Lib2Application.createInstance(this);
    }

    public static App getInstance() {
        return INSTANCE;
    }

    private static void setInstance(App instance) {
        INSTANCE = instance;
    }
}
