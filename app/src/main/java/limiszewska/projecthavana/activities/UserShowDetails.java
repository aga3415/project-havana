package limiszewska.projecthavana.activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import dataModel.User;

/**
 * Created by Agnieszka on 2015-11-26.
 */
public class UserShowDetails extends Activity {

    public static User user;
    private static TextView login, reputation;
    private static Button addMark, addComment;
    private static ListView commentsList;
    private static EditText writeComment;
    private static RatingBar rating, giveMarkRatingBar;

    public static UserShowDetails instance;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_user_show_details_portrait);
        } else {
            setContentView(R.layout.activity_user_show_details_portrait);
        };

        login = (TextView) findViewById(R.id.userLogin);
        reputation = (TextView) findViewById(R.id.reputationMarkText);
        rating = (RatingBar) findViewById(R.id.reputationMark);
        giveMarkRatingBar = (RatingBar) findViewById(R.id.giveReputationMark);
        addMark = (Button) findViewById(R.id.addMarkButton);
        writeComment = (EditText) findViewById(R.id.writeCommentEditText);
        addComment = (Button) findViewById(R.id.addCommentButton);
        commentsList = (ListView) findViewById(R.id.commentsListView);

        if (user != null){
            setDetailAboutUser();
        }
    }

    public void setDetailAboutUser(){
        login.setText(user.login);
        if (user.reputation.equals("null")){
            reputation.setText("Brak ocen");
            rating.setRating((float) 0.0);
        }else{
            reputation.setText(user.reputation);
            rating.setRating(Float.parseFloat(user.reputation)/5);
        }



    }

    public static void setUser(User user){
        UserShowDetails.user = user;
    }

    public static UserShowDetails getInstance(){
        return instance==null? new UserShowDetails() : instance;
    }
}
