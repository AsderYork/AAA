package com.triplea.domain;

/**
 * Created by Asder on 05.12.2016.
 */
public class ResourceData {
    public Integer id;
    public String subresource;
    public Integer permission;


    public ResourceData(Integer id, String subresource, Integer permission) {
        this.id = id;
        this.subresource = subresource;
        this.permission = permission;
    }

    public ResourceData() {
        this.id = null;
        subresource = null;
        permission = null;
    }
}
