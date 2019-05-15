package ym.lib2.injection.components;

import javax.inject.Singleton;

import dagger.Component;
import ym.lib2.injection.scope.ScopeLib2;
import ym.lib2.services.Lib2ServiceImpl;

@Component
@Singleton
public interface Lib2Component {
    void inject(Lib2ServiceImpl lib2Service);
}
