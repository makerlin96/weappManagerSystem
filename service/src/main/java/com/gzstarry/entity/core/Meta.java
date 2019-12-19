package com.gzstarry.entity.core;

import lombok.Data;

@Data
public class Meta {
    private String title;
    private String icon;

    public Meta(String title, String icon) {
        this.title = title;
        this.icon = icon;
    }

    public Meta(String title) {
        this.title = title;
    }
}
