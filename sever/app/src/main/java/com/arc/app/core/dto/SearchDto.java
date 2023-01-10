package com.arc.app.core.dto;

public class SearchDto {
    private Integer pageIndex;
    private Integer pageSize;
    private String textSearch;

    private Integer typeParameter;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getTextSearch() {
        return textSearch;
    }

    public void setTextSearch(String textSearch) {
        this.textSearch = textSearch;
    }

    public Integer getTypeParameter() {
        return typeParameter;
    }

    public void setTypeParameter(Integer typeParameter) {
        this.typeParameter = typeParameter;
    }
}
