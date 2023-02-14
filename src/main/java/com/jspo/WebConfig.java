package com.jspo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;

@Configuration
public class WebConfig  implements WebMvcConfigurer {


    @Value("${file.dir}")
    private String uploadImagesPath;

    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        Calendar cal = Calendar.getInstance();
        String yearPath = File.separator+ cal.get(Calendar.YEAR);
        String monthPath = yearPath + File.separator+ new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
        String datePath = monthPath + File.separator+ new DecimalFormat("00").format(cal.get(Calendar.DATE));
        // 로케이션 2개 설정가능
        // .addResourceHandler - localhost:8080/static/1.PNG 1그림파일이 나옴
        // .addResourceLocations - 사진이 있는 경로
        System.out.println("파일 위치 = " + "file:///" + uploadImagesPath + "imgUpload" + datePath + "/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("file:///" + uploadImagesPath + "imgUpload" + datePath + "/")
                .setCachePeriod(60 * 60 * 24 * 365)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
