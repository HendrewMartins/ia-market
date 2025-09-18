package br.dgs.hanckathon.ia_market.commons.model;

import lombok.Data;

@Data
public class CategoryResponse {
    private Long id;
    private String name;
    private String parentCategoryId;
}