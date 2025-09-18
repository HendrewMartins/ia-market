package br.dgs.hanckathon.ia_market.product.model;

import br.dgs.hanckathon.ia_market.commons.model.ProductSKUResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdjustedProduct extends ProductSKUResponse {

    private String marketPlace;

    public AdjustedProduct(ProductSKUResponse produto, String marketPlace) {
        super(produto);
        this.marketPlace = marketPlace;
    }


}
