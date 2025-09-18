package br.dgs.hanckathon.ia_market.externals.huggingface;


import br.dgs.hanckathon.ia_market.commons.model.CategoryAttributesResponse;
import br.dgs.hanckathon.ia_market.commons.model.CategoryResponse;
import br.dgs.hanckathon.ia_market.commons.util.JsonUtils;
import br.dgs.hanckathon.ia_market.configs.properties.HuggingFaceProperties;
import br.dgs.hanckathon.ia_market.product.model.AdjustedProduct;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.List;

@Service
public class IAGeneratorService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public IAGeneratorService(WebClient.Builder builder, HuggingFaceProperties properties) {
        this.webClient = builder.baseUrl(properties.getModelUrl())
                .defaultHeader("Authorization", "Bearer " + properties.getToken())
                .build();
    }

    public AdjustedProduct adjustProduct(AdjustedProduct product,
                                         List<CategoryResponse> category,
                                         List<CategoryAttributesResponse> attributes) {
        try {
            // 1️⃣ Montar prompt
            String prompt = "Ajuste este produto para o marketplace " + product.getMarketPlace() + " com base nas categorias e atributos.\n"
                    + "Produto: " + JsonUtils.toJson(product) + "\n"
                    + "Categoria: " + JsonUtils.toJson(category) + "\n"
                    + "Atributos: " + JsonUtils.toJson(attributes) + "\n"
                    + "Retorne apenas o JSON do produto ajustado.";

            // 2️⃣ Preparar payload para Hugging Face
            var body = new java.util.HashMap<String, Object>();
            body.put("inputs", prompt);

            // 3️⃣ Chamar Hugging Face e receber resposta (bloqueando por simplicidade)
            String response = webClient.post()
                    .bodyValue(body)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            // 4️⃣ Extrair texto gerado (o API retorna JSON com "generated_text")
            JsonNode node = objectMapper.readTree(response);
            String generatedText;
            if (node.isArray() && node.size() > 0 && node.get(0).has("generated_text")) {
                generatedText = node.get(0).get("generated_text").asText();
            } else {
                generatedText = "{}";
            }

            // 5️⃣ Desserializar para AdjustedProduct
            return objectMapper.readValue(generatedText, AdjustedProduct.class);

        } catch (WebClientResponseException e) {
            System.err.println("Erro HTTP: " + e.getRawStatusCode() + " - " + e.getResponseBodyAsString());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product;
    }
}
