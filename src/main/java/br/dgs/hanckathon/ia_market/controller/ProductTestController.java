package br.dgs.hanckathon.ia_market.controller;


import br.dgs.hanckathon.ia_market.commons.model.ProductSKURequest;
import br.dgs.hanckathon.ia_market.externals.sandbox.SandboxClient;
import br.dgs.hanckathon.ia_market.commons.model.ProductSKUResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductTestController {

    private final SandboxClient sandboxClient;

    @GetMapping("/product/sku")
    public List<ProductSKUResponse> getSku() {
        return sandboxClient.getProductSKU(349464315);
    }

    @PostMapping("/product/sku")
    public ProductSKUResponse postSku() {
        return sandboxClient.postProductSKU(349464315,
                new ProductSKURequest(
                        "Cuba Gourmet Inox 50x40cm Escovada com Cesta Escorredora, Dispenser de Detergente, Válvula de Ralo e Sifão – Durável, Moderna e Versátil para Cozinha", // title
                        "HACKATHON",     // partnerId
                        "7899851709858", // ean
                        50,              // amount
                        997.4,           // price
                        0,               // additionalTime
                        18448,           // stockLocalId
                        null,            // variations
                        null,            // additionalStocks
                        null             // externalId
                )
        );
    }

    @PatchMapping("/product/sku")
    public ProductSKUResponse putSku() {
        return sandboxClient.patchProductSKU(
                349464315,
                108559459,
                new ProductSKURequest(
                        "Cuba Gourmet Inox 50x40cm Escovada com Cesta Escorredora, Dispenser de Detergente, Válvula de Ralo e Sifão – Durável, Moderna e Versátil para Cozinha", // title
                        null,     // partnerId
                        null, // ean
                        50,              // amount
                        997.4,           // price
                        0,               // additionalTime
                        18448,           // stockLocalId
                        null,            // variations
                        null,            // additionalStocks
                        null             // externalId
                )
        );
    }
}
