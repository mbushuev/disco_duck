package com.example.maks.discoduck.adapters;

import android.content.Context;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.maks.discoduck.R;
import com.example.maks.discoduck.models.Artist;
import com.example.maks.discoduck.models.CatalogItem;
import com.example.maks.discoduck.models.Track;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class CatalogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final float MAX_START = 5;
    private static final float MIN_RATING_POSITION = 100;

    private Context context;
    private List<CatalogItem> catalogItems;

    public CatalogAdapter(Context context) {
        this.context = context;
    }

    public CatalogAdapter(List<CatalogItem> data, Context context) {
        this.catalogItems = data;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType) {
            case CatalogItem.TYPE_ARTIST: {
                holder = new ArtistViewHolder(LayoutInflater.from(context).inflate(R.layout.item_artist, parent, false));
                break;
            }
            case CatalogItem.TYPE_TRACK: {
                holder = new TrackViewHolder(LayoutInflater.from(context).inflate(R.layout.item_track, parent, false));
                break;
            }
        }

        return holder;
    }

    @Override
    public int getItemCount() {
        return null != catalogItems ? catalogItems.size() : 0;
    }

    @Override
    public int getItemViewType(int position) {
        return null != catalogItems ? catalogItems.get(position).getType() : 0;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (null != holder && null != catalogItems && position < catalogItems.size() && null != catalogItems.get(position)) {
            switch (holder.getItemViewType()) {
                case CatalogItem.TYPE_ARTIST: {
                    constructArtistHolder((ArtistViewHolder) holder, (Artist) catalogItems.get(position));
                    break;
                }
                case CatalogItem.TYPE_TRACK: {
                    constructTrackHolder((TrackViewHolder) holder, (Track) catalogItems.get(position));
                    break;
                }
            }
        }
    }

    private void constructArtistHolder(ArtistViewHolder artistHolder, Artist artist) {
        artistHolder.name.setText(artist.getName());
        artistHolder.ratingBar.setRating(getRatingInStars(artist));
        artistHolder.position.setText(String.valueOf(artist.getPosition()));
    }

    private float getRatingInStars(Artist artist) {
        return MAX_START * (MIN_RATING_POSITION - artist.getRating()) / MIN_RATING_POSITION;
    }

    private void constructTrackHolder(TrackViewHolder trackHolder, Track track) {
        trackHolder.rating.setText(String.valueOf(track.getRating()));
        trackHolder.name.setText(track.getName());
        trackHolder.album.setText(track.getAlbumName());
        trackHolder.position.setText(String.valueOf(track.getPosition()));

        int color = track.getPosition() % 2 == 0 ? R.color.track_background_light : R.color.track_background_dark;
        trackHolder.container.setBackgroundColor(ResourcesCompat.getColor(context.getResources(), color, null));

        Picasso.with(context)
                .load(track.getAlbumCoverUrl())
                .error(R.drawable.stub)
                .placeholder(R.drawable.stub)
                .into(trackHolder.cover);
    }

    class ArtistViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_artist_iv_avatar)
        ImageView avatar;
        @BindView(R.id.item_artist_tv_name)
        TextView name;
        @BindView(R.id.item_artist_tv_position)
        TextView position;
        @BindView(R.id.item_artist_rb_rating)
        RatingBar ratingBar;

        ArtistViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class TrackViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_track_tv_track_name)
        TextView name;
        @BindView(R.id.item_track_tv_track_rating)
        TextView rating;
        @BindView(R.id.item_track_tv_album)
        TextView album;
        @BindView(R.id.item_track_tv_position)
        TextView position;
        @BindView(R.id.item_track_ll_container)
        LinearLayout container;
        @BindView(R.id.item_track_iv_cover)
        ImageView cover;

        TrackViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
