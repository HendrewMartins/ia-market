package br.dgs.hanckathon.ia_market.commons.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductSKUResponse {
    private int id;
    private String title;
    private String partnerId;
    private String ean;
    private int amount;
    private int additionalTime;
    private double price;
    private double sellPrice;
    private int stockLocalId;
    private List<VariationDTO> variations;
    private List<AdditionalStockDTO> additionalStocks;
    private String externalId;

    public ProductSKUResponse(ProductSKUResponse product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.partnerId = product.getPartnerId();
        this.ean = product.getEan();
        this.amount = product.getAmount();
        this.additionalTime = product.getAdditionalTime();
        this.price = product.getPrice();
        this.sellPrice = product.getSellPrice();
        this.stockLocalId = product.getStockLocalId();
        this.variations = product.getVariations();
        this.additionalStocks = product.getAdditionalStocks();
        this.externalId = product.getExternalId();
    }

}
