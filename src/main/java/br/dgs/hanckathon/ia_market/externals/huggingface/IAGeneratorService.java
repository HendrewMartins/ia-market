package br.dgs.hanckathon.ia_market.externals.huggingface;

import ai.djl.ModelException;
import ai.djl.inference.Predictor;
import ai.djl.ndarray.NDList;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ModelZoo;
import ai.djl.repository.zoo.ZooModel;
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

import java.io.IOException;
import java.util.List;

@Service
public class IAGeneratorService {

    private final ObjectMapper objectMapper = new ObjectMapper();

    public AdjustedProduct adjustProduct(AdjustedProduct product,
                                         List<CategoryResponse> category,
                                         List<CategoryAttributesResponse> attributes) {

        String prompt = "Ajuste este produto para o marketplace " + product.getMarketPlace() +
                " com base nas categorias e atributos.\n" +
                "Produto: " + JsonUtils.toJson(product) + "\n" +
                "Categoria: " + JsonUtils.toJson(category) + "\n" +
                "Atributos: " + JsonUtils.toJson(attributes) + "\n" +
                "Retorne apenas o JSON do produto ajustado.";

        try {
            // 1️⃣ Define translator simples (String -> String)
            Translator<String, String> translator = new Translator<>() {
                @Override
                public NDList processInput(TranslatorContext ctx, String input) {
                    return new NDList(ctx.getNDManager().create(input));
                }

                @Override
                public String processOutput(TranslatorContext ctx, NDList list) {
                    return list.singletonOrThrow().toString();
                }

                @Override
                public Batchifier getBatchifier() {
                    return null;
                }
            };

            // 2️⃣ Usa Criteria para carregar modelo Hugging Face (ex: GPT2)
            Criteria<String, String> criteria = Criteria.builder()
                    .setTypes(String.class, String.class)
                    .optModelUrls("djl://ai.djl.huggingface/gpt2")
                    .optTranslator(translator)
                    .build();

            try (ZooModel<String, String> model = ModelZoo.loadModel(criteria);
                 Predictor<String, String> predictor = model.newPredictor()) {
                String output = predictor.predict(prompt);
                System.out.println(output);
            }

        } catch (TranslateException | IOException | ModelException e) {
            e.printStackTrace();
        }

        return product; // fallback
    }
}
