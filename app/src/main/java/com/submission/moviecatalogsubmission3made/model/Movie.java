package com.submission.moviecatalogsubmission3made.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Movie implements Parcelable {
    private String name;
    private String date;
    private String category;
    private String description;
    private String photo;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Movie(JSONObject object) {
        try {

            String titleMovie = object.optString("title", "-");
            String titleMovieOrTv, dateMovieOrTv, categoryMovieOrTv;

            if(titleMovie.equals("-")){
                titleMovieOrTv = object.getString("name");
                dateMovieOrTv = object.getString("first_air_date");
                categoryMovieOrTv = "TV SHOW";
            }else{
                titleMovieOrTv = object.getString("title");
                dateMovieOrTv = object.getString("release_date");
                categoryMovieOrTv = "MOVIE";
            }

            String name = titleMovieOrTv;
            String date = dateMovieOrTv;
            String category = categoryMovieOrTv;
            String description = object.getString("overview");
            String poster_path = object.getString("poster_path");

            this.name = name;
            this.date = date;
            this.category = category;
            this.description = description;
            this.photo = poster_path;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.date);
        dest.writeString(this.category);
        dest.writeString(this.description);
        dest.writeString(this.photo);
    }

    protected Movie(Parcel in) {
        this.name = in.readString();
        this.date = in.readString();
        this.category = in.readString();
        this.description = in.readString();
        this.photo = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel source) {
            return new Movie(source);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
}
