package foo.sample.app.config;

import static foo.sample.constant.SP.UNKNOWN;

import java.util.function.Supplier;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;

import foo.sample.component.LogComponent;
import foo.sample.constant.SP;

/**
 * Builderの管理.
 */
public class RestTemplateBuilderSupplier implements InitializingBean, Supplier<RestTemplateBuilder> {

    @Value("${api.platform.timeout.connect:1000}")
    private int connectTimeout;
    @Value("${api.platform.timeout.read:3000}")
    private int readTimeout;

    @Value("${api.dev.timeout.connect:1000}")
    private int devConnectTimeout;
    @Value("${api.dev.timeout.read:3000}")
    private int devReadTimeout;
    @Value("${api.dev.token}")
    private String devToken;

    @Autowired
    private LogComponent logger;

    private SP sp = UNKNOWN;

    // ケーブルID連携基盤（ケーブルIDプラットフォーム）用
    private RestTemplateBuilder cableRestTemplateBuilder;

    // dev用. Authorization: Bearer ヘッダは自動設定.
    private RestTemplateBuilder devRestTemplateBuilder;

    @Override
    public void afterPropertiesSet() throws Exception {
        // @formatter:off
        cableRestTemplateBuilder = new RestTemplateBuilder()
                .setConnectTimeout(connectTimeout)
                .setReadTimeout(readTimeout)
                .errorHandler(new CustomErrorHandler())
                .customizers(new CustomRestTemplateCustomizer())
                .requestFactory(() -> new CustomClientHttpRequestFactory(logger));

        devRestTemplateBuilder = new RestTemplateBuilder()
                .setConnectTimeout(devConnectTimeout)
                .setReadTimeout(devReadTimeout)
                .errorHandler(new CustomErrorHandler())
                .customizers(new CustomRestTemplateCustomizer())
                .requestFactory(() -> new CustomClientHttpRequestFactory(logger, devToken));
        // @formatter:on
    }

    /**
     * getしたいRestTemplateBuilderのサービスプロバイダを設定.<br>
     * デフォルトは、ケーブルID連携基盤用を返す.
     * 
     * @param sp サービスプロバイダ
     * @return this.Supplier
     */
    public RestTemplateBuilderSupplier Initialize(SP sp) {

        this.sp = ObjectUtils.defaultIfNull(sp, UNKNOWN);
        return this;
    }

    @Override
    public RestTemplateBuilder get() {
        switch (sp) {
        case dev:
            return devRestTemplateBuilder;
        default:
            return cableRestTemplateBuilder;
        }
    }

}
