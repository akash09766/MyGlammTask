package com.myglamm.myglammtask.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import com.myglamm.myglammtask.R;
import com.myglamm.myglammtask.model.AvailableArtists;
import com.myglamm.myglammtask.util.GConstants;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

/**
 * Created by Akash Wangalwar on 27-10-2016.
 */
public class AvailableArtistAdapter extends
        RecyclerView.Adapter<AvailableArtistAdapter.ArtistViewholder>{

    private final LayoutInflater inflator;
    private final ImageLoader mImageLoader;
    private ArrayList<AvailableArtists> mArtistList;
    private Context context;

    public AvailableArtistAdapter(Context context, ArrayList<AvailableArtists> artistList) {

        this.context = context;
        this.mArtistList = artistList;
        inflator = LayoutInflater.from(context);
        mImageLoader = ImageLoader.getInstance();
    }

    @Override
    public ArtistViewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflator.inflate(R.layout.artist_list_row,parent,false);
        return new ArtistViewholder(view);
    }

    @Override
    public void onBindViewHolder(ArtistViewholder holder, int position) {
        String name = mArtistList.get(position).getFirstName().substring(0, 1).toUpperCase() +
                mArtistList.get(position).getFirstName().substring(1).toLowerCase()+" "+
                mArtistList.get(position).getLastName().substring(0, 1).toUpperCase() +
                mArtistList.get(position).getLastName().substring(1).toLowerCase();
        holder.mArtistName.setText(name);

        mImageLoader.displayImage(GConstants.ARTIST_PROFILE_PRE_LINK_URL+mArtistList.get(position).getImageURL(),
                holder.mProfileIv);
        holder.mArtistListRating.setRating(mArtistList.get(position).getAggregatedRating());
        holder.mExperience.setText(mArtistList.get(position).getExperience()+"yrs experience");
        holder.mServiceOffered.setText("3 out of 4 serivices"); // hard coded , bcz i dont know how many serices user requested.
        holder.mPrice.setText(context.getString(R.string.indian_rs_symb0l)+" "+mArtistList.get(position).getTotalPrice());
    }

    @Override
    public int getItemCount() {
        return mArtistList.size();
    }

    public class ArtistViewholder extends RecyclerView.ViewHolder{

        private final RatingBar mArtistListRating;
        private TextView mArtistName;
        private ImageView mProfileIv;
        private TextView mServiceOffered;
        private TextView mExperience;
        private TextView mPrice;

        public ArtistViewholder(View itemView) {
            super(itemView);
            mArtistName = (TextView) itemView.findViewById(R.id.user_name_tv_id);
            mPrice = (TextView) itemView.findViewById(R.id.price_tv_id);
            mServiceOffered = (TextView) itemView.findViewById(R.id.services_offered_tv_id);
            mExperience = (TextView) itemView.findViewById(R.id.experience_tv_id);
            mProfileIv = (ImageView) itemView.findViewById(R.id.user_profile_iv_id);
            mArtistListRating = (RatingBar) itemView.findViewById(R.id.artist_rating_id);
        }
    }
}
