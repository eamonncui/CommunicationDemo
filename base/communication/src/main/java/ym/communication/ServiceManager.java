package ym.communication;

import android.support.annotation.IntDef;
import android.util.SparseArray;

import java.lang.annotation.Retention;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import ym.communication.services.IService;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class ServiceManager {

    private static final class InstanceHolder {
        /**
         * This will be lazily initialised and thread safe.
         */
        static ServiceManager self = new ServiceManager();
    }

    public static ServiceManager getInstance() {
        return InstanceHolder.self;
    }

    public static final int TYPE_LIB2_SERVICE = 1;

    private SparseArray<ServiceHolder> serviceMap = new SparseArray<>();

    public void register(@ServiceType int serviceType, Class<? extends IService> clazz, Object... params){
        serviceMap.put(serviceType, new ServiceHolder(clazz, null, params));
    }

    public void unregister(@ServiceType int serviceType){
        serviceMap.delete(serviceType);
    }

    public <T extends IService> T getService(@ServiceType int serviceType){
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

    private IService createServiceInstance(Class<? extends IService> clazz, Object[] params){
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
        Class<? extends IService> clazz;
        IService instance;
        Object[] params;

        public ServiceHolder(Class<? extends IService> clazz, IService instance, Object[] params) {
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
}
