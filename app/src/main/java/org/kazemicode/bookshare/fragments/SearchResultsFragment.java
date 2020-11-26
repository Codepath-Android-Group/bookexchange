package org.kazemicode.bookshare.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.kazemicode.bookshare.R;
import org.kazemicode.bookshare.adapters.BookAdapter;
import org.kazemicode.bookshare.adapters.PostAdapter;
import org.kazemicode.bookshare.models.Post;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchResultsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchResultsFragment extends Fragment {

    public static final String TAG = "SearchResultsFragment";
    protected List<Post> posts;
    protected PostAdapter adapter;


    public SearchResultsFragment() {
        // Required empty public constructor
    }

    @SuppressWarnings("unused")
    public SearchResultsFragment newInstance(int columnCount) {
        SearchResultsFragment fragment = new SearchResultsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_results, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String isbn = getArguments().getString("search");

        // initialize books ArrayList so it can get Posts
        posts = new ArrayList<>();
        // Set recycler view so we can view books list on screen
        RecyclerView rvPosts = view.findViewById(R.id.rvPosts);
        // Create the adapter
        adapter = new PostAdapter(getContext(), posts);
        // Set the adapter on the RecycleView
        rvPosts.setAdapter(adapter);
        // Set a Layout Manager on the Recycler View
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));
        // query
        searchByISBN(isbn);
    }

    private void searchByISBN(String isbn) {
        int LIMIT = 20;
        //specify which class to query
        ParseQuery<Post> query = ParseQuery.getQuery(Post.class);
        query.setLimit(LIMIT);
        // order by most recent
        query.addDescendingOrder(Post.KEY_CREATED_AT);
        // Retrieve all posts
        query.findInBackground(new FindCallback<Post>() {
            public void done(List<Post> resultPosts, ParseException e) {
                if (e == null) {
                    for(Post post : resultPosts) {
                        Log.i(TAG, "Post: " + post.getDescription());
                    }
                } else {
                    // something went wrong
                    Log.e(TAG, "Issue with getting posts", e);
                }

                posts.addAll(resultPosts);
                adapter.notifyDataSetChanged();

            }
        });
    }







}