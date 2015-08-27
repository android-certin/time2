package com.ciandt.worldwonders.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.database.WonderDAO;
import com.ciandt.worldwonders.helpers.Helpers;
import com.ciandt.worldwonders.model.Wonder;
import com.squareup.picasso.Picasso;

/**
 * Created by wgomes on 26/08/15.
 */
public class WonderDetailFragment extends Fragment {

    Wonder wonder;

    MenuItem itemBookmark;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        wonder = (Wonder) getActivity().getIntent().getSerializableExtra("wonder");
        View view = inflater.inflate(R.layout.activity_wonder_detail, container, false);

        final Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) view.findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(wonder.getName());
        toolbar.inflateMenu(R.menu.menu_world_wonder);

        TextView txDetailDescription = (TextView) getActivity().findViewById(R.id.detail_description);
        ImageView imgDetail = (ImageView) getActivity().findViewById(R.id.detail_image);

        txDetailDescription.setText(wonder.getDescription().toString());

        String img = wonder.getPhoto().split("\\.")[0];

        Picasso.with(getActivity()).load(Helpers.getRawResourceID(getActivity(), img))
                .config(Bitmap.Config.RGB_565).resize(300,300).centerCrop()
                .into(imgDetail);

        TextView txtFonte = (TextView) view.findViewById(R.id.detail_fonte);
        txtFonte.setText(R.string.detail_description);
        txtFonte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WebViewFragment webFragment = new WebViewFragment();
                Bundle bd = new Bundle();
                bd.putSerializable("wonderItem",wonder);
                webFragment.show(getFragmentManager()).setArguments(bd);
            }
        });

        return view;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_bookmark:
                WonderDAO wonderDAO = new WonderDAO(getActivity());
                if (wonder.isBookMark()){
                    wonderDAO.deleteBookMark(wonder);
                    itemBookmark.setIcon(R.drawable.ic_bookmark_border_white_24dp);
                }else {
                    wonderDAO.insertBookMark(wonder);
                    itemBookmark.setIcon(R.drawable.ic_bookmark_white_24dp);
                }

                break;

            case R.id.action_share:

                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);

                break;

            case R.id.action_directions:

                if (wonder.getLatitude()!=0.0&&wonder.getLongitude()!=0.0){

                    Uri gmmIntentUri = Uri.parse("geo:"+wonder.getLatitude() +","+wonder.getLongitude());
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }

                else {
                    Toast.makeText(getActivity(), getResources().getText(R.string.mensagem_direcao_invalida), Toast.LENGTH_SHORT).show();
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }


 
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_world_wonder, menu);

        itemBookmark= menu.findItem(R.id.action_bookmark);

        if (wonder.isBookMark()) {
            itemBookmark.setIcon(R.drawable.ic_bookmark_white_24dp);
        }
    }


}
