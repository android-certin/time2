package com.ciandt.worldwonders.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
public class WonderDetailActivity extends AppCompatActivity {

    Wonder wonder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wonder = (Wonder) getIntent().getSerializableExtra("wonder");
        setContentView(R.layout.activity_wonder_detail);

        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setBackgroundColor(getResources().getColor(android.R.color.transparent));
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(wonder.getName());
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView txDetailDescription = (TextView) findViewById(R.id.detail_description);
        ImageView imgDetail = (ImageView) findViewById(R.id.detail_image);

        txDetailDescription.setText(wonder.getDescription().toString());

        String img = wonder.getPhoto().split("\\.")[0];

        Picasso.with(this).load(Helpers.getRawResourceID(this, img))
                .config(Bitmap.Config.RGB_565).resize(300,300).centerCrop()
                .into(imgDetail);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.action_bookmark:
                WonderDAO wonderDAO = new WonderDAO(this);
                if (wonder.isBookMark()){
                    wonderDAO.deleteBookMark(wonder);
                }else
                {
                    wonderDAO.insertBookMark(wonder);
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
                    Toast.makeText(this, getResources().getText(R.string.mensagem_direcao_invalida), Toast.LENGTH_SHORT).show();
                }

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_world_wonder, menu);
        return true;
    }
}
