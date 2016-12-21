package com.myproj;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.Calendar;
import java.util.Date;

public class DAO {
    //Устанавливаем соединение с PosgreSQL
    private static final Logger logger = LoggerFactory.getLogger(DAO.class);
    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            logger.error("No driver");
            System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
            e.printStackTrace();
            return null;
        }
        System.out.println("PostgreSQL JDBC Driver Registered!");
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/WebDB", "root", "root");
        } catch (SQLException e) {
            logger.error("Can't to connect");
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }
        return connection;
    }
    //Функция добавления пользователя
    public static void addUser(String name, String pass){
        try {
            Connection connect = getConnection();
            PreparedStatement prepStat = connect.prepareStatement("INSERT INTO users (name, pass) VALUES ('" + name +"', '" + pass + "');");
            prepStat.executeUpdate();
            System.out.println("User added");
        } catch (ClassNotFoundException e) {
            logger.error("Class not found");
            e.printStackTrace();
        } catch (SQLException e) {
            logger.error("Incorrect sql query");
            e.printStackTrace();
        }
    }
    //Функция проверки сущесвтующего имени пользователя в БД
    public static boolean checkUserExist(String name)
    {
        name = name.toLowerCase();
        try {
            Connection connect = getConnection();
            PreparedStatement prepStat = connect.prepareStatement("SELECT * FROM users WHERE LOWER (name) = '" + name + "';");
            ResultSet myResult = prepStat.executeQuery();
            if(myResult.next()){
                return true;
            }
        } catch (SQLException e) {
            logger.error("Incorrect sql query");
            e.printStackTrace();
        } catch (NullPointerException c){
            logger.error("Link to null");
            c.printStackTrace();
        } catch (ClassNotFoundException e) {
            logger.error("Class not found");
            e.printStackTrace();
        }
        return false;
    }
    //Функция проверки соответсвии имени и пароля
    public static boolean checkUser(String name, String password)
    {
        try {
            Connection connect = getConnection();
            PreparedStatement prepStat = connect.prepareStatement("SELECT * FROM users WHERE name = '" + name +"' AND pass = '" + password + "';");
            ResultSet myResult = prepStat.executeQuery();
            if(myResult.next()){
                return true;
            }
        } catch (ClassNotFoundException e) {
            logger.error("Class not found");
            e.printStackTrace();
        } catch (SQLException e) {
            logger.error("Incorrect sql query");
            e.printStackTrace();
        }
        return false;
    }
    //Функция проверки правильности ввода имени при регистрации
    public static boolean checkName(String name)
    {
        char[] charArray = name.toCharArray();
        for(char c : charArray)
        {
            if( ((int)c >= 65 && (int)c <= 90) || ((int)c >=97 && (int)c <= 122) || ((int)c >=48 && (int)c <= 57) ){
            }else return false;
        }
        return true;
    }
    //Функция распознования времени
    public static String checkTime()
    {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int hours = cal.get(Calendar.HOUR_OF_DAY);
        String whatTime;

        if(hours >= 6 && hours < 10){
            whatTime = "Доброе утро, ";
        }else if(hours >= 10 && hours < 18){
            whatTime = "Добрый день, ";
        }else if (hours >= 18 && hours < 22) {
            whatTime = "Добрый вечер, ";
        }else whatTime = "Доброй ночи, ";
        return whatTime;
    }
    //Функция проверки правильности введенного пароля при регистрации
    public static boolean checkPass(String pass)
    {
        char[] charArray = pass.toCharArray();
        boolean result = false;
        for(char c : charArray) {
            if(Character.isUpperCase(c)){
                result = true;
                break;
            }
        }
        if(result == true){
            result = false;
            for(char c : charArray){
                if(Character.isLowerCase(c)){
                    result = true;
                    break;
                }
            }
        }
        if(result == true){
            result = false;
            for(char c : charArray){
                if(Character.isDigit(c)){
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
}