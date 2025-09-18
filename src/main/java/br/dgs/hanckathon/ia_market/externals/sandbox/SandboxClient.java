package br.dgs.hanckathon.ia_market.externals.sandbox;

import br.dgs.hanckathon.ia_market.commons.model.ProductSKURequest;
import br.dgs.hanckathon.ia_market.commons.model.ProductSKUResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.PatchExchange;
import org.springframework.web.service.annotation.PostExchange;

import java.util.List;

public interface SandboxClient {

    @GetExchange("/v2/products/{productId}/skus")
    List<ProductSKUResponse> getProductSKU(
            @PathVariable int productId
    );

    @PostExchange("/v2/products/{productId}/skus")
    ProductSKUResponse postProductSKU(
            @PathVariable int productId,
            @RequestBody ProductSKURequest request
    );

    @PatchExchange(
            value = "/v2/products/{productId}/skus/{skuId}",
            contentType = "application/merge-patch+json"
    )
    ProductSKUResponse patchProductSKU(
            @PathVariable int productId,
            @PathVariable int skuId,
            @RequestBody ProductSKURequest request
    );
}
