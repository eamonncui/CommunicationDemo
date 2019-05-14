package ym.lib1;

import android.content.Context;

/**
*
* @Author Mark.Yu
* @Date 2019/5/7
**/
public class Lib1Application {
    private static Lib1Application INSTANCE;

    private Context applicationContext;

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
}
