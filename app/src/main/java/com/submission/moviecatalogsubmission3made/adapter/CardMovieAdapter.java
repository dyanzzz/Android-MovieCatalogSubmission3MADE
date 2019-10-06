package com.submission.moviecatalogsubmission3made.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.submission.moviecatalogsubmission3made.Detail;
import com.submission.moviecatalogsubmission3made.R;
import com.submission.moviecatalogsubmission3made.model.Movie;

import java.util.ArrayList;

public class CardMovieAdapter extends RecyclerView.Adapter<CardMovieAdapter.MovieViewHolder> {

    private ArrayList<Movie> mData = new ArrayList<>();
    public void setData(ArrayList<Movie> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_movie, viewGroup, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        movieViewHolder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView imgPhoto;
        TextView tvName, tvDate, tvDescription;
        Button btnFavorite, btnShare;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            btnFavorite = itemView.findViewById(R.id.btn_set_favorite);
            btnShare = itemView.findViewById(R.id.btn_set_share);

            itemView.setOnClickListener(this);
        }

        void bind(Movie movies) {
            String url_image = "https://image.tmdb.org/t/p/w185" + movies.getPhoto();

            tvName.setText(movies.getName());
            tvDate.setText(movies.getDate());
            tvDescription.setText(movies.getDescription());

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .apply(new RequestOptions().override(350, 550))
                    .into(imgPhoto);

            btnFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Favorite " +
                            mData.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                }
            });
            btnShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(itemView.getContext(), "Share " +
                            mData.get(getAdapterPosition()).getName(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movie movie = mData.get(position);

            movie.setName(movie.getName());
            movie.setDescription(movie.getDescription());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), Detail.class);
            moveWithObjectIntent.putExtra(Detail.EXTRA_MOVIE, movie);
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }


}