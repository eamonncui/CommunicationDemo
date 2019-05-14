package ym.communication.injection.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ym.communication.servicerouter.ServiceRouterImpl;
import ym.communication.servicerouter.interfaces.ServiceRouter;

@Module
public class ServiceRouterModule {

    @Singleton
    @Provides
    public ServiceRouter provideServiceRouter(){
        return new ServiceRouterImpl();
    }
}
