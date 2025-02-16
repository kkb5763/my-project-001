package io.github.devkb.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.github.devkb.config.interceptor.BearerAuthInterceptor;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer{
    private final io.github.devkb.config.interceptor.BearerAuthInterceptor bearerAuthInterceptor;

    public WebMvcConfig(BearerAuthInterceptor bearerAuthInterceptor) {
        this.bearerAuthInterceptor = bearerAuthInterceptor;
    }

    public void addInterceptors(InterceptorRegistry registry){
        System.out.println(">>> 인터셉터 등록");
        registry.addInterceptor(bearerAuthInterceptor).addPathPatterns("/api/**");
    }

  @Override
  public void configureViewResolvers(ViewResolverRegistry registry) {
      MustacheViewResolver resolver = new MustacheViewResolver();

      resolver.setCharset("UTF-8");
      resolver.setContentType("text/html;charset=UTF-8");
      resolver.setPrefix("classpath:/templates/");
      resolver.setSuffix(".html");

      registry.viewResolver(resolver);
  }
}
