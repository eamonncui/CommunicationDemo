package ym.communicationdemo;

import android.content.Context;
import android.content.Intent;

import ym.communication.servicerouter.interfaces.AppService;

public class AppServiceImpl implements AppService {
    @Override
    public Intent getMainActivityIntent(Context context) {
        return new Intent(context, AppServiceImpl.class);
    }

    @Override
    public void inject() {

    }
}
