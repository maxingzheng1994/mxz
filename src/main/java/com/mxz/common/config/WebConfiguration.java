package com.mxz.common.config;

        import org.springframework.context.annotation.Configuration;
        import org.springframework.core.Ordered;
        import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
        import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
        import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description
 * @Date 2019/10/29 15:03
 * @Author mxz
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {
  /*  @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController( "/" ).setViewName( "forward:/html/index.html" );
        registry.addViewController( "/index" ).setViewName( "forward:/html/index.html" );
        registry.setOrder( Ordered.HIGHEST_PRECEDENCE );
        WebMvcConfigurer.super.addViewControllers(registry);
    }
*/
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("/static/");
//        registry.addResourceHandler("/templates/**").addResourceLocations("/templates/");
    }
}

