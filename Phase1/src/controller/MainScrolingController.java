package controller;

import database.PostDB;
import models.Comment;
import models.Post;

public class MainScrolingController extends Controller{

    public static void addComment(Comment comment){
        PostDB.addComment(comment);
    }
}
