package br.dgs.hanckathon.ia_market.configs;

import br.dgs.hanckathon.ia_market.configs.properties.SandboxProperties;
import br.dgs.hanckathon.ia_market.externals.anymarket.interceptor.GumgaAuthFilter;
import br.dgs.hanckathon.ia_market.externals.sandbox.SandboxClient;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class SandboxClientConfig {

    private final SandboxProperties properties;

    @Bean
    public HttpServiceProxyFactory sandboxHttpServiceProxyFactory() {
        ClientHttpRequestInterceptor interceptor = new GumgaAuthFilter(properties.getToken());
        return RestClientFactory.createFactory(properties.getBaseUrl(), interceptor);
    }

    @Bean
    public SandboxClient sandboxClient(HttpServiceProxyFactory sandboxHttpServiceProxyFactory) {
        return sandboxHttpServiceProxyFactory.createClient(SandboxClient.class);
    }
}