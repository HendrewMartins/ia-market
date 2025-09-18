package br.dgs.hanckathon.ia_market.commons.model;

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

}