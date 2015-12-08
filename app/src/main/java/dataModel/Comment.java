package dataModel;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Agnieszka on 2015-11-26.
 */
public class Comment {
    public String idAuthor;
    public String loginAuthor;
    public String idUserTo;
    public String date;
    public String commentText;

    public Comment(JSONObject jsonObject) throws JSONException{
        JSONObject jsonObjectAuthor = jsonObject.getJSONObject("user");
        idAuthor = jsonObjectAuthor.getString("id");
        loginAuthor = jsonObjectAuthor.getString("login");

        idUserTo = jsonObject.getString("idUserTo");
        date = jsonObject.getString("data").substring(0,10);
        commentText = jsonObject.getString("comment");
    }
    /*public Comment(){
        idAuthor = "ada";
        loginAuthor = "ada";
        idUserTo = "2";
        date = "2015-10-30";
        commentText="n;aef";
    }*/

}


