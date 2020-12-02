package org.kazemicode.bookshare.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kazemicode.bookshare.BuildConfig;
import org.kazemicode.bookshare.R;
import org.kazemicode.bookshare.adapters.BookAdapter;
import org.kazemicode.bookshare.adapters.PostAdapter;
import org.kazemicode.bookshare.models.Book;
import org.kazemicode.bookshare.models.Post;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Headers;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BestSellerBooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BestSellerBooksFragment extends Fragment {

    public static final String TAG = "BestSellerBooksFragment";
    public static final String API_KEY = BuildConfig.NYT_API_KEY;
    public static final String BEST_SELLER_URL = String.format("https://api.nytimes.com/svc/books/v3/lists/current/combined-print-and-e-book-fiction?isbn=9781524763138&api-key=%s", API_KEY);
    protected List<Book> books;
    protected List<Post> posts;
    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BestSellerBooksFragment() {
        // Required empty public constructor
    }

    @SuppressWarnings("unused")
    public static BestSellerBooksFragment newInstance(int columnCount) {
        BestSellerBooksFragment fragment = new BestSellerBooksFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_best_seller_books, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        posts = new ArrayList<>();
        final PostAdapter postAdapter = new PostAdapter(getContext(), posts);
        final EditText etSearch = getView().findViewById(R.id.etSearch);
        Button btnSearch = getView().findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i(TAG, "onClick search button");
                String isbn = etSearch.getText().toString();
                etSearch.setText("");
                // Use Fragments
                Bundle bundle = new Bundle();
                bundle.putString("search", isbn);
                Fragment fragment = new SearchResultsFragment();
                fragment.setArguments(bundle);
                FragmentManager fragmentManager = getFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.replace(R.id.content, fragment, null).commit();




            }

        });
    }



    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // initialize books ArrayList so it can get Books via our JSONArray
        books = new ArrayList<>();
        // Set recycler view so we can view books list on screen
        RecyclerView rvBooks = view.findViewById(R.id.rvBooks);
        // Create the adapter
        final BookAdapter bookAdapter = new BookAdapter(getContext(), books);
        // Set the adapter on the RecycleView
        rvBooks.setAdapter(bookAdapter);
        // Set a Layout Manager on the Recycler View
        rvBooks.setLayoutManager(new LinearLayoutManager(getContext()));


        // Create our Async client so we can request from our API endpoint
        final AsyncHttpClient client = new AsyncHttpClient();
        // Get Books
        client.get(BEST_SELLER_URL, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Headers headers, JSON json) {
                Log.d(TAG, "onSuccess");
                JSONObject jsonObject = json.jsonObject;
                try {
                    JSONObject results = jsonObject.getJSONObject("results");
                    JSONArray bookResults = results.getJSONArray("books");
                    Log.i(TAG, "Results: " + bookResults.toString());
                    books.addAll(Book.fromJsonArray(bookResults));
                    // Let adapter know when books updates to re-render
                    bookAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Log.i(TAG, "Books: " + books);
            }

            @Override
            public void onFailure(int statusCode, Headers headers, String response, Throwable throwable) {
                Log.d(TAG, "onFailure");
            }
        });

    }
}