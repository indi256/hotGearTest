package com.indigo.hotgear.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.indigo.hotgear.R;
import com.indigo.hotgear.model.Shot;

import java.util.List;

import io.realm.Realm;

public class ShotsAdapter extends RecyclerView.Adapter<ShotsAdapter.ViewHolder> {
    private Realm realm = Realm.getDefaultInstance();
    private List<Shot> shots;
    private Context context;


    public ShotsAdapter(List<Shot> shots) {
        this.shots = shots;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shot_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Shot shot = shots.get(position);
        holder.title.setText(shot.getTitle());
        if (!TextUtils.isEmpty(shot.getDescription())) {
            holder.descr.setText(Html.fromHtml(shot.getDescription()));
        }
        Glide.with(context).load(shot.getImageSize().getLowRes()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return shots.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView image;
        private TextView title, descr;

        public ViewHolder(View itemView) {
            super(itemView);

            image = (ImageView) itemView.findViewById(R.id.shot_item_image);
            title = (TextView) itemView.findViewById(R.id.shot_item_title);
            descr = (TextView) itemView.findViewById(R.id.shot_item_description);
            context = itemView.getContext();

        }

        @Override
        public void onClick(View v) {

        }
    }
}
