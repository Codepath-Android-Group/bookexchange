package org.kazemicode.bookshare.adapters;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.parse.ParseException;
import com.parse.ParseUser;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import org.kazemicode.bookshare.R;
import org.kazemicode.bookshare.models.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;
    private static String TAG = "PostAdapter";

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);

    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // Instance fields

        RelativeLayout container;
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvType;
        TextView tvDateAdded;
        ImageView ivCover;
        TextView tvCustomDescription;
        TextView tvPoster;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            tvTitle = itemView.findViewById(R.id.tvPTitle);
            tvAuthor = itemView.findViewById(R.id.tvPAuthor);
            tvType = itemView.findViewById(R.id.tvType);
            tvDateAdded = itemView.findViewById(R.id.tvDateAdded);
            ivCover = itemView.findViewById(R.id.ivPCover);
            tvPoster = itemView.findViewById(R.id.tvPoster);
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public void bind(Post post) {
            //bind post data to element
            tvTitle.setText(post.getTitle());
            tvAuthor.setText(post.getAuthor());
            Log.i("PostAdapter", post.getPoster().toString());
            tvType.setText(post.getType());
            String daysSincePosted = calculateDays(post.getCreatedAt());
            tvDateAdded.setText(daysSincePosted);
            String name = getPosterName(post.getPoster());
            tvPoster.setText(name);
            if(!post.getImgUrl().isEmpty()){
                Glide.with(context).load(post.getImgUrl()).into(ivCover);
            }

        }

        public String getPosterName(ParseUser user){
            String name = "Posted by ";
            try {
                name += user.fetchIfNeeded().getUsername();
            } catch (ParseException e) {
                Log.v(TAG, e.toString());
                e.printStackTrace();
            }

            return name;

        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        public String calculateDays(Date posted) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
            LocalDate.now();
            long diffInMillis = Math.abs(posted.getTime() - new Date().getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);

            if(diff > 0) {
                return diff + " days ago";
            }
            else {
                return "today";
            }

        }
    }
}
