package com.MovieApps.view.fragment.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.MovieApps.R;
import com.MovieApps.data.local.PreferencesHelper;
import com.MovieApps.model.favorite.FavoriteMoviesParam;
import com.MovieApps.model.movies.ListMoviesResponse;
import com.bumptech.glide.Glide;

import net.derohimat.baseapp.ui.adapter.BaseRecyclerAdapter;
import net.derohimat.baseapp.ui.adapter.viewholder.BaseItemViewHolder;
import net.derohimat.baseapp.ui.view.BaseImageView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

public class MoviesGridAdapter extends BaseRecyclerAdapter<ListMoviesResponse, MoviesGridAdapter.ViewHolder> {
    @Inject
    public MoviesGridAdapter(Context context) {
        super(context);
    }

    @Override
    protected int getItemResourceLayout(int i) {
        return R.layout.item_row;
    }

    @Override
    public MoviesGridAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new MoviesGridAdapter.ViewHolder(
                getView(viewGroup, i),
                mItemClickListener,
                mLongItemClickListener
        );
    }

    static class ViewHolder extends BaseItemViewHolder<ListMoviesResponse> {

        @Bind(R.id.bg_row) RelativeLayout bgColor;
        @Bind(R.id.score_row) TextView score;
        @Bind(R.id.image_row) BaseImageView image;
        @Bind(R.id.favorite_row) BaseImageView favorite;
        @Bind(R.id.rating_row) TextView rating;
        @Bind(R.id.title_row) TextView title;
        @Bind(R.id.date_row) TextView date;
        @Bind(R.id.gendre_row) TextView gendre;
        @Bind(R.id.overview_row) TextView overview;

        public ViewHolder(View itemView, BaseRecyclerAdapter.OnItemClickListener itemClickListener, BaseRecyclerAdapter.OnLongItemClickListener longItemClickListener) {
            super(itemView, itemClickListener, longItemClickListener);
        }

        public Context getContext() {return itemView.getContext();}


        @Override
        public void bind(ListMoviesResponse data) {
            if (PreferencesHelper.getFavoriteMovies() != null){
                for (int j=0; j<PreferencesHelper.getFavoriteMovies().size(); j++) {
                    FavoriteMoviesParam eachMoviest = PreferencesHelper.getFavoriteMovies().get(j);
                    if (data.getId() == eachMoviest.getId()) {
                        favorite.setVisibility(View.VISIBLE);
                        break;
                    }else{
                        favorite.setVisibility(View.GONE);
                    }
                }

            }

            if(Double.valueOf(data.getVote_average()) <= 10){
                if (Double.valueOf(data.getVote_average()) >= 8){
                    score.setText("A");
                }else if(Double.valueOf(data.getVote_average()) <= 7){
                    if (Double.valueOf(data.getVote_average()) >= 6){
                        score.setText("B");
                    }else if (Double.valueOf(data.getVote_average()) <= 5){
                        if (Double.valueOf(data.getVote_average()) >= 4){
                            score.setText("C");
                        }else if (Double.valueOf(data.getVote_average()) < 4){
                            score.setText("D");
                        }
                    }
                }
            }

            if (data.getGenre_ids()[0] == 1){
                gendre.setText("Action");
                bgColor.setBackgroundColor(Color.parseColor("#e31102"));
            }else  if(data.getGenre_ids()[0] == 2){
                gendre.setText("Drama");
                bgColor.setBackgroundColor(Color.parseColor("#1a8cff"));
            }else  if(data.getGenre_ids()[0] == 3){
                gendre.setText("Comedy");
                bgColor.setBackgroundColor(Color.parseColor("#27d604"));
            }else  if(data.getGenre_ids()[0] == 4){
                gendre.setText("Animation");
                bgColor.setBackgroundColor(Color.parseColor("#fff317"));
            }else {
                gendre.setText("Other");
                bgColor.setBackgroundColor(Color.parseColor("#b800a5"));
            }

            Glide.with(getContext()).load("https://image.tmdb.org/t/p/w500/" + data.getPoster_path()).into(image);
            rating.setText(data.getVote_average());
            title.setText(data.getTitle());
            date.setText(data.getRelease_date());
            overview.setText(data.getOverview());

        }
    }

}

