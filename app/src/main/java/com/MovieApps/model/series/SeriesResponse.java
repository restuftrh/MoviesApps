package com.MovieApps.model.series;

import android.os.Parcel;
import android.os.Parcelable;

import com.MovieApps.model.movies.ListMoviesResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SeriesResponse implements Parcelable {

    @SerializedName("page") private int page;
    @SerializedName("total_results") private int total_results;
    @SerializedName("total_pages") private int total_pages;
    @SerializedName("results") private List<ListSeriesResponse> results;



    protected SeriesResponse(Parcel in) {
        page = in.readInt();
        total_results = in.readInt();
        total_pages = in.readInt();
        results = in.createTypedArrayList(ListSeriesResponse.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(page);
        dest.writeInt(total_results);
        dest.writeInt(total_pages);
        dest.writeTypedList(results);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public List<ListSeriesResponse> getResults() {
        return results;
    }

    public void setResults(List<ListSeriesResponse> results) {
        this.results = results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<SeriesResponse> CREATOR = new Creator<SeriesResponse>() {
        @Override
        public SeriesResponse createFromParcel(Parcel in) {
            return new SeriesResponse(in);
        }

        @Override
        public SeriesResponse[] newArray(int size) {
            return new SeriesResponse[size];
        }
    };
}
