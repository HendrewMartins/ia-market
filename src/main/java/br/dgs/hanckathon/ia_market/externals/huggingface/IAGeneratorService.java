package br.dgs.hanckathon.ia_market.externals.huggingface;

import br.dgs.hanckathon.ia_market.commons.model.CategoryAttributesResponse;
import br.dgs.hanckathon.ia_market.commons.model.CategoryResponse;
import br.dgs.hanckathon.ia_market.commons.util.JsonUtils;
import br.dgs.hanckathon.ia_market.product.model.AdjustedProduct;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;
import java.util.Map;

@Service
public class IAGeneratorService {

    private final WebClient webClient;

    public IAGeneratorService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("https://api.openai.com/v1").build();
    }

    public AdjustedProduct adjustProduct(AdjustedProduct product,
                                         List<CategoryResponse> categories,
                                         List<CategoryAttributesResponse> attributes) {

        String prompt = "Ajuste este produto para o marketplace " + product.getMarketPlace() +
                " com base nas categorias e atributos.\n" +
                "Produto: " + JsonUtils.toJson(product) + "\n" +
                "Categoria: " + JsonUtils.toJson(categories) + "\n" +
                "Atributos: " + JsonUtils.toJson(attributes) + "\n" +
                "Retorne apenas o JSON do produto ajustado.";

        try {
            Map<String, Object> requestBody = Map.of(
                    "model", "gpt-4",
                    "messages", List.of(
                            Map.of("role", "user", "content", prompt)
                    ),
                    "temperature", 0
            );

            String response = webClient.post()
                    .uri("/chat/completions")
                    .header("Authorization", "Bearer " + "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")
                    .bodyValue(requestBody)
                    .retrieve()
                    .bodyToMono(Map.class)
                    .map(res -> ((List<Map<String, Object>>) ((Map<String, Object>) res).get("choices")).get(0).get("message"))
                    .map(msg -> (String) ((Map<String, Object>) msg).get("content"))
                    .block();

            if (response != null && !response.isEmpty()) {
                return JsonUtils.fromJson(response, AdjustedProduct.class);
            }

        } catch (WebClientResponseException e) {
            e.printStackTrace();
        }

        return product; // fallback
    }
}
