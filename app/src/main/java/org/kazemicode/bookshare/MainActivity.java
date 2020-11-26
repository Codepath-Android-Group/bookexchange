package org.kazemicode.bookshare;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import org.kazemicode.bookshare.adapters.PostAdapter;
import org.kazemicode.bookshare.fragments.BestSellerBooksFragment;
import org.kazemicode.bookshare.models.Post;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Use Fragments
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content, new BestSellerBooksFragment(), null).commit();

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