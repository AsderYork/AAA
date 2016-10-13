package com.triplea;

import java.util.TreeMap;

class ResourceData {
    private TreeMap<Integer, Integer> UserID;//Ключ - ид пользователя. Значение - тип доступа

    public ResourceData() {//Инициализируем контейнер
        UserID = new TreeMap<>();
    }

    public void AddUserPermission(int ID, int Role) {//Еще раз напомню, 1-READ;2-WRITE;4-EXECUTE;
        Integer Data = UserID.get(ID);
        if (Data == null)//Если записи о пользователе еще нет
        {
            UserID.put(ID, Role);
        } else {
            if ((Data & Role) == 0) {//Если такой роли еще нет, то добавляем её
                UserID.put(ID, Data + Role);
            }//Иначе не делаем ничего
        }
    }

    public boolean IsPermissionExist(int ID, int Role) {
        Integer Data = UserID.get(ID);
        if (Data != null)//Проверяем, есть ли запись у этого пользователея.
        {
            if ((Data & Role) == 1) {//Если такая роль есть, то сообщаем об этом
                return true;
            }
        }
        return false;
    }


}