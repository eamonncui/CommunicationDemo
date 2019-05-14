package ym.communication.servicerouter.interfaces;

import ym.communication.servicerouter.ServiceRouterImpl;

public interface ServiceHolder {
    void register(@ServiceRouterImpl.ServiceType int serviceType, Class<? extends Service> clazz, Object... params);
    void unregister(@ServiceRouterImpl.ServiceType int serviceType);
}
