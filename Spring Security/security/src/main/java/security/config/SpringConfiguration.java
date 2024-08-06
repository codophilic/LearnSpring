package security.config;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan( basePackages = "security")
@EnableWebMvc
public class SpringConfiguration implements WebMvcConfigurer{

    // Creating InternalResourceViewResolver bean 
    @Bean
    public ViewResolver getInternalResourceViewResolver(){ 
        InternalResourceViewResolver viewResolver 
            = new InternalResourceViewResolver(); 
        
        // setting prefix and suffix to the path & extension 
        viewResolver.setPrefix("/WEB-INF/pages/"); 
        viewResolver.setSuffix(".jsp"); 
        return viewResolver; 
    }
}
