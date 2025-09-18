package br.dgs.hanckathon.ia_market.configs;

import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

public class RestClientFactory {
    public static HttpServiceProxyFactory createFactory(String baseUrl, ClientHttpRequestInterceptor clientHttpRequestInterceptor) {
        RestClient restClient = RestClient.builder().baseUrl(baseUrl).requestInterceptor(clientHttpRequestInterceptor).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        return HttpServiceProxyFactory.builderFor(adapter).build();
    }

    public static <T> T createClient(String baseUrl, Class<T> clientClass) {
        RestClient restClient = RestClient.builder().baseUrl(baseUrl).build();
        RestClientAdapter adapter = RestClientAdapter.create(restClient);
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        return factory.createClient(clientClass);
    }
}