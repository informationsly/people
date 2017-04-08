package com.demo.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by liyong on 2017/4/7.
 */

@Component
@ConfigurationProperties(prefix = "people")
public class PeopleProperties {

    private String skinColor;
    private Integer averageHeight;

    public String getSkinColor() {
        return skinColor;
    }

    public void setSkinColor(String skinColor) {
        this.skinColor = skinColor;
    }

    public Integer getAverageHeight() {
        return averageHeight;
    }

    public void setAverageHeight(Integer averageHeight) {
        this.averageHeight = averageHeight;
    }
}
