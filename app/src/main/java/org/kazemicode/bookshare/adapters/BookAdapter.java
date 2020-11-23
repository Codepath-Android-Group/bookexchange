package org.kazemicode.bookshare.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import org.kazemicode.bookshare.R;
import org.kazemicode.bookshare.models.Book;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>{
    // Need context to inflate a view
    Context context;
    // Need our Book data
    List<Book> books;

    // Constructor
    public BookAdapter(Context context, List<Book> books) {
        this.context = context;
        this.books = books;
    }

    // Involves inflating a layout from XML and returning the ViewHolder
    @NonNull
    @Override
    public BookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View bookView = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
        return new ViewHolder(bookView);
    }

    // Involves populating data into the item through ViewHolder
    @Override
    public void onBindViewHolder(@NonNull BookAdapter.ViewHolder holder, int position) {
        Book book = books.get(position);
        // bind book data into ViewHolder
        holder.bind(book);

    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    // inner ViewHolder class
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Instance fields
        RelativeLayout container;
        TextView tvTitle;
        TextView tvAuthor;
        TextView tvDescription;
        TextView tvRank;
        ImageView ivCover;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.container);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvAuthor = itemView.findViewById(R.id.tvAuthor);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvRank = itemView.findViewById(R.id.tvRank);
            ivCover = itemView.findViewById(R.id.ivCover);
        }

        public void bind(Book book) {
            tvTitle.setText(book.getTitle());
            tvAuthor.setText(book.getAuthor());
            tvRank.setText(book.getRank());
            tvDescription.setText(book.getDescription());
            String imageUrl = book.getImage_url();
            Glide.with(context).load(imageUrl).into(ivCover);


            // TODO: Search for book via ISBN in Backend upon tap
//            // 1. Register click listener on whole row
//            container.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    // 2.  Navigate to a new activity on tap
//                    Intent i = new Intent(context, DetailActivity.class);
//                    i.putExtra("book", Parcels.wrap(book));
//                    context.startActivity(i);
//
//                }
//            });
        }
    }
}
