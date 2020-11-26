package org.kazemicode.bookshare.adapters;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import java.util.List;
import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import org.kazemicode.bookshare.R;
import org.kazemicode.bookshare.models.Post;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
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

    /* Within the RecyclerView.Adapter class */

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        // Instance fields

        RelativeLayout container;
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvDescription;
        TextView tvISBN;
        ImageView ivCover;
        TextView tvCustomDescription;
        TextView poster;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.resultsContainer);
           // tvTitle = itemView.findViewById(R.id.tvTitle);
           // tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvDescription = itemView.findViewById(R.id.tvDescription);
           // tvRank = itemView.findViewById(R.id.tvRank);
           // ivCover = itemView.findViewById(R.id.ivCover);
        }

        public void bind(Post post) {
            //bind post data to element
            tvDescription.setText(post.getDescription());
            //tvISBN.setText(post.getPoster().getUsername());
            //ParseFile image = post.getImage();
//            if(image != null){
//                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
//            }


        }
    }
}
