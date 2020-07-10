package com.MovieApps.model.movies;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListMoviesResponse implements Parcelable {

    @SerializedName("popularity") private String popularity;
    @SerializedName("vote_count") private int vote_count;
    @SerializedName("video") private String video;
    @SerializedName("poster_path") private String poster_path;
    @SerializedName("genre_ids") private int[] genre_ids;
    @SerializedName("id") private int id;
    @SerializedName("adult") private String adult;
    @SerializedName("backdrop_path") private String backdrop_path;
    @SerializedName("original_language") private String original_language;
    @SerializedName("original_title") private String original_title;
    @SerializedName("title") private String title;
    @SerializedName("vote_average") private String vote_average;
    @SerializedName("overview") private String overview;
    @SerializedName("release_date") private String release_date;



    protected ListMoviesResponse(Parcel in) {
        popularity = in.readString();
        vote_count = in.readInt();
        video = in.readString();
        poster_path = in.readString();
        genre_ids = in.createIntArray();
        id = in.readInt();
        adult = in.readString();
        backdrop_path = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        title = in.readString();
        vote_average = in.readString();
        overview = in.readString();
        release_date = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(popularity);
        dest.writeInt(vote_count);
        dest.writeString(video);
        dest.writeString(poster_path);
        dest.writeIntArray(genre_ids);
        dest.writeInt(id);
        dest.writeString(adult);
        dest.writeString(backdrop_path);
        dest.writeString(original_language);
        dest.writeString(original_title);
        dest.writeString(title);
        dest.writeString(vote_average);
        dest.writeString(overview);
        dest.writeString(release_date);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }
    public static final Creator<ListMoviesResponse> CREATOR = new Creator<ListMoviesResponse>() {
        @Override
        public ListMoviesResponse createFromParcel(Parcel in) {
            return new ListMoviesResponse(in);
        }

        @Override
        public ListMoviesResponse[] newArray(int size) {
            return new ListMoviesResponse[size];
        }
    };
}
