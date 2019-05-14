package ym.lib2;

import android.content.Context;

import javax.inject.Inject;

import ym.communication.servicerouter.ServiceRouterImpl;
import ym.communication.servicerouter.interfaces.ServiceRouter;
import ym.lib2.injection.components.DaggerLib2Component;
import ym.lib2.injection.components.Lib2Component;
import ym.lib2.services.Lib2ServiceImpl;

/**
*
* @Author Mark.Yu
* @Date 2019/5/7
**/
public class Lib2Application {
    private static Lib2Application INSTANCE;

    private Context applicationContext;
    private Lib2Component lib2Component;

    @Inject
    ServiceRouter serviceRouter;

    public static Lib2Application createInstance(Context applicationContext){
        INSTANCE = new Lib2Application(applicationContext);
        return INSTANCE;
    }

    public static Lib2Application getInstance(){
        return INSTANCE;
    }

    private Lib2Application(Context applicationContext){
        this.applicationContext = applicationContext;

        getLib2Component().inject(this);
        serviceRouter.register(ServiceRouterImpl.TYPE_LIB2_SERVICE, Lib2ServiceImpl.class);
    }

    public Lib2Component getLib2Component(){
        if(lib2Component == null){
            lib2Component = DaggerLib2Component.builder()
                    .build();
        }
        return lib2Component;
    }
}
