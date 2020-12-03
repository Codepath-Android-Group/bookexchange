package org.kazemicode.bookshare.adapters;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
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
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import org.kazemicode.bookshare.R;
import org.kazemicode.bookshare.fragments.DetailedFragment;
import org.kazemicode.bookshare.models.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;
    private static String TAG = "PostAdapter";
    private String daysSincePosted;

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
        Log.i(TAG, String.valueOf(posts.size()));
        return posts.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
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
        public void bind(final Post post) {
            //bind post data to element
            tvTitle.setText(post.getTitle());
            tvAuthor.setText(post.getAuthor());
            // Log.i("PostAdapter", post.getPoster().toString());
            tvType.setText(post.getType());
            daysSincePosted = calculateDays(post.getCreatedAt());
            tvDateAdded.setText(daysSincePosted);
            final String name = getPosterName(post.getPoster());
            tvPoster.setText(name);
            if(!post.getImgUrl().isEmpty()){
                Glide.with(context).load(post.getImgUrl()).into(ivCover);
            }

            // Register click listener on whole row
            container.setOnClickListener((new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Use Fragments
                    Bundle bundle = new Bundle();
                    bundle.putString("isbn", post.getISBN());
                    bundle.putString("img", post.getImgUrl());
                    bundle.putString("author", post.getAuthor());
                    bundle.putString("custom", post.getCustomDescription());
                    bundle.putString("summary", post.getDescription());
                    bundle.putString("poster", name);
                    bundle.putString("type", post.getType());
                    bundle.putString("title", post.getTitle());
                    bundle.putString("date", daysSincePosted);
                    Fragment fragment = new DetailedFragment();
                    fragment.setArguments(bundle);
                    FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.replace(R.id.content, fragment, null).commit();
                }
            }));

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
