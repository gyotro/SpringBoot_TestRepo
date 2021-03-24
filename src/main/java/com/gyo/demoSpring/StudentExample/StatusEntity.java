package com.gyo.demoSpring.StudentExample;

import org.springframework.lang.Nullable;

public class StatusEntity {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StatusEntity(String errorText) {
        this.errorText = errorText;
    }

    public String getErrorText() {
        return errorText;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public StatusEntity(Long id, String errorText) {
        this.id = id;
        this.errorText = errorText;
    }

    public StatusEntity() {
    }

    public Long id;
    public String errorText;

}
