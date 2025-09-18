package br.dgs.hanckathon.ia_market.externals.anymarket.client;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;


public interface AnymarketClient {

    @GetMapping("/rest/api/marketplaces/{marketplace}/categories/{categoryId}")
    Mono<CategoryResponse> getCategory(
        @PathVariable("marketplace") String marketplace,
        @PathVariable("categoryId") String categoryId,
        @RequestHeader("Authorization") String token
    );

    @GetMapping("/rest/api/marketplace_category_attributes/categories/{categoryId}/marketplace_attributes")
    Mono<CategoryAttributesResponse> getCategoryAttributes(
        @PathVariable("categoryId") Long categoryId,
        @RequestHeader("Authorization") String token
    );
}
