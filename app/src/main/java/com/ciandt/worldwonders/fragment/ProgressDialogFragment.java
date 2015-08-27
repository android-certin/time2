package com.ciandt.worldwonders.fragment;

import android.app.Dialog;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.ciandt.worldwonders.R;

/**
 * Created by wgomes on 27/08/15.
 */
public class ProgressDialogFragment extends DialogFragment {

    public static DialogFragment show(FragmentManager fm) {
        ProgressDialogFragment progressDialogFragment = new ProgressDialogFragment();
        progressDialogFragment.show(fm, "progress_dialog_fragment");

        return progressDialogFragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_dialog, null);

        ImageView imgAnim01 = (ImageView)view.findViewById(R.id.anim_01);
        ImageView imgAnim02 = (ImageView)view.findViewById(R.id.anim_02);
        ImageView imgAnim03 = (ImageView)view.findViewById(R.id.anim_03);
        ImageView imgAnim04 = (ImageView)view.findViewById(R.id.anim_04);
        ImageView imgAnim05 = (ImageView)view.findViewById(R.id.anim_05);


        ((AnimationDrawable) imgAnim01.getBackground()).start();
        ((AnimationDrawable) imgAnim02.getBackground()).start();
        ((AnimationDrawable) imgAnim03.getBackground()).start();
        ((AnimationDrawable) imgAnim04.getBackground()).start();
        ((AnimationDrawable) imgAnim05.getBackground()).start();



        AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setView(view)
                .create();


        return alertDialog;


    }
}
