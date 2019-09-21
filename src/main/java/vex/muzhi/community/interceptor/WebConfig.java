package vex.muzhi.community.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Author: lichuang
 * Date: Create in 15:05 2019/9/14
 * Description:
 */
@Configuration
@EnableWebMvc
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    @Value("${file.image.path}")
    private String imagePath;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 拦截请求
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/upload/images/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 过滤静态资源
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
        // 请求图片路径，磁盘绝对路径映射到请求路径
        registry.addResourceHandler("/upload/images/**")
                .addResourceLocations("file:" + uploadFolder + imagePath);
    }
}
