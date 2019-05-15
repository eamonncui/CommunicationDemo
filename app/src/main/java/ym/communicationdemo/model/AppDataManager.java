package ym.communicationdemo.model;

import javax.inject.Inject;
import javax.inject.Singleton;

import ym.lib2.model.Lib2DataManager;

@Singleton
public class AppDataManager {

    @Inject
    public AppDataManager(Lib2DataManager lib2DataManager){

    }
}
