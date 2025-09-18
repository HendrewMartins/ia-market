package br.dgs.hanckathon.ia_market.commons.model;

import lombok.Data;

@Data
public class CategoryAttributesResponse {
    private Long id;
    private String attributeName;
    private boolean required;
    private String type;
}

