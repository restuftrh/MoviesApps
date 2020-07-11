package com.MovieApps.model.favorite;

import com.google.gson.annotations.SerializedName;

public class FavoriteParam {
    @SerializedName("poster_path") private String poster_path;
    @SerializedName("genre_ids") private int genre_ids;
    @SerializedName("id") private int id;
    @SerializedName("original_title") private String original_title;
    @SerializedName("title") private String title;
    @SerializedName("vote_average") private String vote_average;
    @SerializedName("overview") private String overview;
    @SerializedName("release_date") private String release_date;


    public FavoriteParam(String poster_path, int genre_ids, int id, String original_title, String title, String vote_average,
                         String overview, String release_date ) {
        this.poster_path = poster_path;
        this.genre_ids = genre_ids;
        this.id = id;
        this.original_title = original_title;
        this.title = title;
        this.vote_average = vote_average;
        this.overview = overview;
        this.release_date = release_date;

    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public int getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int genre_ids) {
        this.genre_ids = genre_ids;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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


}
