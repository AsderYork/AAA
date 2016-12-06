package com.triplea.domain;

/**
 * Created by Asder on 05.12.2016.
 */
public class ResourceData {
    public Integer ID;
    public String Subresource;
    public Integer Permission;


    public ResourceData(Integer ID, String subresource, Integer permission) {
        this.ID = ID;
        Subresource = subresource;
        Permission = permission;
    }

    public ResourceData() {
        this.ID = null;
        Subresource = null;
        Permission = null;
    }
}
