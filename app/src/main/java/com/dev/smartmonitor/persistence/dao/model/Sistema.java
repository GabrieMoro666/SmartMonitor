package com.dev.smartmonitor.persistence.dao.model;

public class Sistema {
    private Long id;

    public Sistema() {
        this.id = 0L;
    }

    public Sistema(Long id) {
        this.id = id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
