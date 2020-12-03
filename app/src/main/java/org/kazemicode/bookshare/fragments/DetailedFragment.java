package org.kazemicode.bookshare.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.kazemicode.bookshare.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetailedFragment extends Fragment {
    RelativeLayout container;
    TextView tvTitle;
    TextView tvAuthor;
    TextView tvType;
    TextView tvDateAdded;
    ImageView ivCover;
    TextView tvCustom;
    TextView tvDescription;
    TextView tvPoster;
    TextView tvISBN;

    public DetailedFragment() {
        // Required empty public constructor
    }


    public static DetailedFragment newInstance(String param1, String param2) {
        DetailedFragment fragment = new DetailedFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detailed, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {
            String isbn = getArguments().getString("isbn");
            String title = getArguments().getString("title");
            String author = getArguments().getString("author");
            String img = getArguments().getString("img");
            String custom = getArguments().getString("custom");
            String summary = getArguments().getString("summary");
            String poster = getArguments().getString("poster");
            String type = getArguments().getString("type");
            String date = getArguments().getString("date");

            tvTitle = getView().findViewById(R.id.tvTitle);
            tvAuthor = getView().findViewById(R.id.tvAuthor);
            tvDescription = getView().findViewById(R.id.tvDescription);
            tvCustom = getView().findViewById(R.id.tvCustom);
            ivCover = getView().findViewById(R.id.ivCover);
            tvType = getView().findViewById(R.id.tvType);
            tvPoster = getView().findViewById(R.id.tvPoster);
            tvDateAdded = getView().findViewById(R.id.tvDateAdded);
            tvISBN = getView().findViewById(R.id.tvISBN);

            tvTitle.setText(title);
            tvAuthor.setText(author);
            tvType.setText(type);
            tvDateAdded.setText(date);
            Glide.with(getContext()).load(img).into(ivCover);
            tvCustom.setText(custom);
            tvDescription.setText(summary);
            tvPoster.setText(poster);
            tvISBN.setText(isbn);
        }
    }
}