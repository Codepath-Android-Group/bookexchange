package org.kazemicode.bookshare.models;

import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseUser;



@ParseClassName("Post")
public class Post extends ParseObject {
    // Ensure that your subclass has a public default constructor
    public static final String KEY_DESCRIPTION = "custom_description";
    public static final String KEY_POSTER = "poster";
    public static final String KEY_ISBN = "isbn";
    public static final String KEY_CREATED_AT = "createdAt";


    public Post() {
    }



    public ParseUser getPoster() {
        return getParseUser(KEY_POSTER );
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public String getISBN() {
        return getString(KEY_ISBN);
    }

    public void setPoster(ParseUser poster) {
        put(KEY_POSTER, poster);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public void setISBN(String isbn) {
        put(KEY_ISBN, isbn);
    }



}