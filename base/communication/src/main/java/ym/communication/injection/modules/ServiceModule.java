package ym.communication.injection.modules;

import dagger.Module;
import dagger.Provides;
import ym.communication.ServiceManager;
import ym.communication.services.Lib2Service;

@Module
public class ServiceModule {

    @Provides
    public Lib2Service provideLib2Service(){
        return ServiceManager.getInstance().getService(ServiceManager.TYPE_LIB2_SERVICE);
    }
}
