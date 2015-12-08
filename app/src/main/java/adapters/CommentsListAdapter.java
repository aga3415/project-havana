package adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import dataModel.Comment;
import limiszewska.projecthavana.activities.R;

/**
 * Created by Agnieszka on 2015-11-26.
 */
public class CommentsListAdapter extends ArrayAdapter<Comment> {

    private Activity context;
    private ArrayList<Comment> commentsList;

    public CommentsListAdapter(Activity context, ArrayList<Comment> commentsList) {
        super(context, R.layout.list_item_comment, commentsList);
        this.context = context;
        this.commentsList = commentsList;

    }

    static class ViewHolder {
        TextView commentText;
        TextView date;
        TextView author;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        View rowView = convertView;
        if(rowView == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            rowView =  layoutInflater.inflate(R.layout.list_item_comment, null, true);

            viewHolder = new ViewHolder();
            viewHolder.commentText = (TextView) rowView.findViewById(R.id.commentText);
            viewHolder.date = (TextView) rowView.findViewById(R.id.commentDate);
            viewHolder.author = (TextView) rowView.findViewById(R.id.commentAuthor);
            rowView.setTag(viewHolder);

            rowView.setClickable(false);

        }else{
            viewHolder = (ViewHolder) rowView.getTag();
        }

        Comment currentComment = commentsList.get(position);
        viewHolder.commentText.setText(currentComment.commentText);
        viewHolder.author.setText(currentComment.loginAuthor);
        viewHolder.date.setText(currentComment.date);


        return rowView;
    }


}
