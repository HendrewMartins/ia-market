package br.dgs.hanckathon.ia_market.externals.anymarket.interceptor;

import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.HttpRequest;

import java.io.IOException;

public class GumgaAuthFilter implements ClientHttpRequestInterceptor {

    private final String gumgaToken;

    public GumgaAuthFilter(String gumgaToken) {
        this.gumgaToken = gumgaToken;
    }

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("gumgaToken", gumgaToken);
        return execution.execute(request, body);
    }
}