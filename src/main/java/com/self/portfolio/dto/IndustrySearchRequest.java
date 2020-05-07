package com.self.portfolio.dto;

public class IndustrySearchRequest {

    private Object searchBy;

    private Boolean mixed= false;

    public Boolean getMixed() {
        return mixed;
    }

    public void setMixed(Boolean mixed) {
        this.mixed = mixed;
    }

    public Object getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(Object searchBy) {
        this.searchBy = searchBy;
    }

    
}