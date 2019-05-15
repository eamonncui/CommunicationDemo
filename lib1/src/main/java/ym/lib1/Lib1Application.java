package ym.lib1;

import android.content.Context;

import ym.communication.injection.modules.ServiceModule;
import ym.lib1.injection.components.DaggerLib1Component;
import ym.lib1.injection.components.Lib1Component;
import ym.lib1.router.Lib1Action;

/**
 * @Author Mark.Yu
 * @Date 2019/5/7
 **/
public class Lib1Application {
    private static Lib1Application INSTANCE;

    private Context applicationContext;
    private Lib1Component lib1Component;
    private Lib1Navigator mLib1Navigator;

    public static Lib1Application createInstance(Context applicationContext) {
        INSTANCE = new Lib1Application(applicationContext);
        return INSTANCE;
    }

    public static Lib1Application getInstance() {
        return INSTANCE;
    }

    public void startLib1(Context context, Lib1Action lib1Router) {
       startLib1(context,-1,-1,lib1Router);
    }

    public void startLib1(Context context, int startCode, int resultCode, Lib1Action lib1Router) {
        // TODO: 2019-05-15 正式肯定不能这么low的注入，反射生成啥的，但是注意类的包名
        mLib1Navigator.setRouterRule(lib1Router);
        mLib1Navigator.startInitActvity(context);
    }

    private Lib1Application(Context applicationContext) {
        this.applicationContext = applicationContext;
        mLib1Navigator = new Lib1Navigator();
    }

    public Lib1Component getLib1Component() {
        if (lib1Component == null) {
            lib1Component = DaggerLib1Component.builder()
                    .serviceModule(new ServiceModule())
                    .build();
        }
        return lib1Component;
    }

    public Lib1Navigator getmLib1Navigator() {
        return mLib1Navigator;
    }
}
