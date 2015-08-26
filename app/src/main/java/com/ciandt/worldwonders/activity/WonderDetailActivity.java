package com.ciandt.worldwonders.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.model.Wonder;

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
        txDetailName.setText(wonder.getName().toString());

    }

}
