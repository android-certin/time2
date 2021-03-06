package com.ciandt.worldwonders.adapter;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.activity.WonderDetailActivity;
import com.ciandt.worldwonders.fragment.WonderDetailFragment;
import com.ciandt.worldwonders.helpers.Helpers;
import com.ciandt.worldwonders.model.Wonder;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by wgomes on 25/08/15.
 */
public class WonderRecyclerAdapter extends RecyclerView.Adapter<WonderRecyclerAdapter.ViewHolder> {


    private List<Wonder> wonderLista;
    private Context context;
    WonderOnClickListener listener;


    public WonderRecyclerAdapter(List<Wonder> wonderList, Context context, WonderOnClickListener listener) {
        this.listener = listener;
        this.wonderLista = wonderList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemLayoutView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycle_view_layout, parent,false);

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.txtViewTitle.setText(wonderLista.get(position).getName());
        String imgStr = wonderLista.get(position).getPhoto().split("\\.")[0];
        Picasso.with(context).load(Helpers.getRawResourceID(context, imgStr))
                .config(Bitmap.Config.RGB_565).resize(250,250).centerCrop()
                .into(holder.imgViewIcon);
        final Wonder wonder = wonderLista.get(position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(wonder);
                //Intent intent = new Intent(context, WonderDetailActivity.class);
                //intent.putExtra("wonder", wonder);
                //v.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return wonderLista.size();
    }

    public interface WonderOnClickListener{
        void onClick(Wonder wonder);
    }

    public class ViewHolder extends RecyclerView.ViewHolder{


        public TextView txtViewTitle;
        public ImageView imgViewIcon;

        public ViewHolder(final View itemLayoutView) {
            super(itemLayoutView);
            txtViewTitle = (TextView) itemLayoutView.findViewById(R.id.text_list);
            imgViewIcon = (ImageView) itemLayoutView.findViewById(R.id.img_list);

        }

    }
}