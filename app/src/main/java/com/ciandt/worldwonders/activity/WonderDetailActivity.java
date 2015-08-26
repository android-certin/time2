package com.ciandt.worldwonders.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ciandt.worldwonders.R;
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

        TextView txDetailName = (TextView) findViewById(R.id.detail_name);
        TextView txDetailDescription = (TextView) findViewById(R.id.detail_description);
        ImageView imgDetail = (ImageView) findViewById(R.id.detail_image);

        txDetailName.setText(wonder.getName().toString());
        txDetailDescription.setText(wonder.getDescription().toString());

        String img = wonder.getPhoto().split("\\.")[0];

        Picasso.with(this).load(Helpers.getRawResourceID(this, img))
                .config(Bitmap.Config.RGB_565).resize(300,300).centerCrop()
                .into(imgDetail);


    }

}
