package com.rba.ecommerce.model.entity;

/**
 * Created by Ricardo Bravo on 29/06/16.
 */

public class ItemEntity {

    private String id, description;

    public ItemEntity(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
