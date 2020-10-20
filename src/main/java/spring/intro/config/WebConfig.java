package spring.intro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"spring.intro.controllers"})
public class WebConfig {

    @Bean
    public InternalResourceViewResolver resolver() {
        InternalResourceViewResolver resolve = new InternalResourceViewResolver();
        resolve.setViewClass(JstlView.class);
        resolve.setPrefix("/WEB-INF/views/");
        resolve.setSuffix(".jsp");
        return resolve;
    }
}
