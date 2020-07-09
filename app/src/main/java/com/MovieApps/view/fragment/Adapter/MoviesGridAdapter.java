package com.MovieApps.view.fragment.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.MovieApps.R;
import com.MovieApps.model.movies.ListMoviesResponse;
import com.bumptech.glide.Glide;

import net.derohimat.baseapp.ui.adapter.BaseRecyclerAdapter;
import net.derohimat.baseapp.ui.adapter.viewholder.BaseItemViewHolder;
import net.derohimat.baseapp.ui.view.BaseImageView;

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

        @Bind(R.id.score_row) TextView score;
        @Bind(R.id.image_row) BaseImageView image;
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
            if(Integer.valueOf(data.getVote_average()) <= 10){
                if (Integer.valueOf(data.getVote_average()) >= 8){
                    score.setText("A");
                }
            }else if(Integer.valueOf(data.getVote_average()) <= 7){
                if (Integer.valueOf(data.getVote_average()) >= 6){
                    score.setText("B");
                }
            }else if(Integer.valueOf(data.getVote_average()) <= 5){
                if (Integer.valueOf(data.getVote_average()) >= 4){
                    score.setText("C");
                }
            }else if(Integer.valueOf(data.getVote_average()) < 4){
                score.setText("D");
            }

            Glide.with(getContext()).load("http://goo.gl/gEgYUd").into(image);
            rating.setText(data.getVote_average());
            title.setText(data.getTitle());
            date.setText(data.getRelease_date());
            overview.setText(data.getOverview());




        }
    }
}

