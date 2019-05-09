package ym.lib1;

import android.content.Context;

import ym.communication.injection.modules.ServiceModule;
import ym.lib1.injection.components.DaggerLib1Component;
import ym.lib1.injection.components.Lib1Component;

/**
*
* @Author Mark.Yu
* @Date 2019/5/7
**/
public class Lib1Application {
    private static Lib1Application INSTANCE;

    private Context applicationContext;
    private Lib1Component lib2Component;

    public static Lib1Application createInstance(Context applicationContext){
        INSTANCE = new Lib1Application(applicationContext);
        return INSTANCE;
    }

    public static Lib1Application getInstance(){
        return INSTANCE;
    }

    private Lib1Application(Context applicationContext){
        this.applicationContext = applicationContext;
    }

    public Lib1Component getLib1Component(){
        if(lib2Component == null){
            lib2Component = DaggerLib1Component.builder()
                    .serviceModule(new ServiceModule())
                    .build();
        }
        return lib2Component;
    }
}
