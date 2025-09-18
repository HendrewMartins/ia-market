package br.dgs.hanckathon.ia_market.commons.model;

import lombok.Data;

@Data
public class CategoryResponse {
    private String codeInMarketPlace;
    private String name;
    private String completePath;
}