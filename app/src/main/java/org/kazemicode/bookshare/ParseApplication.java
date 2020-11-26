package org.kazemicode.bookshare;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import org.kazemicode.bookshare.models.Post;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register Parse models before initialization code
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("TrUOrKkRACF05MRb7LBkNz8AwNvUHMuc4TYhvnKV")
                .clientKey("rljHzFD7AjNJVkB7P4ZIuwqoopDy6tqaz4fMWgpU")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
