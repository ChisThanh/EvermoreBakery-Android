package project.evermorebakery.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import project.evermorebakery.Model.ModelComment;
import project.evermorebakery.R;

public class AdapterComment extends RecyclerView.Adapter<AdapterComment.CommentViewHolder>
{
    Context context;
    ArrayList<ModelComment> comment_list;

    public AdapterComment(Context context, ArrayList<ModelComment> comment_list)
    {
        this.context = context;
        this.comment_list = comment_list;
    }

    @NonNull
    @Override
    public AdapterComment.CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int view_type)
    {
        return new CommentViewHolder(LayoutInflater
                .from(context).inflate(R.layout.adapter_comment, parent, false));
    }

    @SuppressLint({"DiscouragedApi", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull AdapterComment.CommentViewHolder holder, int position)
    {
        ModelComment comment = comment_list.get(position);

        holder.vImage_dComment_Avatar.setImageResource(R.drawable.square_cat);
        holder.vText_dComment_Name.setText(comment.getAccount());
        holder.vText_dComment_Comment.setText(comment.getComment());
        holder.uRating_dComment_Rating.setRating(comment.getRating());
    }

    @Override
    public int getItemCount() {
        return comment_list.size();
    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder
    {
        ImageView vImage_dComment_Avatar;
        TextView vText_dComment_Name;
        RatingBar uRating_dComment_Rating;
        TextView vText_dComment_Comment;

        public CommentViewHolder(@NonNull View item_view)
        {
            super(item_view);
            vImage_dComment_Avatar = item_view.findViewById(R.id.vImage_dComment_Avatar);
            vText_dComment_Name = item_view.findViewById(R.id.vText_dComment_Name);
            vText_dComment_Comment = item_view.findViewById(R.id.vText_dComment_Comment);
            uRating_dComment_Rating = item_view.findViewById(R.id.uRating_dComment_Rating);
        }
    }
}
