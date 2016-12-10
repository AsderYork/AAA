package com.triplea.domain;

/**
 * Created by Asder on 05.12.2016.
 */

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(force=true)
public class ResourceData {
    public Integer id;
    public String subresource;
    public Integer permission;
}
