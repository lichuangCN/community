package vex.muzhi.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("vex.muzhi.community.mapper")
@EnableTransactionManagement
public class CommunityApplication extends SpringBootServletInitializer {

    @Value("${file.uploadFolder}")
    private String uploadFolder;

    public static void main(String[] args) {
        SpringApplication.run(CommunityApplication.class, args);
    }

    /**
     * 用于文件上传
     *
     * @return
     */
    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(uploadFolder);
        return factory.createMultipartConfig();
    }
}
