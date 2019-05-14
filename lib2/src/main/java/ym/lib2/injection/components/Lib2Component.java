package ym.lib2.injection.components;

import javax.inject.Singleton;

import dagger.Component;
import ym.communication.injection.modules.ServiceRouterModule;
import ym.lib2.Lib2Application;
import ym.lib2.injection.scope.ScopeLib2;
import ym.lib2.services.Lib2ServiceImpl;

@Singleton
@Component(modules = {ServiceRouterModule.class})
public interface Lib2Component {
    void inject(Lib2Application lib2Application);
    void inject(Lib2ServiceImpl lib2Service);
}
