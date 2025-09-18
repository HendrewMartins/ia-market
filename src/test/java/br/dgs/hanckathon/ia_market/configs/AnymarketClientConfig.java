package br.dgs.hanckathon.ia_market.configs;


import br.dgs.hanckathon.ia_market.configs.properties.AnyMarketProperties;
import br.dgs.hanckathon.ia_market.externals.anymarket.client.AnymarketClient;
import br.dgs.hanckathon.ia_market.externals.anymarket.interceptor.AnymarketAuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
@RequiredArgsConstructor
public class AnymarketClientConfig {

    private final AnyMarketProperties properties;

    @Bean
    public HttpServiceProxyFactory anymarketHttpServiceProxyFactory() {
        ClientHttpRequestInterceptor interceptor = new AnymarketAuthFilter(properties.getToken());
        return RestClientFactory.createFactory(properties.getBaseUrl(), interceptor);
    }

    @Bean
    public AnymarketClient anymarketClient(HttpServiceProxyFactory anymarketHttpServiceProxyFactory) {
        return anymarketHttpServiceProxyFactory.createClient(AnymarketClient.class);
    }
}
