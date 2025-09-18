package br.dgs.hanckathon.ia_market.externals.anymarket.client;


import br.dgs.hanckathon.ia_market.commons.model.CategoryAttributesResponse;
import br.dgs.hanckathon.ia_market.commons.model.CategoryResponse;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;


public interface AnymarketClient {

    @GetExchange("/rest/api/marketplaces/MERCADO_LIVRE/categories?accountIdentifier=")
    List<CategoryResponse> getCategory();

    @GetExchange("rest/api/marketplace_category_attributes/categories/1381821/marketplaces/MERCADO_LIVRE/attributes/")
    List<CategoryAttributesResponse> getCategoryAttributes();
}
