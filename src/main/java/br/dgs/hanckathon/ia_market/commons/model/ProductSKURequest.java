package br.dgs.hanckathon.ia_market.commons.model;

import br.dgs.hanckathon.ia_market.product.model.AdjustedProduct;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSKURequest {

    private String title;
    private String partnerId;
    private String ean;
    private int amount;
    private double price;
    private int additionalTime;
    private int stockLocalId;
    private Map<String, String> variations;
    private List<AdditionalStockDTO> additionalStocks;
    private String externalId;

    public ProductSKURequest(AdjustedProduct product) {
        this.title = product.getTitle();
        this.partnerId = product.getPartnerId();
        this.ean = product.getEan();
        this.amount = product.getAmount();
        this.additionalTime = product.getAdditionalTime();
        this.price = product.getPrice();
        this.stockLocalId = product.getStockLocalId();
        this.additionalStocks = product.getAdditionalStocks();
        this.externalId = product.getExternalId();
    }

}