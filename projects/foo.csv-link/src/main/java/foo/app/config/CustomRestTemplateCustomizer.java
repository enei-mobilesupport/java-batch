package foo.sample.app.config;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.nio.charset.Charset;

import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplateのデフォルトを ISO-8859-1から、UTF-8に変更.
 */
public class CustomRestTemplateCustomizer implements RestTemplateCustomizer {

    @Override
    public void customize(RestTemplate restTemplate) {

        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName(UTF_8.name())));
    }

}
