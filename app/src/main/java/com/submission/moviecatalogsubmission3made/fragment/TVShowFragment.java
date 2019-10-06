package com.submission.moviecatalogsubmission3made.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.submission.moviecatalogsubmission3made.R;
import com.submission.moviecatalogsubmission3made.adapter.CardMovieAdapter;
import com.submission.moviecatalogsubmission3made.model.Movie;
import com.submission.moviecatalogsubmission3made.model.MovieData;

import java.util.ArrayList;

public class TVShowFragment extends Fragment {

    private CardMovieAdapter adapter;
    private ProgressBar progressBar;

    public TVShowFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        adapter = new CardMovieAdapter();
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        progressBar = view.findViewById(R.id.progressBar);

        RecyclerView rvMovies = view.findViewById(R.id.rv_movie);
        rvMovies.setHasFixedSize(true);
        rvMovies.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rvMovies.setAdapter(adapter);

        //list.addAll(MovieData.getListData("TVShow"));
        MovieData moviesViewModel = ViewModelProviders.of(this).get(MovieData.class);
        moviesViewModel.getMovies().observe(this, getMovie);
        moviesViewModel.setMovies("tv");

        showLoading(true);

        return view;
    }

    private Observer<ArrayList<Movie>> getMovie = new Observer<ArrayList<Movie>>() {
        @Override
        public void onChanged(ArrayList<Movie> movies) {
            if (movies != null) {
                adapter.setData(movies);
            }

            showLoading(false);
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

}


