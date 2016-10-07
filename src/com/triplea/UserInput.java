package com.triplea;

import java.time.LocalDate;

class UserInput {
    public String name;//Имя пользователя
    public String password;//Пароль
    public String resource;//Путь до ресурса
    public String role;//Роль использования ресурса
    public LocalDate startDateOfResourceRequest;//Начальная дата
    public LocalDate endDateOfResourceRequest;//Конечная дата
    public int valueOfResourse; //значение ресурса
}