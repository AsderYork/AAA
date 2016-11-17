package com.triplea;

import java.util.TreeMap;

class ResourceData {
    private TreeMap<Integer, Integer> userid;

    public ResourceData() {
        userid = new TreeMap<>();
    }

    public void addUserPermission(int ID, int Role) {
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

    /*
    Role
    READ    1,3,5,7
    WRITE   2,3,6,7
    EXECUTE 4,5,6,7
     */

    public boolean isPermissionExist(int ID, int Role) {
        Integer Data = userid.get(ID);
        if (Data != null) {
            if (Role == 1) {
                switch (Data) {
                    case 1:
                    case 3:
                    case 5:
                    case 7: {
                        return true;
                    }
                }
            }

            if (Role == 2) {
                switch (Data) {
                    case 2:
                    case 3:
                    case 6:
                    case 7: {
                        return true;
                    }
                }
            }

            if (Role == 4) {
                switch (Data) {
                    case 4:
                    case 5:
                    case 6:
                    case 7: {
                        return true;
                    }
                }
            }
        }
        return false;
    }


}