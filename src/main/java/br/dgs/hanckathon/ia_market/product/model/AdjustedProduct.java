package br.dgs.hanckathon.ia_market.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdjustedProduct extends Product {

    private String marketPlace;

    public AdjustedProduct(String id, String name, String description, String marketPlace) {
        super(id, name, description);
        this.marketPlace = marketPlace;
    }
}
