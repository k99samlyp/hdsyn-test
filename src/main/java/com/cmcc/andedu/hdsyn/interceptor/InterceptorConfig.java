package com.cmcc.andedu.hdsyn.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.MappedInterceptor;

/**
 * Created by LiYangpan on 2018/8/24  3:26 PM.
 * with INTELLIJ IDEA on rmbp osx 10.11
 * 描述:
 */
@Component
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    /**
     * Add Spring MVC lifecycle interceptors for pre- and post-processing of
     * controller method invocations. Interceptors can be registered to apply
     * to all requests or be limited to a subset of URL patterns.
     * <p><strong>Note</strong> that interceptors registered here only apply to
     * controllers and not to resource handler requests. To intercept requests for
     * static resources either declare a
     * {@link MappedInterceptor MappedInterceptor}
     * bean or switch to advanced configuration mode by extending
     * {@link WebMvcConfigurationSupport
     * WebMvcConfigurationSupport} and then override {@code resourceHandlerMapping}.
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CommonInterceptor());
        super.addInterceptors(registry);
    }
}
