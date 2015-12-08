package dataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Agnieszka on 2015-11-26.
 */
public class CommentsFactory {

    public static ArrayList<Comment> commentsList = new ArrayList<>();

    public static ArrayList<Comment> prepareCommentsList(JSONObject jsonObject){

        commentsList.clear();
        try {
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i=0; i< jsonArray.length(); i++){
                commentsList.add(new Comment(jsonArray.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return commentsList;
    }

}
