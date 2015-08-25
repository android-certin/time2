package com.ciandt.worldwonders.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ciandt.worldwonders.R;
import com.ciandt.worldwonders.database.WonderDAO;
import com.ciandt.worldwonders.model.Wonder;

import java.util.List;

/**
 * Created by andersonr on 21/08/15.
 */
public class HighlightFragment extends Fragment {
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
        
        ImageView imageView = (ImageView)view.findViewById(R.id.img_wonders);

    }
    
   public static HighlightFragment newInstance(Wonder wonder) {
        
        Bundle args = new Bundle();
        args.putSerializable("wonder",wonder);
        HighlightFragment fragment = new HighlightFragment();
        fragment.setArguments(args);
        return fragment;
    }
}
