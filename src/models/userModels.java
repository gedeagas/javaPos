package models;

import service.Database;

/**
 * Created by gedeagas on 22/11/17.
 */
public class userModels {
    private String username, password, name;
    Database db = new Database();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Database getDb() {
        return db;
    }

    public void setDb(Database db) {
        this.db = db;
    }

    public userModels(String username, String password){

        this.username = username;
        this.password = password;


    }
    public String login(){
        return db.loginUser(this.username,this.password);
    }

    public void setUserToDb(){
        db.addUserToDatabase(this.username,this.password);
    }

    public static void main(String[] args) {
    }
}
