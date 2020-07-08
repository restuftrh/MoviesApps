package com.MovieApps.view.main.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;


public class ModuleResponse implements Parcelable  {

    @SerializedName("id") private int id;
    @SerializedName("module") private String merek;
    @SerializedName("urutan") private String urutan;
    @SerializedName("image_path") private String image_path;
    @SerializedName("description") private String description;
    @SerializedName("created_at") private String created_at;
    @SerializedName("updated_at") private String updated_at;

    public int getId() {
        return id;
    }

    public String getMerek() {
        return merek;
    }

    public String getUrutan() {
        return urutan;
    }

    public String getImage_path() {
        return image_path;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.merek);
        parcel.writeString(this.urutan);
        parcel.writeString(this.image_path);
        parcel.writeString(this.description);
        parcel.writeString(this.created_at);
        parcel.writeString(this.updated_at);
    }

    protected ModuleResponse(Parcel in) {
        this.id = in.readInt();
        this.merek = in.readString();
        this.urutan = in.readString();
        this.image_path = in.readString();
        this.description = in.readString();
        this.created_at = in.readString();
        this.updated_at = in.readString();
    }

    public static final Creator<ModuleResponse> CREATOR = new Creator<ModuleResponse>() {
        @Override
        public ModuleResponse createFromParcel(Parcel in) {
            return new ModuleResponse(in);
        }

        @Override
        public ModuleResponse[] newArray(int size) {
            return new ModuleResponse[size];
        }
    };
}
