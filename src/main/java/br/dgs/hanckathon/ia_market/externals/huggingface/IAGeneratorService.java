package br.dgs.hanckathon.ia_market.externals.huggingface;

import ai.djl.Model;
import ai.djl.inference.Predictor;
import ai.djl.ndarray.NDList;
import ai.djl.translate.Batchifier;
import ai.djl.translate.TranslateException;
import ai.djl.translate.Translator;
import ai.djl.translate.TranslatorContext;
import br.dgs.hanckathon.ia_market.commons.model.CategoryAttributesResponse;
import br.dgs.hanckathon.ia_market.commons.model.CategoryResponse;
import br.dgs.hanckathon.ia_market.commons.util.JsonUtils;
import br.dgs.hanckathon.ia_market.product.model.AdjustedProduct;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IAGeneratorService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public AdjustedProduct adjustProduct(AdjustedProduct product,
                                         List<CategoryResponse> category,
                                         List<CategoryAttributesResponse> attributes) {
        // 1️⃣ Montar prompt
        String prompt = "Ajuste este produto para o marketplace " + product.getMarketPlace() +
                " com base nas categorias e atributos.\n" +
                "Produto: " + JsonUtils.toJson(product) + "\n" +
                "Categoria: " + JsonUtils.toJson(category) + "\n" +
                "Atributos: " + JsonUtils.toJson(attributes) + "\n" +
                "Retorne apenas o JSON do produto ajustado.";

        try {
            // 2️⃣ Carregar o modelo do Hugging Face Hub via DJL
            try (Model model = Model.newInstance("Salesforce/codegen-6B-mono")) {

                // 3️⃣ Criar translator simples
                Translator<String, String> translator = new Translator<>() {
                    @Override
                    public NDList processInput(TranslatorContext ctx, String input) {
                        // Aqui você pode converter a string para NDArray se necessário
                        return new NDList();
                    }

                    @Override
                    public String processOutput(TranslatorContext ctx, NDList list) {
                        // Aqui você processa a saída do modelo para String
                        return list.singletonOrThrow().toString();
                    }

                    @Override
                    public Batchifier getBatchifier() {
                        return null;
                    }
                };

                // 4️⃣ Criar predictor
                try (Predictor<String, String> predictor = model.newPredictor(translator)) {
                    String generatedText = predictor.predict(prompt);

                    // 5️⃣ Desserializar para AdjustedProduct
                    return objectMapper.readValue(generatedText, AdjustedProduct.class);
                }
            }

        } catch (TranslateException e) {
            System.err.println("Erro de tradução com DJL: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return product; // fallback
    }
}
