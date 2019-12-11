package com.mxz.common.config;

        import org.springframework.context.annotation.Configuration;
        import org.springframework.core.Ordered;
        import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
        import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Description
 * @Date 2019/10/29 15:03
 * @Author mxz
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
//    @Override
//    public void addViewControllers(ViewControllerRegistry registry) {
//        registry.addViewController( "/" ).setViewName( "forward:/index.html" );
//        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
    }
}
