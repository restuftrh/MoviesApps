package com.MovieApps.model.favorite;

import com.google.gson.annotations.SerializedName;

public class FavoriteSeriesParam {
    @SerializedName("original_name") private String original_name;
    @SerializedName("name") private String name;
    @SerializedName("popularity") private String popularity;
    @SerializedName("vote_count") private String vote_count;
    @SerializedName("first_air_date") private String first_air_date;
    @SerializedName("genre_ids") private int genre_ids;
    @SerializedName("backdrop_path") private String backdrop_path;
    @SerializedName("original_language") private String original_language;
    @SerializedName("id") private int id;
    @SerializedName("status") private int status;
    @SerializedName("vote_average") private String vote_average;
    @SerializedName("overview") private String overview;
    @SerializedName("poster_path") private String poster_path;


    public FavoriteSeriesParam(String original_name, String name, String popularity, String vote_count, String first_air_date,
                               int genre_ids, String backdrop_path, String original_language, int id, int status, String vote_average,
                               String overview, String poster_path) {
        this.original_name = original_name;
        this.name = name;
        this.popularity = popularity;
        this.vote_count = vote_count;
        this.first_air_date = first_air_date;
        this.genre_ids = genre_ids;
        this.backdrop_path = backdrop_path;
        this.original_language = original_language;
        this.id = id;
        this.status = status;
        this.vote_average = vote_average;
        this.overview = overview;
        this.poster_path = poster_path;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public int getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int genre_ids) {
        this.genre_ids = genre_ids;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }



}
