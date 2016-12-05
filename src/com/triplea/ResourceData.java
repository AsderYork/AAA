package com.triplea;

/**
 * Created by Asder on 05.12.2016.
 */
public class ResourceData {
    Integer ID;
    String Subresource;
    Integer Permission;


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
