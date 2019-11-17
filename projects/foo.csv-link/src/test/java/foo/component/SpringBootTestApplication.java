/**
 * 
 */
package foo.sample.component;

import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * SpringBoot単体テスト用
 */
@SpringBootApplication
@ConfigurationProperties("application.properties")
public class SpringBootTestApplication {

    private static final Logger LOG = LoggerFactory.getLogger(SpringBootTestApplication.class);
    
    public static void main(String[] args) {
    	LOG.debug("Starting up Spring Boot for Test");
        SpringApplication.run(SpringBootTestApplication.class, args);
    }
    
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasenames("messages");
        source.setDefaultEncoding(StandardCharsets.UTF_8.name());
        return source;
    }   

}
