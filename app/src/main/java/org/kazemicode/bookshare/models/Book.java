package org.kazemicode.bookshare.models;

import org.json.JSONArray;
import org.json.JSONException;
import java.util.List;
import java.util.ArrayList;

import org.json.JSONObject;
import org.parceler.Parcel;

@Parcel
public class Book {

    // instance variables
    private String title;
    private String author;
    private String isbn; // get primary_isbn13
    private String description;
    private String rank;
    private String image_url; // need Glide

    // empty constructor needed by Parceler library
    public Book(){}

    public Book(JSONObject jsonObject) throws JSONException {
        this.title = jsonObject.getString("title");
        this.author = jsonObject.getString("author");
        this.isbn = jsonObject.getString("primary_isbn13");
        this.description = jsonObject.getString("description");
        this.image_url = jsonObject.getString("book_image");
        this.rank = jsonObject.getString("rank");
    }

    public static List<Book> fromJsonArray(JSONArray bookJsonArray) throws JSONException {
        List<Book> books = new ArrayList<>();
        for(int i = 0; i < bookJsonArray.length(); i++){
            books.add(new Book(bookJsonArray.getJSONObject(i)));
        }

        return books;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getDescription() {
        return description;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getRank() {
        return rank;
    }


    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", description='" + description + '\'' +
                ", image_url='" + image_url + '\'' +
                '}';
    }
}
