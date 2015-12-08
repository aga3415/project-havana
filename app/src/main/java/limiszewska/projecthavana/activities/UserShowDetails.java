package limiszewska.projecthavana.activities;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import adapters.CommentsListAdapter;
import dataModel.Comment;
import dataModel.User;
import rest.GetComments;
import rest.SendUserComment;
import rest.SendUserMark;
import rest.SettingConnections;

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
    public static CommentsListAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        instance = this;

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_user_show_details_portrait);
        } else {
            setContentView(R.layout.activity_user_show_details_landscape);
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
            GetComments getComments = new GetComments(instance);
            getComments.execute(user.id);
        }

        addMark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mark = Integer.toString((int) giveMarkRatingBar.getRating());
                SendUserMark sendUserMark = new SendUserMark();
                sendUserMark.execute(user.id, mark);
            }
        });

        addComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = writeComment.getText().toString();
                if (comment.length() > 1000) {
                    writeComment.setError(SettingConnections.context.getString(R.string.toLongCommentError));
                }else{
                    writeComment.setText("");
                    new SendUserComment().execute(user.id, comment);
                    GetComments getComments = new GetComments(instance);
                    getComments.execute(user.id);

                }
            }
        });
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
        GetComments getComments = new GetComments(instance);
        getComments.execute(user.id);
    }

    public static UserShowDetails getInstance(){
        return instance==null? new UserShowDetails() : instance;
    }

    public static void setCommentsList(ArrayList<Comment> commentArrayList){

        adapter = new CommentsListAdapter(instance, commentArrayList);
        commentsList.setAdapter(adapter);

    }
}
