package org.kazemicode.bookshare;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Zrt5EoPWuGu4lZhrU3ygrVUPoEIC2pSZujLsDc59")
                .clientKey("zlSBzrMjqdX5eihfbht0DYTgkKWEsuGNaMWvyLW8")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
