package com.triplea;

import java.util.TreeMap;

class ResourceData {
    private TreeMap<Integer, Integer> userid;

    public ResourceData() {//Инициализируем контейнер
        userid = new TreeMap<>();
    }

    public void AddUserPermission(int ID, int Role) {
        Integer Data = userid.get(ID);
        if (Data == null)
        {
            userid.put(ID, Role);
        } else {
            if ((Data & Role) == 0) {
                userid.put(ID, Data + Role);
            }
        }
    }

    public boolean IsPermissionExist(int ID, int Role) {
        Integer Data = userid.get(ID);
        if (Data != null)
        {
            if ((Data & Role) == 1) {
                return true;
            }
        }
        return false;
    }


}