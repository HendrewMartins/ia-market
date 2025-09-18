package br.dgs.hanckathon.ia_market.commons.model;

import lombok.Data;

import java.util.List;

@Data
public class CategoryAttributesResponse {
    private String description;
    private String codeInMarketPlace;
    private boolean hidden;
    private boolean recommended;
    private boolean requireValueBind;
    private int valueCount;
    private List<String> tags;
    private List<Object> dataDefinitions;
}

