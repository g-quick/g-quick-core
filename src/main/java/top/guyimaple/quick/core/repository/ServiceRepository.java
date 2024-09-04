package top.guyimaple.quick.core.repository;

import top.guyimaple.quick.common.QuickService;

import java.net.URL;
import java.net.URLClassLoader;
import java.util.ServiceLoader;

/**
 * 服务仓库
 * @author guyi
 * @date 2024/9/4
 */
public interface ServiceRepository {

    URL getUrl(String serviceName);

    default QuickService getService(String serviceName) {
        URLClassLoader loader = new URLClassLoader(new URL[]{this.getUrl(serviceName)}, this.getClass().getClassLoader());
        ServiceLoader<QuickService> services = ServiceLoader.load(QuickService.class, loader);
        return services.stream()
                .findFirst()
                .map(ServiceLoader.Provider::get)
                .orElseThrow(() -> new RuntimeException("无效的服务包"));
    }

}
