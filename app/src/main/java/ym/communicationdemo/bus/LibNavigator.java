package ym.communicationdemo.bus;

import android.content.Context;

import java.lang.ref.WeakReference;
import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

import ym.communication.libaction.ILibAction;
import ym.communicationdemo.MainActivity;
import ym.lib1.Lib1Application;
import ym.lib1.router.Lib1Action;


/**
 * CommunicationDemo
 *
 * @author Eamonn.Cui
 * Created by Eamonn.Cui on 2019/05/14.
 */
@Singleton
public class LibNavigator {

    private static String LIB1_TAG_LOGIC1 = "LIB1_TAG_LOGIC1";
    private static String LIB1_TAG_LOGIC2 = "LIB1_TAG_LOGIC2";

    private WeakReference<Context> mContext;

    private HashMap<String, ILibAction> mLibActionHashMap = new HashMap();

    @Inject
    public LibNavigator() {
        initMap();
    }

    public void init(MainActivity activity) {
        mContext = new WeakReference<>(activity);
    }

    public void toModule1() {
        if (mContext.get() != null) {
            Lib1Application.getInstance().startLib1(mContext.get(), (Lib1Action) mLibActionHashMap.get(LIB1_TAG_LOGIC1));
        }
    }

    private void initMap() {
        mLibActionHashMap.put(LIB1_TAG_LOGIC1, new Lib1RouterImpl());
        mLibActionHashMap.put(LIB1_TAG_LOGIC2, new Lib1RouterImpl());
    }
}
