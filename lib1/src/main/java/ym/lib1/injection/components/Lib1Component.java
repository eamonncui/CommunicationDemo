package ym.lib1.injection.components;

import javax.inject.Singleton;

import dagger.Component;
import ym.communication.injection.modules.ServiceRouterModule;
import ym.lib1.views.Lib1Activity;

@Singleton
@Component(modules = {ServiceRouterModule.class})
public interface Lib1Component {
    void inject(Lib1Activity lib1Activity);
}
