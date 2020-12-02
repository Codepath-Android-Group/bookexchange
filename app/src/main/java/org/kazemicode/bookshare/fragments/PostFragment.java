package org.kazemicode.bookshare.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;

import org.kazemicode.bookshare.R;
import org.kazemicode.bookshare.models.Post;

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
    private EditText etType;
    private EditText etCustom;
    private Button btnSubmit;

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
        return inflater.inflate(R.layout.fragment_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        etISBN = view.findViewById(R.id.etISBN);
        etAuthor = view.findViewById(R.id.etAuthor);
        etTitle = view.findViewById(R.id.etTitle);
        etImgURL = view.findViewById(R.id.etImgURL);
        etType = view.findViewById(R.id.etType);
        etCustom = view.findViewById(R.id.etCustom);
        btnSubmit = view.findViewById(R.id.btnPost);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isbn = etISBN.getText().toString();
                String author = etAuthor.getText().toString();
                String title = etTitle.getText().toString();
                String imgUrl = etImgURL.getText().toString();
                String type = etType.getText().toString();
                String custom = etCustom.getText().toString();

                if(isbn.isEmpty()){
                    Toast.makeText(getContext(), "isbn is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(author.isEmpty()){
                    Toast.makeText(getContext(), "author is required", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(title.isEmpty()){
                    Toast.makeText(getContext(), "title is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(type.isEmpty()){
                    Toast.makeText(getContext(), "type is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(custom.isEmpty()){
                    Toast.makeText(getContext(), "description is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                ParseUser currentUser = ParseUser.getCurrentUser();
                savePost(isbn, author, title, imgUrl,type, custom, currentUser);


            }
        });

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
        https://s1.nyt.com/du/books/images/9780765326386.jpg
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
                    etType.setText("");
                    etISBN.setText("");


                }
            }
        });
    }
}