package org.kazemicode.bookshare.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.kazemicode.bookshare.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BestSellerBooksFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BestSellerBooksFragment extends Fragment {


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BestSellerBooksFragment() {
        // Required empty public constructor
    }

    @SuppressWarnings("unused")
    public static BestSellerBooksFragment newInstance(int columnCount) {
        BestSellerBooksFragment fragment = new BestSellerBooksFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_best_seller_books, container, false);
    }
}