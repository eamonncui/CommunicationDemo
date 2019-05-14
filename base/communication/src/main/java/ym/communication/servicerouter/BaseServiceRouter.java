package ym.communication.servicerouter;

import android.support.annotation.IntDef;
import android.util.SparseArray;

import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import ym.communication.servicerouter.interfaces.Service;
import ym.communication.servicerouter.interfaces.ServiceRouter;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
*
* @Author Mark.Yu
* @Date 2019/5/14
**/
public abstract class BaseServiceRouter implements ServiceRouter {
    public static final int TYPE_APP_SERVICE = 0;
    public static final int TYPE_LIB2_SERVICE = 1;

    private SparseArray<ServiceHolder> serviceMap = new SparseArray<>();

    @Override
    public void register(@ServiceType int serviceType, Class<? extends Service> clazz, Object... params){
        serviceMap.put(serviceType, new ServiceHolder(clazz, null, params));
    }

    @Override
    public void unregister(@ServiceType int serviceType){
        serviceMap.delete(serviceType);
    }

    protected  <T extends Service> T getService(@ServiceType int serviceType){
        ServiceHolder serviceHolder = serviceMap.get(serviceType);

        if(serviceHolder == null){
            return null;
        }

        if(serviceHolder.instance == null){
            serviceHolder.instance = createServiceInstance(serviceHolder.clazz, serviceHolder.params);
            serviceHolder.instance.inject();
        }
        return (T) serviceHolder.instance;
    }

    private Service createServiceInstance(Class<? extends Service> clazz, Object[] params){
        if(params == null || params.length == 0){
            try {
                return clazz.newInstance();
            } catch (IllegalAccessException e) {
            } catch (InstantiationException e) {
            }
        } else {
            try {
                Constructor constructor = clazz.getConstructor(getParameterTypes(params));
                constructor.newInstance(params);
            } catch (NoSuchMethodException e) {
            } catch (IllegalAccessException e) {
            } catch (InstantiationException e) {
            } catch (InvocationTargetException e) {
            }
        }

        return null;
    }

    private static Class[] getParameterTypes(Object[] params) {
        if (params == null || params.length == 0) {
            return null;
        }

        Class[] parameterTypes = new Class[params.length];
        for (int i = 0, size = params.length; i < size; i++) {
            parameterTypes[i] = params[i].getClass();
        }

        return parameterTypes;
    }

    private class ServiceHolder{
        Class<? extends Service> clazz;
        Service instance;
        Object[] params;

        public ServiceHolder(Class<? extends Service> clazz, Service instance, Object[] params) {
            this.clazz = clazz;
            this.instance = instance;
            this.params = params;
        }
    }

    @Retention(SOURCE)
    @IntDef({
            TYPE_LIB2_SERVICE
    })
    public @interface ServiceType {
    }

    @Override
    public void inject() {

    }
}
