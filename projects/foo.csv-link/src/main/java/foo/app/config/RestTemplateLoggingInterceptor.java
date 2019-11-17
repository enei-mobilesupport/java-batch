package foo.sample.app.config;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import foo.sample.component.LogComponent;

/**
 * API呼出しのリクエスト・レスポンスのログ出力.
 * 
 * @deprecated 開発や障害対応時のログ出力用の為、非推奨. HttpStatus 400や 500で、IOExceptionとなる.
 */
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {

    private LogComponent logger;

    public RestTemplateLoggingInterceptor(LogComponent logger) {
        this.logger = logger;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
            throws IOException {
        // @formatter:off
        logger.log("SIM.INFO.0101", // "API Request: URI={}, Headers={}, Body={}"
                request.getURI(),
                request.getHeaders(),
                new String(body));
        // @formatter:on
        ClientHttpResponse response = new CustomClientHttpResponse(execution.execute(request, body));

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), UTF_8.name()));
        String line = bufferedReader.readLine();

        StringBuilder inputStringBuilder = new StringBuilder();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        // @formatter:off
        logger.log("SIM.INFO.0102", // "API Response: StatusCode={} {}, Headers={}, Body={}"
                response.getStatusCode(),
                response.getStatusText(),
                response.getHeaders(),
                inputStringBuilder.toString()
        );
        // @formatter:on
        return response;
    }

}
