package com.submission.moviecatalogsubmission3made;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.submission.moviecatalogsubmission3made.model.Movie;

import java.lang.reflect.Constructor;

import de.hdodenhof.circleimageview.CircleImageView;

public class Detail extends AppCompatActivity {

    public static final String EXTRA_MOVIE = "extra_movie";

    TextView tvName, tvDate, tvDescription, tvPhotoQty,
            textDirector, directorText;
    Button buyTicket;
    private ProgressBar progressBar;
    ImageView imgPhoto;
    CircleImageView profilDirector;
    ActionBar actionBar;
    Handler handler;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");

        tvName = findViewById(R.id.item_name);
        tvDate = findViewById(R.id.item_date);
        tvDescription = findViewById(R.id.item_description);
        tvPhotoQty = findViewById(R.id.item_photo_qty);
        imgPhoto = findViewById(R.id.item_photo);

        directorText = findViewById(R.id.director_text);
        textDirector = findViewById(R.id.text_director);
        profilDirector = findViewById(R.id.profile_director);
        buyTicket = findViewById(R.id.button_buy_ticket);

        progressBar = findViewById(R.id.progressDetailMovie);
        progressBar.setVisibility(View.VISIBLE);

        handler = new Handler();


        new Thread(new Runnable() {
            public void run() {
                try{
                    Thread.sleep(5000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                handler.post(new Runnable() {
                    public void run() {

                        tvName.setVisibility(View.VISIBLE);
                        tvDate.setVisibility(View.VISIBLE);
                        tvDescription.setVisibility(View.VISIBLE);
                        tvPhotoQty.setVisibility(View.VISIBLE);
                        imgPhoto.setVisibility(View.VISIBLE);

                        directorText.setVisibility(View.VISIBLE);
                        textDirector.setVisibility(View.VISIBLE);
                        profilDirector.setVisibility(View.VISIBLE);
                        buyTicket.setVisibility(View.VISIBLE);

                            Movie movie = getIntent().getParcelableExtra(EXTRA_MOVIE);
                            String name = movie.getName();
                            String date = movie.getDate();
                            String category = movie.getCategory();
                            String description = movie.getDescription();
                            String url_image = "https://image.tmdb.org/t/p/w185" + movie.getPhoto();

                            actionBar.setTitle(name);

                            tvName.setText(name);
                            tvDate.setText(date);
                            tvPhotoQty.setText(category);
                            tvDescription.setText(description);

                            Glide.with(Detail.this)
                                    .load(url_image)
                                    .into(imgPhoto);
                            progressBar.setVisibility(View.INVISIBLE);

                    }
                });
            }
        }).start();

    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
