package pbo.proyek.kel3.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Mengambil jalur folder absolut ke src/main/resources/static/uploads/
        String path = "file:" + System.getProperty("user.dir") + "/src/main/resources/static/uploads/";
        
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations(path);
    }
}