package com.ciandt.worldwonders.fragment;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaSync;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.database.WonderDAO;
import com.ciandt.worldwonders.helpers.Helpers;
import com.ciandt.worldwonders.model.Wonder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.List;


/**
 * Created by andersonr on 21/08/15.
 */
public class HighlightFragment extends Fragment {

    DialogFragment progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        progressDialog =  new ProgressDialogFragment().show(getFragmentManager());

        ImageView imageView = (ImageView)view.findViewById(R.id.img_wonders);
        TextView textImg = (TextView) view.findViewById(R.id.nameImg);

        Wonder wonder = (Wonder) getArguments().getSerializable("wonder");

        String img = wonder.getPhoto().split("\\.")[0];


        Picasso.with(getContext()).load(Helpers.getRawResourceID(getContext(),img)).config(Bitmap.Config.RGB_565)
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                       // progressDialog.dismiss();
                    }

                    @Override
                    public void onError() {
                        // TODO Auto-generated method stub

                    }
                });

        textImg.setText(wonder.getName());



    }
    
   public static HighlightFragment newInstance(Wonder wonder) {
        
        Bundle args = new Bundle();
        args.putSerializable("wonder",wonder);
        HighlightFragment fragment = new HighlightFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
