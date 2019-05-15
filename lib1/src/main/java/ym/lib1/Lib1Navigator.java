package ym.lib1;

import android.content.Context;
import android.content.Intent;

import ym.lib1.router.Lib1Action;
import ym.lib1.views.Lib1Activity;

public class Lib1Navigator {

    private Lib1Action mLib1Action;

    void setRouterRule(Lib1Action lib1Action) {
        mLib1Action = lib1Action;
    }

    void startInitActvity(Context context) {
        context.startActivity(new Intent(context, Lib1Activity.class));
    }

    public Lib1Action getAction() {
        return mLib1Action;
    }
}
