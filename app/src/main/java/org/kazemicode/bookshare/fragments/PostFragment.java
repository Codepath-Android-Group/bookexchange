package org.kazemicode.bookshare.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.kazemicode.bookshare.BuildConfig;
import org.kazemicode.bookshare.R;
import org.kazemicode.bookshare.models.Post;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PostFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PostFragment extends Fragment {
    private EditText etISBN;
    private EditText etTitle;
    private EditText etAuthor;
    private EditText etImgURL;
    private EditText etCustom;
    private Button btnPost;
    private Button btnLoad;
    private RadioGroup radioType;
    private String type = "";
    private static HttpURLConnection con;

    public static final String TAG = "PostFragment";


    public PostFragment() {
        // Required empty public constructor
    }


    @SuppressWarnings("unused")
    public static PostFragment newInstance(String param1, String param2) {
        PostFragment fragment = new PostFragment();
        Bundle args = new Bundle();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_post, container, false);

        radioType = (RadioGroup) view.findViewById(R.id.etType);
        radioType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                // Check which radio button was clicked
                switch(i) {
                    case R.id.radioBuying:
                        type = "BUYING";
                        break;
                    case R.id.radioSelling:
                        type = "SELLING";
                        break;
                    case R.id.radioTrading:
                        type = "TRADING";
                        break;
                }

            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        etISBN = view.findViewById(R.id.etISBN);
        etAuthor = view.findViewById(R.id.etAuthor);
        etTitle = view.findViewById(R.id.etTitle);
        etImgURL = view.findViewById(R.id.etImgURL);
        etCustom = view.findViewById(R.id.etCustom);
        btnPost = view.findViewById(R.id.btnPost);
        btnLoad = view.findViewById(R.id.btnLoad);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isbn = etISBN.getText().toString();
                try {
                    autoFillFromISBNDB(isbn);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isbn = etISBN.getText().toString();
                String author = etAuthor.getText().toString();
                String title = etTitle.getText().toString();
                String imgUrl = etImgURL.getText().toString();
                String custom = etCustom.getText().toString();

                if (isbn.isEmpty()) {
                    Toast.makeText(getContext(), "isbn is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (author.isEmpty()) {
                    Toast.makeText(getContext(), "author is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (title.isEmpty()) {
                    Toast.makeText(getContext(), "title is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (type.isEmpty()) {
                    Toast.makeText(getContext(), "type is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (custom.isEmpty()) {
                    Toast.makeText(getContext(), "description is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                ParseUser currentUser = ParseUser.getCurrentUser();
                savePost(isbn, author, title, imgUrl, type, custom, currentUser);


            }
        });

    }



    private void autoFillFromISBNDB(String isbn) throws MalformedURLException,
            ProtocolException, IOException {
        String url = "https://api2.isbndb.com/book/";

        try {

            URL myurl = new URL(url + isbn);
            con = (HttpURLConnection) myurl.openConnection();
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Authorization", BuildConfig.ISBN_API_KEY);
            con.setRequestMethod("GET");

            StringBuilder content;

            try (BufferedReader in = new BufferedReader(
                    new InputStreamReader(con.getInputStream()))) {

                String line;
                content = new StringBuilder();

                while ((line = in.readLine()) != null) {
                    content.append(line);
                    content.append(System.lineSeparator());
                }
            }

            String c = content.toString();
            System.out.println(c);

            JSONObject reader = new JSONObject(c);
            JSONObject book = reader.getJSONObject("book");
            etTitle.setText(book.getString("title"));
            etImgURL.setText(book.getString("image"));
            JSONArray author = book.getJSONArray("authors");
            etAuthor.setText(author.get(0).toString());


            } catch (JSONException e) {
                Toast.makeText(getContext(), "Sorry, we couldn't find that book to autoload info", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            } finally {

                 con.disconnect();
        }
    }



    private void savePost(String isbn, String author, String title, String imgUrl, String type, String custom, ParseUser user) {
        Post post = new Post();
        post.setCustomDescription(custom);
        post.setTitle(title);
        post.setISBN(isbn);
        post.setAuthor(author);
        post.setImgUrl(imgUrl);
        post.setPoster(user);
        post.setType(type);

        post.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if(e != null){
                    // there's a problem
                    Log.e(TAG, "Problem saving post", e);
                    Toast.makeText(getContext(), "Problem saving post", Toast.LENGTH_SHORT).show();

                }
                else {

                    Log.i(TAG, "Post saved");
                    etCustom.setText("");
                    etTitle.setText("");
                    etAuthor.setText("");
                    etImgURL.setText("");
                    etISBN.setText("");


                }
            }
        });
    }
}