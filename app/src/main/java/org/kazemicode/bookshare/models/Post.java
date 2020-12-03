package org.kazemicode.bookshare.models;

import com.parse.ParseObject;
import com.parse.ParseClassName;
import com.parse.ParseUser;



@ParseClassName("Post")
public class Post extends ParseObject {
    // Ensure that your subclass has a public default constructor
    public static final String KEY_CUSTOM_DESCRIPTION = "custom_description";
    public static final String KEY_POSTER = "poster";
    public static final String KEY_ISBN = "isbn";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_CREATED_AT = "createdAt";
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_TITLE = "title";
    public static final String KEY_TYPE = "type";
    public static final String KEY_IMG_URL = "img_url";



    public Post() {
    }

    public ParseUser getPoster() {
        return getParseUser(KEY_POSTER );
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public String getCustomDescription() {
        return getString(KEY_CUSTOM_DESCRIPTION);
    }

    public String getISBN() {
        return getString(KEY_ISBN);
    }

    public String getAuthor() {
        return getString(KEY_AUTHOR);
    }

    public String getTitle() {
        return getString(KEY_TITLE);
    }

    public String getImgUrl() {
        return getString(KEY_IMG_URL);
    }

    public String getType() {
        return getString(KEY_TYPE).toUpperCase();
    }

    public void setPoster(ParseUser poster) {
        put(KEY_POSTER, poster);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public void setCustomDescription(String description) {
        put(KEY_CUSTOM_DESCRIPTION, description);
    }

    public void setISBN(String isbn) {
        put(KEY_ISBN, isbn);
    }

    public void setAuthor(String author) {
        put(KEY_AUTHOR, author);
    }

    public void setTitle(String title) {
        put(KEY_TITLE, title);
    }

    public void setImgUrl(String imgUrl) {
        put(KEY_IMG_URL, imgUrl);
    }

    public void setType(String type) {
        put(KEY_TYPE, type);
    }



}