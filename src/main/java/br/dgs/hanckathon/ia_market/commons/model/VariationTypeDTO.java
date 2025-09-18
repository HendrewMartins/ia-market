package br.dgs.hanckathon.ia_market.commons.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VariationTypeDTO {
    private int id;
    private String name;
    private boolean visualVariation;
}
