package sample.app.config;

import java.io.IOException;
import java.net.URI;
import java.util.Collections;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.StringUtils;

import foo.sample.component.LogComponent;

/**
 * APIコールの RequestFactory.
 */
public class CustomClientHttpRequestFactory extends SimpleClientHttpRequestFactory {

    private LogComponent logger;

    private String token;

    /**
     * 標準の初期化.
     * 
     * @param logger ロガー
     */
    public CustomClientHttpRequestFactory(LogComponent logger) {
        this.logger = logger;
    }

    /**
     * 認証を含めた初期化.
     * 
     * @param logger ロガー
     * @param token  Authorization: Bearer トークン
     */
    public CustomClientHttpRequestFactory(LogComponent logger, String token) {
        this.logger = logger;
        this.token = token;
    }

    @Override
    public ClientHttpRequest createRequest(URI uri, HttpMethod httpMethod) throws IOException {

        ClientHttpRequest request = super.createRequest(uri, httpMethod);

        HttpHeaders headers = request.getHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);

        if (StringUtils.hasText(token)) {
            headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        }
        logger.log(request);
        return request;
    }

}
