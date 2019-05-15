package ym.communicationdemo.bus;

import android.content.Context;
import android.content.Intent;

import ym.lib1.router.Lib1Action;
import ym.lib2.view.Lib2Activity;

/**
 * CommunicationDemo
 *
 * @author Eamonn.Cui
 * Created by Eamonn.Cui on 2019/05/14.
 */

public class Lib1RouterImpl implements Lib1Action {

    @Override
    public void startNavigationAct() {

    }

    @Override
    public void startNavigationAct(Context context) {
        context.startActivity(new Intent(context, Lib2Activity.class));
    }

    @Override
    public void setAfResult(String afResult) {

    }
}
