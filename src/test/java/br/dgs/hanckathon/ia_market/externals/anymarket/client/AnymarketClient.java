package br.dgs.hanckathon.ia_market.externals.anymarket.client;


import br.dgs.hanckathon.ia_market.commons.model.CategoryAttributesResponse;
import br.dgs.hanckathon.ia_market.commons.model.CategoryResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.service.annotation.GetExchange;


public interface AnymarketClient {

    @GetExchange("/rest/api/marketplaces/{marketplace}/categories/{categoryId}")
    CategoryResponse getCategory(
            @PathVariable String marketplace,
            @PathVariable String categoryId,
            @RequestHeader("Authorization") String token
    );

    @GetExchange("/rest/api/marketplace_category_attributes/categories/{categoryId}/marketplace_attributes")
    CategoryAttributesResponse getCategoryAttributes(
            @PathVariable Long categoryId,
            @RequestHeader("Authorization") String token
    );
}
