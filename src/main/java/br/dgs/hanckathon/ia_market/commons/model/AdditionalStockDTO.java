package br.dgs.hanckathon.ia_market.commons.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdditionalStockDTO {

    private double price;
    private int amount;
    private int additionalTime;
    private int stockLocalId;

}

