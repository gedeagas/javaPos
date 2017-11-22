package models;

import service.Database;

/**
 * Created by gedeagas on 22/11/17.
 */
public class userModels {
    private String username, password;
    Database db = new Database();

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
}
