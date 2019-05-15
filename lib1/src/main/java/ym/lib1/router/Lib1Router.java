package ym.lib1.router;

import android.content.Context;

import ym.communication.router.ILibRouter;


/**
 * CommunicationDemo
 *
 * @author Eamonn.Cui
 * Created by Eamonn.Cui on 2019/05/14.
 */
public interface Lib1Router extends ILibRouter {

    void startNavigationAct();

    void startNavigationAct(Context context);

    void setAfResult(String afResult);
}
