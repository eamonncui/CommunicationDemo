package ym.communicationdemo.injection.components;

import javax.inject.Singleton;

import dagger.Component;
import ym.communicationdemo.MainActivity;

/**
*
* @Author Mark.Yu
* @Date 2019/5/6
**/

@Singleton
@Component()
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
