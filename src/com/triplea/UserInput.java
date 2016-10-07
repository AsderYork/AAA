package com.triplea;
import java.time.LocalDateTime;

class UserInput {
    String name;//Имя пользователя
    String password;//Пароль
    String resource;//Путь до ресурса
    String role;//Роль использования ресурса
    LocalDateTime startDateOfResourceRequest;//Начальная дата
    LocalDateTime endDateOfResourceRequest;//Конечная дата
    int valueOfResourse; //значение ресурса
}