package com.example.doctalk;
public class Chat {
    private String name;

    private String message;

    public String getName(){
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Chat(String name, String message) {

        this.name = name;
        this.message = message;

    }

    public Chat(){}
//Chat class to store data in class when we receieve it from DataBase
}
