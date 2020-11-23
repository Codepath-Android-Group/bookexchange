package org.kazemicode.bookshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import java.util.List;
import java.util.ArrayList;
import com.parse.ParseUser;
import com.codepath.asynchttpclient.AsyncHttpClient;
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kazemicode.bookshare.adapters.BookAdapter;
import org.kazemicode.bookshare.models.Book;

import okhttp3.Headers;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    public static final String API_KEY = "3DfFcE09gMXxVkFSusqN3AJ6HVv7W0RI";
    public static final String BEST_SELLER_URL = String.format("https://api.nytimes.com/svc/books/v3/lists/current/combined-print-and-e-book-fiction?isbn=9781524763138&api-key=%s", API_KEY);
    List<Book> books;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // TODO: **** This stuff can be moved into a Fragment later ****
        // initialize books ArrayList so it can get Books via our JSONArray
        books = new ArrayList<>();
        // Set recycler view so we can view books list on screen
        RecyclerView rvBooks = findViewById(R.id.rvBooks);
        // Create the adapter
        final BookAdapter bookAdapter = new BookAdapter(this, books);
        // Set the adapter on the RecycleView
        rvBooks.setAdapter(bookAdapter);
        // Set a Layout Manager on the Recycler View
        rvBooks.setLayoutManager(new LinearLayoutManager(this));


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Inflate the menu; this adds item to the action bar
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if( item.getItemId() == R.id.logout){
            //icon has been selected
            ParseUser.logOut();
            ParseUser currentUser = ParseUser.getCurrentUser(); //this will be null now
            //go to login activity
            if (currentUser == null){
                Log.i(TAG, "Logged Out...");
                goToLoginActivity();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void goToLoginActivity (){
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();
    }
}