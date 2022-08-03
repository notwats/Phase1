package database;

import jdk.jfr.StackTrace;
import models.Comment;
import models.Post;
import models.User;
// add post creation date not sure
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class PostDB extends DBGetter {

    public static ArrayList<Post> getFollowingsPost(Integer userID){
        ArrayList<Post> ret= new ArrayList<>();
        ArrayList<Integer> following= UserDB.getFollowings(userID);
        try {
            Connection con = DBInfo.getConnection();
            Statement st = con.createStatement();
            for (Integer uuID : following) {
               ArrayList<Post> usersPosts= getPostByUserID(uuID);
               ret.addAll(usersPosts);
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

        // sort compare collection

        return ret;
    }


    public static void addPost(Post post) {
        try {
            Connection con = DBInfo.getConnection();
            Statement st = con.createStatement();
            st.executeQuery("INSERT INTO post( sender_id, text, creation_time, type)  VALUES( "+post.getSender().getUserID()+",'"+post.getContext()+"',"+post.getCreationDate()+","+post.getAdPost()+")");

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Post> getPostByUserID(Integer sender_id) {
        ArrayList<Post> ret = new ArrayList<>();
        try {
            Connection con = DBInfo.getConnection();
            Statement st = con.createStatement();
            String query = "select * form post where sender_id = " + sender_id;
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) { // each post
                Post ps = new Post();
                ps.setPostID(rs.getInt("post_id"));
                ps.setSender(DBGetter.findUserByUserNumberID(rs.getInt(2)));
                //  ps.setRepliedPost(getPostbyPostID(rs.getLong(5)));
                //     ps.setLikes(rs.getInt(6));
                //    ps.setViews(rs.getInt(7));
                //    ps.setComments(rs.getInt(8));
                ps.setComments(getCommentByPostID(ps.getPostID()));
                ps.setCreationDate(LocalDateTime.parse(rs.getString("creation_time")));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ret;
    }


    public static ArrayList<Comment> getCommentByPostID(Integer post_id){
        ArrayList<Comment> ret = new ArrayList<>();
    try {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        String query = "select * form comment where post_id = " + post_id;
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
            Comment cc = new Comment();
            cc.setCommentID(rs.getInt(1));
            cc.setPostID(post_id);
            cc.setSender(rs.getInt(3));
            cc.setLikeNumber(rs.getInt(4));
            cc.setRepliedTo(rs.getInt(5));
            cc.setCommentText(rs.getString(6));
            ret.add(cc);
        }
    }
    catch (SQLException e) {
        e.printStackTrace();
    }
        return ret;
    }

    private static Comment getCommentByCommentID(int commentID) {
        Comment cc = null;
     try {
         Connection con = DBInfo.getConnection();
         Statement st = con.createStatement();
         String query = "select * form comment where comment_id = " + commentID;
         ResultSet rs = st.executeQuery(query);

         cc.setCommentID(rs.getInt(1));
         cc.setPostID(rs.getInt(2));
         cc.setSender(rs.getInt(3));
         cc.setLikeNumber(rs.getInt(4));
         cc.setRepliedTo(rs.getInt(5));
         cc.setCommentText(rs.getString(6));

     }
     catch (SQLException e){
         e.printStackTrace();
     }
        return cc;
    }


    public static Post getPostByPostID(Integer post_id) {
        Post ps = null;
        try {
            Connection connection = DBInfo.getConnection();
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery("SELECT * FROM post WHERE post_id = " + post_id);
            if (!rs.next()) {
                return null;
            }
            ps.setPostID(rs.getInt("post_id"));
            ps.setSender(DBGetter.findUserByUserNumberID(rs.getInt(2)));
            //  ps.setRepliedPost(getPostbyPostID(rs.getLong(5)));
            //     ps.setLikes(rs.getInt(6));
            //    ps.setViews(rs.getInt(7));
            //    ps.setComments(rs.getInt(8));
            ps.setComments(getCommentByPostID(ps.getPostID()));
            ps.setCreationDate(LocalDateTime.parse(rs.getString("creation_time")));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ps;
    }

    public static void deletePost(Post post) throws SQLException {
        Connection con = DBInfo.getConnection();
        Statement st = con.createStatement();
        st.execute("delete from post where post_id = " + post.getPostID());
        st.execute("delete from comment where post_id = " + post.getPostID());
        st.execute("delete from post_reaction where post_id = " + post.getPostID());
        st.execute("delete from post_view where post_id = " + post.getPostID());
        st.close();
        con.close();
    }

    public static void updatePost(Post post) {
        try {
            Connection con = DBInfo.getConnection();
            Statement st = con.createStatement();
            // post table
            st.execute("update post set `text` = '" + post.getContext() + "' where post_id = " + post.getPostID() + ";");

            //comment table
            // post reaction table
//another method
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addComment(Comment comment) {
        try {
            Connection con = DBInfo.getConnection();
            Statement statement = con.createStatement();
            // post table

            statement.executeQuery("INSERT INTO comment( sender_id, post_id, text ,replied_comment_id )  VALUES( " + comment.getSender() + "," + comment.getPostID() + "," + comment.getCommentText() +","+ comment.getRepliedTo() +")");

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteComment(Post post) {
        try {
            Connection con = DBInfo.getConnection();
            Statement st = con.createStatement();
            // post table
            st.execute("update post set `text` = '" + post.getContext() + "' where post_id = " + post.getPostID() + ";");

            //comment table
            // post reaction table
//another method
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addLike(Post post) {
        try {
            Connection con = DBInfo.getConnection();
            Statement st = con.createStatement();
            // post table
            st.execute("update post set `text` = '" + post.getContext() + "' where post_id = " + post.getPostID() + ";");

            //comment table
            // post reaction table
//another method
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeLike(Post post) {
        try {
            Connection con = DBInfo.getConnection();
            Statement st = con.createStatement();
            // post table
            st.execute("update post_reaction set `text` = '" + post.getContext() + "' where post_id = " + post.getPostID() + ";");

            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ad post??
    public static void newView(Post post) {
        try {
            Connection con = DBInfo.getConnection();
            Statement st = con.createStatement();
            // post table
            //           st.execute("insert into post_view values(NULL," +
//                "'" + post.getText().toString() + "','" +
//                "" + post.getCreationDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")) + "', " +
//                "" + post.getSender().getId() + ", "
//                + (post.getRepliedPost() != null ? Long.toString(post.getRepliedPost().getId()) : "0") + ", 0, 0, 0)");
//
            //comment table
            // post reaction table
//another method
            con.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
