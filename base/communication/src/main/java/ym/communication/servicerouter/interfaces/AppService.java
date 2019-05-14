package ym.communication.servicerouter.interfaces;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public interface AppService extends Service {
    Intent getMainActivityIntent(Context context);
}
