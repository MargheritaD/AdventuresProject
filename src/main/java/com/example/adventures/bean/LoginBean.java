package com.example.adventures.bean;

import com.example.adventures.exception.EmailFormatException;

import java.util.regex.Pattern;

public class LoginBean {

    private String username;
    private String password;
    private int role; // 1 guide, 2 traveler
    public LoginBean(String username, String password) throws EmailFormatException{
        this.setUsername(username);
        this.setPassword(password);
    }
    public String getUsername(){
        return username;
    }
    public void setUsername(String username) throws EmailFormatException {
        String emailRegex = "[a-zA-Z0-9._%]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
        if(!Pattern.compile(emailRegex).matcher(username).matches())
            throw new EmailFormatException(username);
        this.username = username;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public int getRole(){
        return role;
    }
    public void setRole(int role){
        this.role = role;
    }

/*
    private String username;
    private String password;
    private int role; // 1 guide, 2 traveler

    public LoginBean(String username, String password) throws EmailFormatException{
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username) throws EmailFormatException {
        String emailRegex = "[a-zA-Z0-9._%]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}";
        if(!Pattern.compile(emailRegex).matcher(username).matches())
            throw new EmailFormatException(username);
        this.username = username;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public int getRole(){
        System.out.println("Sono nel bean set role");return role;
    }

    public void setRole(int role){
        this.role = role;
    }

 */
}
