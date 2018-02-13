package com.mapps.stroleapp;

import android.provider.MediaStore;

/**
 * Created by Harshita on 13/02/2018.
 */

public class FeedbackData {

    String user_id ;
    String user_email;
    String query_feedback;
    String suggestion_feedback;
    String path;


    public FeedbackData(){

    }

    public FeedbackData(String user_id , String user_email , String query_feedback , String suggestion_feedback , String path){
        this.user_id = user_id;
        this.query_feedback = query_feedback;
        this.suggestion_feedback = suggestion_feedback;
        this.user_email = user_email;
        this.path = path;
    }

    public String getQuery_feedback() {
        return query_feedback;
    }

    public String getSuggestion_feedback() {
        return suggestion_feedback;
    }


    public String getUser_id() {
        return user_id;
    }

    public String getUser_email() {
        return user_email;
    }

    public String getPath() {
        return path;
    }
}
